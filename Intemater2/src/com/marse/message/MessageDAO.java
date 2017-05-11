package com.marse.message;

import java.util.List;

import com.marse.model.Message;

public interface MessageDAO {
	
	public int saveMessage(Message objMessage);  // It will save the message to the database
	public List<Message> getMessages();    // Fetching the list of all messages
	public void updateReceivers(int msgId, String receivers); // updating email receivers list

}
