package com.marse.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/*
 * Project Name : Intemater
 * Author : Mr. Sachin Parse
 * Mobile: 9028702543
 * Email Id : sachin.parse@gmail.com
 * Details : All the details of the customers will be processed through the following members
 * 
 */

@Entity
@Table(name="customer")
public class Customer {

	// personal details
	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	@Column()
	private int custId;

	@Column()
	private String name;
	@Column()
	private String mobile1;
	@Column()
	private String mobile2;
	@Column()
	private String work;
	@Column()
	private String email;
	@Column()
	private String pan;
	@Column()
	private Date dob;
	@Column()
	private String address;


	// bank details

	/*private Bank bank;
	
	public Customer(Bank bank) {
		super();
		this.bank = bank;
	}*/

	@Column()
	private String bankName;
	@Column()
	private String bankBranch;
	@Column()
	private String acc;
	@Column()
	private String ifsc;
	@Column()
	private String micr;
	@Column()
	private String catagory;

	// flag for Active / Inactive customer
	@Column(length=1)
	private String status;   // this filed will be used for to determine the active or deactive customer for service



	// setters and getters

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getMicr() {
		return micr;
	}

	public void setMicr(String micr) {
		this.micr = micr;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}




}
