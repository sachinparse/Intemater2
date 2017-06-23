package com.marse.customer;

import java.util.List;

import com.marse.model.Category;
import com.marse.model.Customer;
import com.marse.model.User;

public interface CustomerDAO {

	public void addCustomer(Customer objCustomer);                // adding new customer details to the DB.
	public Customer getCustomer(int customerId); // getting the details of the Customer of given customer id.
	public void updateCustomer(Customer objCutomer);             // updating an existing customer details of given customerId.
	public void deleteCustomer(int customerId);             // deactivating an existing customer of given customerId.
	public List<Customer> listOfCustomer(int categoryId,int offSet, int noOfRecordsPerPage);   // getting the list of all the customers of selected category.
	public int customerCount(int categoryId);// getting the count of the selected category customers count.
	public List<Customer> listOfAllCustomer(int categoryId);// All customers
	public List<Customer> getCustomerforEmail(Integer[] ids);  // Fetching customer records to send emails which are checked.
	public List<Customer> getCustomers(String receivers); // MessageReport - receivers contains the string of the userId's seperated by ','
}
