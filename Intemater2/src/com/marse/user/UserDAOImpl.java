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

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.marse.crypto.CryptoUtil;
import com.marse.hibernate.util.HibernateUtils;
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
			
			if (new CryptoUtil().decrypt(objUser.getUserPassword()).equals(password) && objUser.getUserStatus().equals("A")) {
				
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
	public void deleteUser(int userId) {
		
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
			String hql="UPDATE User u SET u.userStatus= :status WHERE u.userId= :userId";

			int updatedRow=session.createQuery(hql)
						.setString("status", "I")
						.setInteger("userId",userId)
						.executeUpdate();
			System.out.println("Rows updated from deleteUser() count : "+updatedRow);
			tx.commit();
			session.close();
	}

	@Override
	public void changePassword() {

	}

	public List<User> listOfUser(int offSet, int records){
		
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		// SELECT u.userId,u.firstName,u.lastName,u.userEmail,u.userMobile1,u.userMobile2,u.roll,u.userGender From User u
		String hqlQuery="From User u";
		
		Query query=session.createQuery(hqlQuery);
		query.setFirstResult(offSet);
		query.setMaxResults(records);
		List<User> list=query.list();
		
		return list;
	
	}

	@Override
	public void updateUser(User objUser) {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		
		session.saveOrUpdate(objUser);
		
		tx.commit();
		session.close();
		
	}

	@Override
	public int userCount() {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		String hqlQuery="SELECT count(*) From User u";
		
		long count = (Long)session.createQuery(hqlQuery).uniqueResult();
		
		int cnt=(int) count;
		
		System.out.println("User count: "+count);
		return cnt;
	}

	@Override
	public boolean changePassword(User objUser, String userOldPassword, String userNewPassword) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
    							  NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException,
    							  IllegalBlockSizeException, BadPaddingException, IOException {

		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		User objUser2=(User) session.get(User.class, objUser.getUserId()); 
		if (new CryptoUtil().decrypt(objUser2.getUserPassword()).equals(userOldPassword) && objUser2.getUserStatus().equals("A")) {
			
			// old password matched.
			
			objUser2.setUserPassword(new CryptoUtil().encrypt(userNewPassword));
			
			new UserDAOImpl().updateUser(objUser2);
			
			return true;
			
		} else {
			return false;
		}
		
	}
}
