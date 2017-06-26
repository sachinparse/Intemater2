package com.marse.credentials;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.marse.hibernate.util.HibernateUtils;
import com.marse.model.Customer;
import com.marse.model.EmailCredentials;

public class EmailCredentialsDAOImpl implements EmailCredentialsDAO {

	@Override
	public EmailCredentials getCredentials(int credentialsId) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		Object objEmailCredentials=session.get(EmailCredentials.class, credentialsId); 
		
		EmailCredentials emailCredentials=new EmailCredentials();
		emailCredentials= (EmailCredentials)objEmailCredentials;
		
		return emailCredentials;
	}

}
