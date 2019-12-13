/**
 * 
 */
package com.hariom.customers.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.hariom.customers.dao.CustomerDAO;
import com.hariom.customers.dao.CustomerDAOImpl;
import com.hariom.customers.model.Customer;

import io.swagger.annotations.Api;

/**
 * @author harmisra
 *
 */

@Controller
@RequestMapping("/customers")
@Api(value="onlinecustomermgmt", description="Operations pertaining to customer management") 
public class CustomerOperations {
	
	@ResponseBody
	@RequestMapping(value = "/showall", method = GET, produces = "application/json")
	public String getCustomerByFirstName() {;
		CustomerDAO dao = new CustomerDAOImpl();
		List<Customer> customers = dao.getAllCustomers();
		if(null == customers){
			return "{\"status\":\"nocusotmerfound\"}";
		}else {
			Gson gson = new Gson();
			 return gson.toJson(customers).toString();
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/test", method = GET, produces = "application/json")
	public String getTest() {
			return "{\"status\":\"this is test\"}";
	}
	

	@ResponseBody
	@RequestMapping(value = "/firstname", method = GET, produces = "application/json")
	public String getCustomerByFirstName( @RequestParam("firstName") String firstName) {;
		CustomerDAO dao = new CustomerDAOImpl();
		List<Customer> customers = dao.getCustomersByFirstName(firstName);
		if(null == customers){
			return "{\"status\":\"nocusotmerfound\"}";
		}else {
			Gson gson = new Gson();
			 return gson.toJson(customers).toString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/lastname", method = GET, produces = "application/json")
	public String getCustomerByLastName( @RequestParam("lastName") String lastName) {;
		CustomerDAO dao = new CustomerDAOImpl();
		List<Customer> customers = dao.getCustomersByLastName(lastName);
		if (null == customers || customers.isEmpty()) {
			return "{\"status\":\"nocusotmerfound\"}";
		} else {
			Gson gson = new Gson();
			return gson.toJson(customers).toString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/age", method = GET, produces = "application/json")
	public String getCustomerByAge(@RequestParam("age") String age) {
		;
		CustomerDAOImpl dao = new CustomerDAOImpl();
		List<Customer> customers = dao.getCustomersByAge(age);
		if (null == customers || customers.isEmpty()) {
			return "{\"status\":\"nocusotmerfound\"}";
		} else {
			Gson gson = new Gson();
			return gson.toJson(customers).toString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/email", method = GET, produces = "application/json")
	public String getCustomerByEmail(@RequestParam("email") String email) {
		;
		CustomerDAOImpl dao = new CustomerDAOImpl();
		List<Customer> customers = dao.getCustomersByEmail(email);
		if (null == customers || customers.isEmpty()) {
			return "{\"status\":\"nocusotmerfound\"}";
		} else {
			Gson gson = new Gson();
			return gson.toJson(customers).toString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/id", method = GET, produces = "application/json")
	public String getCustomerByCustomerId(@RequestParam("customerId") String customerId) {
		;
		CustomerDAOImpl dao = new CustomerDAOImpl();
		List<Customer> customers = dao.getCustomersByCustomerId(customerId);
		if (null == customers || customers.isEmpty()) {
			return "{\"status\":\"nocusotmerfound\"}";
		} else {
			Gson gson = new Gson();
			return gson.toJson(customers).toString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/addcustomer", method = POST, produces = "application/json")
	public String addCustomer(@RequestBody Customer customer) {
		CustomerDAO dao = new CustomerDAOImpl();
		String status = dao.insertCustomer(customer);
		return "{\"status\":\"" + status + "\"}";
	}

	@ResponseBody
	@RequestMapping(value = "/updatecustomer", method = PUT, produces = "application/json")
	public String UpdateCustomerByFirstName(@RequestBody Customer customer) {
		CustomerDAO dao = new CustomerDAOImpl();
		String status = dao.updateCustomer(customer);
		return "{\"status\":\"" + status + "\"}";
	}

	@ResponseBody
	@RequestMapping(value = "/deletecustomer", method = DELETE, produces = "application/json")
	public String deleteCustomerByFirstName(@RequestBody Customer customer) {
		CustomerDAO dao = new CustomerDAOImpl();
		String status = dao.deleteCustomer(customer);
		return "{\"status\":\"" + status + "\"}";
	}
}
