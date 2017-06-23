package com.marse.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.marse.customer.CustomerDAO;
import com.marse.customer.CustomerDAOImpl;
import com.marse.model.Customer;
import com.marse.model.Message;
import com.marse.model.MessageReport;

public class MessageService {
	
	MessageDAO messageDAO=new MessageDAOImpl();
	CustomerDAO customerDAO=new CustomerDAOImpl();
	
	// Fetching the list of Message reports
	public List<MessageReport> getMessageReport(Date startDate, Date endDate){
		
		List<MessageReport> objlstMessageReport=new ArrayList<MessageReport>();
		
		List<Message> objlstMessage=new ArrayList<Message>();
		
		objlstMessage= messageDAO.getMessageReport(startDate, endDate);
		
		Iterator<Message> itMessage=objlstMessage.iterator();
		
		while (itMessage.hasNext()) {
			Message message = (Message) itMessage.next();
			
			List<Customer> objlstCustomer=new ArrayList<Customer>();
			
			String custIds[]=message.getReceivers().split(",");
			
			Integer[] id=new Integer[custIds.length];
			
			for(int i = 0;i < custIds.length;i++)
			{
			   id[i] = Integer.parseInt(custIds[i]);
			}
			
			objlstCustomer=customerDAO.getCustomerforEmail(id);
			
			//objlstCustomer=customerDAO.getCustomers(message.getReceivers());
			
			MessageReport objMessageReport=new MessageReport();

			objMessageReport.setObjMessage(message);
			objMessageReport.setObjlstCustomer(objlstCustomer);
			
			objlstMessageReport.add(objMessageReport);
			
		}// end of while()
		
		return objlstMessageReport;
	}

}
