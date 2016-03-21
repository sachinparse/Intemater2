package com.marse.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.marse.crypto.CryptoUtil;
import com.marse.hibernate.util.HibernateUtils;
import com.marse.model.Customer;
import com.marse.model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean authenticateUser(int userId, String password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
	                                                             NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException,
	                                                             IllegalBlockSizeException, BadPaddingException, IOException {

		boolean loginStatus=false;
		
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		User objUser=(User) session.get(User.class, userId); 
		if (null != objUser) {    // this condition for whether the user is exist or not
			
			//if (objUser.getUserPassword().equals(password)) {
			if (new CryptoUtil().decrypt(objUser.getUserPassword()).equals(password)) {
				
				loginStatus=true;
			} else {
				loginStatus=false;
	
			}
		}
		
		return loginStatus;
	}
	
	@Override
	public User getUser(int userId) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		User objUser=(User) session.get(User.class, userId); 
		
		return objUser;
	}

	
	@Override
	public void addUser(User objUser) {
		
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		
		session.save(objUser);
		tx.commit();
		session.close();
		
	}

	@Override
	public void deleteUser() {

	}

	@Override
	public void changePassword() {

	}

	public List<User> listOfUser(){
		
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		// SELECT u.userId,u.firstName,u.lastName,u.userEmail,u.userMobile1,u.userMobile2,u.roll,u.userGender From User u
		String hqlQuery="From User u";
		
		Query query=session.createQuery(hqlQuery);
		
		List<User> list=query.list();
		
		return list;
	
	}
}
