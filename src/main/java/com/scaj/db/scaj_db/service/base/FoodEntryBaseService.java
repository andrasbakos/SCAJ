package com.scaj.db.scaj_db.service.base;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scaj.db.scaj_db.entity.FoodEntry;

//IMPORT RELATIONS
import com.scaj.db.scaj_db.entity.User;

@Service
public class FoodEntryBaseService {

	
	@Autowired
	private Datastore datastore;


    //CRUD METHODS
    
    //CRUD - CREATE
    	
	public FoodEntry insert(FoodEntry obj) {
		datastore.save(obj);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(String id) {
		Query<FoodEntry> query = datastore.createQuery(FoodEntry.class).field("_id").equal(new ObjectId(id));
		datastore.delete(query);
	}

    	
    //CRUD - GET ONE
    	
	public FoodEntry get(String id) {
		return datastore.find(FoodEntry.class).field("_id").equal(new ObjectId(id)).get();
	}

    	
        	
    //CRUD - GET LIST
    	
	public List<FoodEntry> getList() {
		return (ArrayList<FoodEntry>) datastore.find(FoodEntry.class).asList();
	}

    	
    //CRUD - EDIT
    	
	public FoodEntry update(FoodEntry obj) {
		datastore.save(obj);
		return obj;
	}
	
    	
    
    

    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in FoodEntryService.java
     */
    


}
