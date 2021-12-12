package com.scaj.db.scaj_db.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Value;
import com.scaj.security.AuthUser;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.scaj.db.scaj_db.entity.User;

@Service
@PropertySource("classpath:application.properties")
public class SecurityService {
    
	@Autowired
	private Datastore datastore;
	
	@Autowired
	private UserService userService;
	
	@Value( "${token.secret}" )
	private String secret;
	
	//FIND BY USERNAME
	public User findByUsernameAndPassword(String username, String password) throws Exception {
		if(username == null && password == null)
			throw new Exception("Credentials not found");
		if(username == null)
			throw new Exception("Username not found");
		if(password == null)
			throw new Exception("Password not found");
		
		if(userService.countUsers() == 0)
			userService.newUser();
		
		User user = datastore.find(User.class).field("username").equal(username).get();
		if(user == null) {
			throw new Exception("Invalid username");			
		}
		
		if(user.getPassword().equals(password)) {
			Algorithm algorithm;
			Gson gson = new Gson();
			try {
				algorithm = Algorithm.HMAC256(secret);
				Date date = new Date();
				long t = date.getTime();
				Date expirationTime = new Date(t + 28800000l);
			    String token = JWT.create().withSubject(gson.toJson(user)).withExpiresAt(expirationTime)
			    .withClaim("user",gson.toJson(user))
			    .sign(algorithm);    	
			    // Set token in user
			    user.setToken(token);
			    user.setPassword(null);	
				} catch (IllegalArgumentException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else {
				throw new Exception("Password not valid");
			}
		return user;
		
	}
	
	public User findUserByUsername(String username) throws Exception {
		if(username == null)
			throw new Exception("Username not valid");
		User user = datastore.find(User.class).field("username").equal(username).get();
		if(user == null) {
			throw new Exception("Invalid username");			
		}
		
		return user;
		
	}
	
	public User verifyToken(String token) throws Exception {
		if(token == null)
			throw new Exception("Token not found");
		User user = null;
		try {
			//decode token
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify((String)token);
			String userJSON = jwt.getClaims().get("user").asString();

			// Set user in Authentication Service
        	Gson gson = new Gson();
			User DecodedUser = gson.fromJson(userJSON, User.class);
			DecodedUser.setPassword(null);
			user = DecodedUser;
		} catch (IllegalArgumentException | UnsupportedEncodingException | SignatureVerificationException e) {
        	throw new Exception("Token not valid");
		}	
		return user;
	}
	
		public String verifyTokenJson(String token) throws Exception {
		if(token == null)
			throw new Exception("Token not found");
		User user = null;
		try {
			//decode token
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify((String)token);
			String userJSON = jwt.getClaims().get("user").asString();
			return userJSON;
		} catch (IllegalArgumentException | UnsupportedEncodingException | SignatureVerificationException e) {
        	throw new Exception("Token not valid");
		}
	}
	
	public boolean changePasssword(String passwordOld, String passwordNew, HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user = findUserByUsername(((AuthUser) auth.getPrincipal()).getUsername());
		if(user == null) {
			response.setStatus(500);
			return false;
		}
		if(user.getPassword().equals(passwordOld)) {
			user.setPassword(passwordNew);
			userService.update(user);
			return true;
		}
		return false;
	}

}
