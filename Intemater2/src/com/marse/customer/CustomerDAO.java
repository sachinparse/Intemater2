package com.marse.customer;

import java.util.List;

import com.marse.model.Category;
import com.marse.model.Customer;

public interface CustomerDAO {

	public void addCustomer(Customer objCustomer);                // adding new customer details to the DB.
	public void updateCustomer();             // updating an existing customer details of given customerId.
	public void deleteCustomer();             // deactivating an existing customer of given customerId.
	public List<Customer> listOfCustomer(int categoryId,int offSet, int noOfRecordsPerPage);   // getting the list of all the customers of selected category.
	public int customerCount(int categoryId);// getting the count of the selected category customers count.
	
	
}
