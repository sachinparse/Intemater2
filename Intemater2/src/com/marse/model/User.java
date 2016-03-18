package com.marse.model;

/*
 * Project Name : Intemater
 * Author : Mr. Sachin Parse
 * Mobile: 9028702543
 * Email Id : sachin.parse@gmail.com
 * Details : This class is used for the login purpose.
 *           Only the 'Admin' has the roll to add or delete an user.
 * 
 */



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="user")
public class User {

	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	@Column()
	private int userId;
	@Column()
	private String firstName;
	@Column()
	private String lastName;
	@Column()
	private String userPassword;
	@Column()
	private String roll;
	@Column()
	private String userMobile;
	@Column()
	private String userEmail;
	
	
	// setters and getters
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	
	
}
