package com.marse.sendemail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.marse.model.Customer;

public class EmailService {

	
	public List<Customer> sendEmail(List<Customer> objlstCustomer, String subject, String messageBody){

		   String temp="Mail to be Send : <font color='blue'>"+objlstCustomer.size()+"</font> ";
		
		   List<Customer> templstCustomer=new ArrayList<Customer>();
		   
		   // Sender's email ID needs to be mentioned
		   String from = "sachin.learning007@gmail.com";
		   final String username = "sachin.learning007@gmail.com";//change accordingly
		   final String password = "micromax25";//change accordingly
		   // Assuming you are sending email through relay.jangosmtp.net
		   String port="587";
		   String host = "smtp.gmail.com";
		   
		   //Properties props = new Properties();
		   Properties props = System.getProperties();
		   //props.put("mail.smtp.auth", "true");
		   //props.put("mail.smtp.starttls.enable", "true");
		   //props.put("mail.smtp.host", host);
		   //props.put("mail.smtp.port", "25");
		   
		   
		   props.setProperty("mail.transport.protocol", "smtp"); 
		   props.setProperty("mail.host", host); 
		   
		   props.put("mail.smtp.auth", "true"); 
		   props.put("mail.smtp.port", port);
		   props.setProperty("mail.user", username); 
		   props.setProperty("mail.password", password); 
		   
		   props.put("mail.smtp.starttls.enable", "true");   // Updated by Sachin Dated : 10122016
		   //props.put("mail.smtp.ssl.trust", "mail.india.techspeed.com");
		   
		   //Session session = Session.getInstance(props,null);
		   Session session = Session.getDefaultInstance(props, null); 
		   session.setDebug(true);
		   
		   int success=0;
		   int failed=0;
		   
		   Iterator<Customer> it= objlstCustomer.iterator();
		   
		   while (it.hasNext()) {
			   
			  Customer customer = (Customer) it.next();
			 
			  String to = customer.getEmail();
					   
					   
			   try {
			         // Create a default MimeMessage object.
			         Message message = new MimeMessage(session);
	
			         // Set From: header field of the header.
			         message.setFrom(new InternetAddress(from));
	
			         // Set To: header field of the header.                                             
	      
			
			           
					System.out.println("Has Own Email Id !");
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			         // Set Subject: header field
			         message.setSubject(subject);
	
			         // Create the message part
			         BodyPart messageBodyPart = new MimeBodyPart();
	
			         // Now set the actual message
			         messageBodyPart.setText(messageBody);
	
			         // Create a multipar message
			         Multipart multipart = new MimeMultipart();
	
			         // Set text message part
			         multipart.addBodyPart(messageBodyPart);
	
			         // Part two is attachment
			         messageBodyPart = new MimeBodyPart();
			        
			         // Send the complete message parts
			         message.setContent(multipart);
	
			         // Send message
			         //Transport.send(message);
			         
			         Transport transport = session.getTransport("smtp");
			         
			         transport.connect(host, username,password); 
			         transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO)); 
			         transport.close(); 
			         
			         
			         success++;
			         System.out.println("Sent message successfully....");
			         
			         customer.setEmailSent("Y");
			         
			      }catch (MessagingException e) {
			         e.getMessage();
			         e.printStackTrace();
			         failed++;
			         customer.setEmailSent("N");
			    	 //throw new RuntimeException(e);
			      }catch (Exception e) {
			         e.getMessage();
			         e.printStackTrace();
			         failed++;
			         customer.setEmailSent("N");
			    	 //throw new RuntimeException(e);
				  }
			
			   templstCustomer.add(customer);
		   }// end while()
		   
		   temp=temp+"\n,  Successful : <font color='green'>"+success+" </font> ,  Failed : <font color='red'>"+failed+"</font>";
		   
		   new EmailStats().setReply(temp);
		   
		   return templstCustomer;
	}
	
}
