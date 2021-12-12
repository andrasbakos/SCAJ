package com.scaj.controller.base;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.bson.types.ObjectId;
import org.springframework.security.access.annotation.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import com.scaj.db.scaj_db.service.FoodEntryService;
import com.scaj.db.scaj_db.entity.FoodEntry;

//IMPORT RELATIONS
import com.scaj.db.scaj_db.entity.User;

public class FoodEntryBaseController {
    
    @Autowired
	FoodEntryService foodentryService;



//CRUD METHODS


    //CRUD - CREATE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/foodentry",method = RequestMethod.POST, headers = "Accept=application/json")
	public FoodEntry insert(@RequestBody FoodEntry obj) {
		 return foodentryService.insert(obj);
	}

	
    //CRUD - REMOVE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/foodentry/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void delete(@PathVariable("id") String id) {
		foodentryService.delete(id);
	}
	
	
    //CRUD - GET ONE
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/foodentry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public FoodEntry get(@PathVariable String id) {
		return foodentryService.get(id);
	}
	
	
    //CRUD - GET LIST
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/foodentry", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<FoodEntry> getList() {
		return foodentryService.getList();
	}
	
	

    //CRUD - EDIT
    @Secured({ "ROLE_PRIVATE_USER" })
	@RequestMapping(value = "/foodentry/{id}", method = RequestMethod.POST, headers = "Accept=application/json")
	public FoodEntry update(@RequestBody FoodEntry obj) {
		return foodentryService.update(obj);
	}
	


/*
 * CUSTOM SERVICES
 * 
 *	These services will be overwritten and implemented in  Custom.js
 */


	
}
