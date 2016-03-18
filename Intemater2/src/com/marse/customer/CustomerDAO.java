package com.marse.customer;

import java.util.List;

import com.marse.model.Customer;

public interface CustomerDAO {

	public void addCustomer(Customer objCustomer);                // adding new customer details to the DB.
	public void updateCustomer();             // updating an existing customer details of given customerId.
	public void deleteCustomer();             // deactivating an existing customer of given customerId.
	public List<Customer> listOfCustomer();   // getting the list of all the customers.
	public List<Customer> listOfCatagory();   // getting the list of all the distinct categories of the customer.
	
	
	
}
