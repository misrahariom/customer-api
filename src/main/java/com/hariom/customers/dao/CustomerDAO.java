/**
 * 
 */
package com.hariom.customers.dao;

import java.util.List;

import org.bson.Document;

import com.hariom.customers.model.Customer;
import com.mongodb.client.FindIterable;

/**
 * @author harmisra
 *
 */
public interface CustomerDAO {

	public List<Customer> getAllCustomers();

	public List<Customer> getCustomersByFirstName(String firstName);

	public List<Customer> getCustomersByLastName(String lastName);

	public List<Customer> getCustomersByAge(String age);
	public List<Customer> getCustomersByEmail(String email);

	public List<Customer> getCustomersByCustomerId(String id);

	public String insertCustomer(Customer customer);
	public String updateCustomer(Customer customer);

	public String deleteCustomer(Customer customer);

}
