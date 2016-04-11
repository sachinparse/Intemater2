package com.marse.daofactory;

import com.marse.category.CategoryDAO;
import com.marse.category.CategoryDAOImpl;
import com.marse.customer.CustomerDAO;
import com.marse.customer.CustomerDAOImpl;
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
	
}
