package com.scaj.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.scaj.db.scaj_db.entity.User;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Profile({"DEV"})
public class SwaggerConfiguration {
    
	@Value( "${token.secret}" )
	private String secret;
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.scaj.controller"))
				.paths(regex("/.*")).build();

	}


	@Bean
    SecurityConfiguration security() throws IllegalArgumentException, UnsupportedEncodingException {

	    User user = new User();
	    user.setUsername("admin");
	    user.setPassword("62f264d7ad826f02a8af714c0a54b197935b717656b80461686d450f7b3abde4c553541515de2052b9af70f710f0cd8a1a2d3f4d60aa72608d71a63a9a93c0f5");
	    
		Algorithm algorithm;
		Gson gson = new Gson();
		algorithm = Algorithm.HMAC256(secret);
		Date date = new Date();
		long t = date.getTime();
		Date expirationTime = new Date(t + 28800000l);
		String token = JWT.create().withSubject(gson.toJson(user )).withExpiresAt(expirationTime)
	    .withClaim("user",gson.toJson(user))
	    .sign(algorithm);    	
	    
        return new SecurityConfiguration("","","","","Bearer " + token ,ApiKeyVehicle.HEADER,"Authorization","");
    }
    
}
