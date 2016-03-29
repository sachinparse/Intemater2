package com.marse.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="bank")
public class Bank {
	
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
	
	
	// setters and getters
	
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
	
	

}
