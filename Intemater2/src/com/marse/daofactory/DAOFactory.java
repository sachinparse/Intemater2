package com.marse.daofactory;

import com.marse.category.CategoryDAO;
import com.marse.category.CategoryDAOImpl;
import com.marse.credentials.EmailCredentialsDAO;
import com.marse.credentials.EmailCredentialsDAOImpl;
import com.marse.customer.CustomerDAO;
import com.marse.customer.CustomerDAOImpl;
import com.marse.message.MessageDAO;
import com.marse.message.MessageDAOImpl;
import com.marse.user.UserDAO;
import com.marse.user.UserDAOImpl;

public class DAOFactory {

	public static CustomerDAO getInstanceOfCustomer(){   // returns object of the CustomerDAOImpl class
		
		return new CustomerDAOImpl();
	}
	
	public static UserDAO getInstanceOfUser(){     // returns object of the UserDAOImpl class
		
		return new UserDAOImpl();
	}
	
	public static CategoryDAO getInstancOfCategory(){
		
		return new CategoryDAOImpl();    // returns object of the CategoryDAOImpl class
	}
	
	public static MessageDAO getInstancOfMessage(){
		
		return new MessageDAOImpl();    // returns object of the MessageDAOImpl class
	}
	
	public static EmailCredentialsDAO getInstanceOfEmailCredentials(){
		return new EmailCredentialsDAOImpl();
	}
}
