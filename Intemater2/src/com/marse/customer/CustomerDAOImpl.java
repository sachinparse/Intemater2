package com.marse.customer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.marse.hibernate.util.HibernateUtils;
import com.marse.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public void addCustomer(Customer objCustomer) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		Transaction tx=session.beginTransaction();
		
		session.save(objCustomer);
		System.out.println("Customer saved ID: "+objCustomer.getCustId());
		tx.commit();
		session.close();

	}

	@Override
	public void updateCustomer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCustomer() {
		// TODO Auto-generated method stub

	}

	// fetching the list of all customers
	@Override
	public List<Customer> listOfCustomer(String categoryName) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		String hqlQuery="From Customer c WHERE c.category='"+categoryName+"'";
		
		Query query=session.createQuery(hqlQuery);
		
		List<Customer> list=query.list();
		
		return list;
	}

	/*@Override
	public List<Customer> listOfCatagory() {
		// TODO Auto-generated method stub
		return null;
	}*/

}
