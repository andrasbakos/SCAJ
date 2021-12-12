package com.scaj.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.bson.types.ObjectId;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.scaj.db.scaj_db.service.UserService;
import com.scaj.db.scaj_db.entity.User;

//IMPORT RELATIONS
import com.scaj.db.scaj_db.entity.User;

public class UserBaseController {
    
    @Autowired
	UserService userService;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/user",method = RequestMethod.POST, headers = "Accept=application/json")
	public User insert(@RequestBody User obj) {
		 return userService.insert(obj);
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") String id) {
		userService.delete(id);
	}
	
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public User get(@PathVariable String id) {
		return userService.get(id);
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/user", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<User> getList() {
		return userService.getList();
	}
	
	

    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public User update(@RequestBody User obj) {
		return userService.update(obj);
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


    /*
    Name: changePassword
    Description: Change password of user from admin
    Params: 
    
    
    @RequestMapping(value = "/user/{id}/changePassword", method = RequestMethod.POST, headers = "Accept=application/json")
    public Object changePassword() {
		return new HashMap<String, String>();
    }
    */
    		

	
}
