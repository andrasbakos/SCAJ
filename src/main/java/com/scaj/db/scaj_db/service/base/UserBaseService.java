package com.scaj.db.scaj_db.service.base;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scaj.db.scaj_db.service.SecurityService;
import com.scaj.db.scaj_db.entity.User;

//IMPORT RELATIONS
import com.scaj.db.scaj_db.entity.User;

@Service
public class UserBaseService {

	
	@Autowired
	private Datastore datastore;
	@Autowired
	private SecurityService securityService;


    //CRUD METHODS
    
    	
        //CRUD - CREATE
    	
	public User insert(User obj) {
		datastore.save(obj);
		return obj;
	}
	
    	
    //CRUD - REMOVE
    
	public void delete(String id) {
		Query<User> query = datastore.createQuery(User.class).field("_id").equal(new ObjectId(id));
		datastore.delete(query);
	}

    	
    //CRUD - GET ONE
    	
	public User get(String id) {
		return datastore.find(User.class).field("_id").equal(new ObjectId(id)).get();
	}

    	
        	
    //CRUD - GET LIST
    	
	public List<User> getList() {
		return (ArrayList<User>) datastore.find(User.class).asList();
	}

    	
    //CRUD - EDIT
    	
	public User update(User obj) {
		datastore.save(obj);
		return obj;
	}
	
    	
    
    

    
    /*
     * CUSTOM SERVICES
     * 
     *	These services will be overwritten and implemented in UserService.java
     */
    
    
    /*
    
    YOU CAN COPY AND MODIFY THIS METHOD IN UserService.java
    
    Name: changePassword
    Description: Change password of user from admin
    Params: 
    */
	public Object changePassword () {
		
        return null;
        
	}
	
	
    public int countUsers() {
		return (int)datastore.createQuery(User.class).count();
	}
	
	public void newUser() {
		User user = new User();
		user.setUsername("admin");
        user.setPassword("62f264d7ad826f02a8af714c0a54b197935b717656b80461686d450f7b3abde4c553541515de2052b9af70f710f0cd8a1a2d3f4d60aa72608d71a63a9a93c0f5");
		ArrayList<String> roles = new ArrayList<>();
		roles.add("ADMIN");
		user.setRoles(roles);
		insert(user);
	}
	
	public boolean changePassword(String id, String passwordAdmin, String passwordNew) throws Exception {
		User admin = securityService.findUserByUsername("admin");
		User user = get(id);
		if(admin == null)
			return false;
		if(user == null)
			return false;
		if(admin.getPassword().equals(passwordAdmin)) {
			user.setPassword(passwordNew);
			update(user);
			return true;
		}
		return false;
	}


}
