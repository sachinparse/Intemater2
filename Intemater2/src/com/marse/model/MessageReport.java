package com.marse.model;

import java.util.List;

public class MessageReport {
	
	private Message objMessage;
	private List<Customer> objlstCustomer;
	
	public Message getObjMessage() {
		return objMessage;
	}
	public void setObjMessage(Message objMessage) {
		this.objMessage = objMessage;
	}
	public List<Customer> getObjlstCustomer() {
		return objlstCustomer;
	}
	public void setObjlstCustomer(List<Customer> objlstCustomer) {
		this.objlstCustomer = objlstCustomer;
	}
	
}
