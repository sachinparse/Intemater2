package com.marse.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.marse.hibernate.util.HibernateUtils;
import com.marse.model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean authenticateUser(int userId, String password) {

		boolean loginStatus=false;
		
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		User objUser=(User) session.get(User.class, userId); 
		if (null != objUser) {    // this condition for whether the user is exist or not
			
			if (objUser.getUserPassword().equals(password)) {
				
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
	public void addUser() {

	}

	@Override
	public void deleteUser() {

	}

	@Override
	public void changePassword() {

	}

}
