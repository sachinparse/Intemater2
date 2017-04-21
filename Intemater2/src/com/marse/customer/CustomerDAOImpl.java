package com.marse.customer;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.marse.hibernate.util.HibernateUtils;
import com.marse.model.Category;
import com.marse.model.Customer;
import com.marse.model.User;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public void addCustomer(Customer objCustomer) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		Transaction tx=session.beginTransaction();
		
		//session.save(objCustomer);
		session.saveOrUpdate(objCustomer);
		System.out.println("Customer saved ID: "+objCustomer.getCustId());
		tx.commit();
		session.close();

	}


	@Override
	public void updateCustomer(Customer objCutomer) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		Transaction tx=session.beginTransaction();
		session.update(objCutomer);
		tx.commit();
		session.close();
		
	}
	
	

	@Override
	public void deleteCustomer(int customerId) {
		

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
			String hql="UPDATE Customer c SET c.status= :status WHERE c.custId= :customerId";

			int updatedRow=session.createQuery(hql)
						.setString("status", "I")
						.setInteger("customerId",customerId)
						.executeUpdate();
			System.out.println("Rows updated from deleteCustomer() count : "+updatedRow);
			tx.commit();
			session.close();

	}

	// fetching the list of all the distinct categories
	@Override
	public List<Customer> listOfCustomer(int categoryId, int offSet, int noOfRecordsPerPage) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		System.out.println("Category :"+categoryId+", OffSet :"+offSet+", No. of Records/page :"+noOfRecordsPerPage);
		
		String hqlQuery="From Customer c WHERE c.category="+categoryId+"";
		
		Query query=session.createQuery(hqlQuery);
		query.setFirstResult(offSet);
		query.setMaxResults(noOfRecordsPerPage);
		List<Customer> list=query.list();
		
		/*Query query=session.createQuery(hqlQuery);
		
		List<Customer> list=query.list();*/
		
		return list;
	}

	@Override
	public int customerCount(int categoryId) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		String hqlQuery="SELECT count(*) From Customer c";
		
		long count = (Long)session.createQuery(hqlQuery).uniqueResult();
		
		int cnt=(int) count;
		
		System.out.println("Customer count: "+count);
		return cnt;
	}

	@Override
	public Customer getCustomer(int customerId) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		Object objCustomer=session.get(Customer.class, customerId); 
		
		Customer customer=new Customer();
		
		customer= (Customer)objCustomer;
		
		System.out.println(customer.toString());
		
		return customer;
	}


	@Override
	public List<Customer> listOfAllCustomer(int categoryId) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		String hqlQuery="From Customer c WHERE c.category="+categoryId+"";
		
		Query query=session.createQuery(hqlQuery);
		List<Customer> list=query.list();
		
		
		return list;
	}

}
