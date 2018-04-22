/**
 * 
 */
package com.hariom.customers.dao;

import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.hariom.customers.model.Customer;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author harmisra
 *
 */
public class CustomerDAOImpl implements CustomerDAO {

	private static final String CUSTOMERS_COLL = "customers";
	CustomersDB cdb = new CustomersDB();
	MongoDatabase myCustomerdb = cdb.getCustomerDB();
	MongoTemplate mongoTemplate = cdb.getMongoTemplate();
	MongoCollection<Document> customerCollection = myCustomerdb.getCollection(CUSTOMERS_COLL);

	
	public CustomerDAOImpl(){
		if(!mongoTemplate.collectionExists(CUSTOMERS_COLL)){
		mongoTemplate.createCollection(CUSTOMERS_COLL);
		}
	}
	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.hariom.customers.dao.CustomerDAO#getAllCustomers()
	 */
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> resultList = mongoTemplate.findAll(Customer.class, CUSTOMERS_COLL);
		return resultList;
	}

	@Override
	public List<Customer> getCustomersByFirstName(String firstName) {
		List<Customer> resultList = mongoTemplate.find(Query.query(Criteria.where("firstName").is(firstName)),
				Customer.class, CUSTOMERS_COLL);
		return resultList;
	}

	@Override
	public List<Customer> getCustomersByLastName(String lastName) {
		List<Customer> resultList = mongoTemplate.find(Query.query(Criteria.where("lastName").is(lastName)),
				Customer.class, CUSTOMERS_COLL);
		return resultList;
	}

	@Override
	public List<Customer> getCustomersByAge(String age) {
		List<Customer> resultList = mongoTemplate.find(Query.query(Criteria.where("age").is(Integer.valueOf(age))),
				Customer.class, CUSTOMERS_COLL);
		return resultList;
	}

	@Override
	public String insertCustomer(Customer customer) {
		try {
			//int custId = getMaxCustomerId(customer);
			//customer.setCustomerId(custId);
			mongoTemplate.save(customer, CUSTOMERS_COLL);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
/**
 * 
 * @param customer
 * @return
 */
	private int getMaxCustomerId(Customer customer) {
		Query query = new Query();
		//query.addCriteria(Criteria.where("customerId").gt("0"));
		query.with(new Sort(Sort.Direction.DESC, "customerId"));
		query.limit(1);
		Customer maxObject = mongoTemplate.findOne(query, Customer.class);
		if (Integer.valueOf(customer.getCustomerId()) > Integer.valueOf(maxObject.getCustomerId())) {
			return customer.getCustomerId();
		} else {
			return maxObject.getCustomerId();
		}
	}

	@Override
	public String updateCustomer(Customer customer) {
		String status = "failure";
		try {
			Query query = getCriteria(customer);
			Customer updatedCustomer = mongoTemplate.findAndModify(query,Update.update("customer", customer), Customer.class, CUSTOMERS_COLL);
			if (null != updatedCustomer) {
				status = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String deleteCustomer(Customer customer) {
		String status = "failure";
		try {
			Customer deletedCustomer = mongoTemplate.findAndRemove(
					Query.query(Criteria.where("customerId").is(Integer.valueOf(customer.getCustomerId()))), Customer.class,
					CUSTOMERS_COLL);
			if (null != deletedCustomer) {
				status = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	@Override
	public List<Customer> getCustomersByEmail(String email) {
		List<Customer> resultList = mongoTemplate.find(Query.query(Criteria.where("email").is(Integer.valueOf(email))),
				Customer.class, CUSTOMERS_COLL);
		return resultList;
	}
	@Override
	public List<Customer> getCustomersByCustomerId(String customerId) {
		List<Customer> resultList = mongoTemplate.find(Query.query(Criteria.where("customerId").is(Integer.valueOf(customerId))),
				Customer.class, CUSTOMERS_COLL);
		return resultList;
	}

	private Query getCriteria(Customer customer) {
		Query query = null;
		if(null != customer.getFirstName()){
			query = Query.query(Criteria.where("firstName").is(customer.getFirstName()));
		}
		else if(null != customer.getLastName()){
			query = Query.query(Criteria.where("lastName").is(customer.getLastName()));
		}
		else if(null != customer.getGender()){
			query = Query.query(Criteria.where("gender").is(customer.getGender()));
		}
		else if(null != customer.getWork()){
			query = Query.query(Criteria.where("work").is(customer.getWork()));
		}
		else if(0 != customer.getAge()){
			query = Query.query(Criteria.where("age").is(Integer.valueOf(customer.getAge())));
		}
		return query;
	}
}
