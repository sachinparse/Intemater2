package com.marse.message;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.marse.hibernate.util.HibernateUtils;
import com.marse.model.Message;

public class MessageDAOImpl implements MessageDAO {

	@Override
	public int saveMessage(Message objMessage) {

		int msgId=0;
		SessionFactory factory=HibernateUtils.getInstance();
		Session session=factory.openSession();
		Transaction tx=session.beginTransaction();
		
		session.save(objMessage);
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

}
