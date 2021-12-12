package com.scaj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import com.scaj.db.scaj_db.entity.User;
import com.scaj.db.scaj_db.service.SecurityService;

@RestController
@PropertySource("classpath:${configfile.path}/SCAJ.properties")
@RequestMapping(value="${endpoint.api}", headers = "Accept=application/json")
public class SecurityController {
	
	@Autowired
	private SecurityService securityService;
	
    //LOGIN
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User login(@RequestBody User login) throws Exception {
		return securityService.findByUsernameAndPassword(login.getUsername(), login.getPassword());
	}
	
	//VERIFY TOKEN
	@RequestMapping(value = "/verifyToken", method = RequestMethod.POST)
	public User verifyToken(@RequestBody User user) throws Exception {
		return securityService.verifyToken(user.getToken());
	}
	
	//CHANGE PASSWORD
	@Secured({"ROLE_PRIVATE_USER"})
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public HashMap<String,String> changePassword(@RequestBody Map<String,String> passwords, HttpServletResponse response) throws Exception {
		boolean check = securityService.changePasssword(passwords.get("passwordOld"), passwords.get("passwordNew"), response);
		if(check)
			return new HashMap<String,String>();
		else {
			response.sendError(500);
return null;
	}
	}
}
