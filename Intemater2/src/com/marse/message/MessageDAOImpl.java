package com.marse.message;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.marse.hibernate.util.HibernateUtils;
import com.marse.model.Message;

public class MessageDAOImpl implements MessageDAO {

	@Override
	public int saveMessage(Message objMessage) {

		System.out.println(objMessage.toString());
		
		int msgId=0;
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		
		//session.save(objMessage);
		session.saveOrUpdate(objMessage);
		msgId=objMessage.getMessageId();
		tx.commit();
		session.close();
		System.out.println("Message ID : "+ msgId);
		return msgId;
	}

	@Override
	public List<Message> getMessages() {

		
		return null;
	}

	@Override
	public void updateReceivers(int msgId, String receivers) {

		/*Message objMessage=new Message();
		objMessage.setMessageId(msgId);
		objMessage.setReceivers(receivers);*/
		
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		Transaction tx=session.beginTransaction();
		/*session.update(objMessage);
		tx.commit();
		session.close();*/

		String hql = "UPDATE Message set receivers = :ids WHERE messageId = :mid";
		Query query = session.createQuery(hql);
		query.setParameter("ids", receivers);
		query.setParameter("mid", msgId);
		int result = query.executeUpdate();
		tx.commit();
		session.close();
		System.out.println("Rows affected: " + result);
		
		
	}

	//@SuppressWarnings("unchecked")
	public List<Message> getMessageReport(Date startDate, Date endDate){
		
		List<Message> objlstMessage= new ArrayList<Message>();
		
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
	    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date sDate = null;
	    Date eDate = null;
	    
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String start = formatter.format(startDate);
	    String end = formatter.format(endDate);
	    System.out.println(start);
	    System.out.println(end);
	    
		 try {
		     sDate = sdf.parse(start);
		     eDate = sdf.parse(end);
		 } catch (ParseException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		 }
     
		/*objlstMessage = session.createQuery("FROM Message AS m WHERE m.msgDate BETWEEN :stDate AND :edDate ")
			.setParameter("stDate", sdf.format(sDate))
			.setParameter("edDate", sdf.format(eDate))
			.list();*/
		 
		 String hql ="FROM Message AS m WHERE m.msgDate BETWEEN '"+sdf.format(sDate)+"' AND '"+sdf.format(eDate)+"'";
		 
		 Query query=session.createQuery(hql);
		 
		 objlstMessage=(List<Message>)query.list();
		 
		return objlstMessage;
	}
}
