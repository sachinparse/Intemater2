package com.marse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="credentials")
public class EmailCredentials {
	
	@Id
	@GenericGenerator(name="credentialsIdGenerator", strategy="increment")
	@GeneratedValue(generator="credentialsIdGenerator")
	@Column()
	private int credentialsId;
	@Column()
	private String emailId;
	@Column()
	private String password;
	
	
	public int getCredentialsId() {
		return credentialsId;
	}
	public void setCredentialsId(int credentialsId) {
		this.credentialsId = credentialsId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
