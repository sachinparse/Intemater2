package com.marse.message;

import java.util.Date;
import java.util.List;

import com.marse.model.Message;
import com.marse.model.MessageReport;

public interface MessageDAO {
	
	public int saveMessage(Message objMessage);  // It will save the message to the database
	public List<Message> getMessages();    // Fetching the list of all messages
	public void updateReceivers(int msgId, String receivers); // updating email receivers list
	public List<Message> getMessageReport(Date startDate, Date endDate); // Fetching sent message within specified dates
}
