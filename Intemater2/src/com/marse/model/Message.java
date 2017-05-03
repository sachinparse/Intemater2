package com.marse.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="message")
public class Message {

	@Id
	@GenericGenerator(name="msgIdGenerator", strategy="increment")
	@GeneratedValue(generator="msgIdGenerator")
	@Column()
	private int messageId;
	@Column(length = 65535,columnDefinition="Text")
	private String messageData;
	@Column()
	@Temporal(TemporalType.DATE)
	private Date msgDate;
	
	private String subject;
	
	// setters and getters
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getMessageId() {
		return messageId;
	}

	public Date getMsgDate() {
		return msgDate;
	}
	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
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
