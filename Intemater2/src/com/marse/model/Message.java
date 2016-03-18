package com.marse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="message")
public class Message {

	@Id
	@GenericGenerator(name="msgIdGenerator", strategy="increment")
	@GeneratedValue(generator="msgIdGenerator")
	@Column()
	private int messageId;
	@Column()
	private String messageData;
	
	
	// setters and getters
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getMessageData() {
		return messageData;
	}
	public void setMessageData(String messageData) {
		this.messageData = messageData;
	}
	
	
}
