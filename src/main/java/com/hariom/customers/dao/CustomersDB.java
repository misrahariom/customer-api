/**
 * 
 */
package com.hariom.customers.dao;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author harmisra
 *
 */
public class CustomersDB {
	private static final String DB_NAME = "mycustomer";
	static MongoClient mongoClient = null;
	static MongoDatabase myCustomerdb = null;
	static MongoTemplate mongoTemplate = null;

	public CustomersDB() {
		getDBConnection();
	}

	private static void getDBConnection() {
		mongoClient = new MongoClient("customers-mongo", 27017);
		myCustomerdb = mongoClient.getDatabase(DB_NAME);
		mongoTemplate = new MongoTemplate(mongoClient, DB_NAME);
	}

	public MongoDatabase getCustomerDB() {
		return myCustomerdb;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
}
