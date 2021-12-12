package com.scaj.config;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@Profile("PROD")
@PropertySource("classpath:${configfile.path}/prod.properties")
public class ProdConfig {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${spring.data.mongodb.uri}")
	private String connectionuri;
	
	@Value("${spring.data.mongodb.database}")
	private String databasename;
	
	@Autowired
	private MongoProperties mongoProperties;

	private Morphia morphia() {
		final Morphia morphia = new Morphia();
		morphia.mapPackage("com.scaj.entity");
		return morphia;
	}

	@Bean
	public Datastore datastore(MongoClient mongoClient) {
		final Datastore datastore = morphia().createDatastore(new MongoClient(new MongoClientURI(connectionuri)), databasename);
		datastore.ensureIndexes();

		return datastore;
	}
}
