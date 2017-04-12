package com.marse.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.marse.category.CategoryDAO;
import com.marse.crypto.CryptoUtil;
import com.marse.customer.CustomerDAO;
import com.marse.daofactory.DAOFactory;
import com.marse.message.MessageDAO;
import com.marse.message.MessageDAOImpl;
import com.marse.model.Category;
import com.marse.model.Customer;
import com.marse.model.Message;
import com.marse.model.User;
import com.marse.user.UserDAO;

@Controller
public class IntematerController {

	// login view
	@RequestMapping(value="login.form", method=RequestMethod.POST)
	public ModelAndView loginPage(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="newUser.form", method=RequestMethod.GET)
	public ModelAndView newUser(){
		return new ModelAndView("newUser");
	}
	
/*	// Register form from link
	@RequestMapping(value="login.form", method=RequestMethod.POST)
	public ModelAndView registerCustomer(){
		
// continues....
		
		User objUser=new User();
		
		objUser=objUserDAO.getUser(userId);  //  fetched whole record of the current user
		 
		// fetching existing categories
		
		List<Category> objlstCategory=new ArrayList<Category>();
		objlstCategory=DAOFactory.getInstancOfCategory().listOfCatagory();
		
		// fetching list of all the users
		List<User> listOfUser=objUserDAO.listOfUser();
		objModel.addObject("listOfUser", listOfUser );
		
		HttpSession objSession=request.getSession(false);
		objSession.setAttribute("objUser", objUser);   // kept current user object into HttpSession for session handling
		objModel.addObject("objlstCategory", objlstCategory);
		objModel.setViewName("register");
		
	}*/
	
	// Testing of the message functionality
	@RequestMapping(value="saveMessage.form", method=RequestMethod.GET)
	public ModelAndView saveMessage(){
		
		
		String m="This is a 2nd Message";
		
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    String strDate = sdf.format(cal.getTime());
	    
		Message objMessage=new Message();
		
		objMessage.setMessageData(m);
		objMessage.setMsgDate(strDate);
		
		MessageDAO objMessageDAO=new MessageDAOImpl();
		
		int msgId=objMessageDAO.saveMessage(objMessage);
		
		System.out.println("Controller: MSG Id :- "+msgId);
		
		return new ModelAndView("register");
	}
	
	// authentication logic
	@RequestMapping(value="LoginAuthenticate.form", method=RequestMethod.POST)
	public ModelAndView validateUser(@RequestParam int userId,
									 @RequestParam String password,
									 HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
									 InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException,
									 IOException{
		
		ModelAndView objModel=new ModelAndView(); 

		System.out.println("Remote Address : "+request.getRemoteAddr());
		System.out.println("Remote Host : "+request.getRemoteHost());
		System.out.println("Remote Port : "+request.getRemotePort());
		System.out.println("Remote User : "+request.getRemoteUser());
		System.out.println("Requested Session Id : "+request.getRequestedSessionId());
		System.out.println("Requested URI : "+request.getRequestURI());
		
		// Checking authentication
		
		UserDAO objUserDAO=DAOFactory.getInstanceOfUser();
		
		boolean loginStatus=objUserDAO.authenticateUser(userId, password);
		
		if (loginStatus==true) {
			
			User objUser=new User();
			
			objUser=objUserDAO.getUser(userId);  //  fetched whole record of the current user
			 
			// fetching existing categories
			
			List<Category> objlstCategory=new ArrayList<Category>();
			objlstCategory=DAOFactory.getInstancOfCategory().listOfCatagory();
			
			/*// fetching list of all the users
			List<User> listOfUser=objUserDAO.listOfUser();
			objModel.addObject("listOfUser", listOfUser );*/
			
			HttpSession objSession=request.getSession(false);
			objSession.setAttribute("objUser", objUser);   // kept current user object into HttpSession for session handling
			objModel.addObject("objlstCategory", objlstCategory);
			objModel.setViewName("register");
		} else {

			System.out.println("Remote Address : "+request.getRemoteAddr());
			System.out.println("Remote Host : "+request.getRemoteHost());
			System.out.println("Remote Port : "+request.getRemotePort());
			System.out.println("Remote User : "+request.getRemoteUser());
			System.out.println("Requested Session Id : "+request.getRequestedSessionId());
			System.out.println("Requested URI : "+request.getRequestURI());
			
			String message="Invalid User Id or password<br> Please check..!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
		}
		
		return objModel;
	}
	
	// Register Page
	@RequestMapping(value="registerPage.form", method=RequestMethod.GET)
	public ModelAndView registerPage(HttpServletRequest request) {
		
		ModelAndView objModel=new ModelAndView(); 
		// first checking session
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			System.out.println("session is null");
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			System.out.println("session is not null");
			if(objSession.getAttribute("objUser")==null){
				System.out.println("session>objuser is null");
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
				System.out.println("session objuser is not null");
				// bussiness logic
		
				// fetching existing categories
				
				List<Category> objlstCategory=new ArrayList<Category>();
				objlstCategory=DAOFactory.getInstancOfCategory().listOfCatagory();
				
				/*// fetching list of all the users
				List<User> listOfUser=objUserDAO.listOfUser();
				objModel.addObject("listOfUser", listOfUser );*/
				
				objModel.addObject("objlstCategory", objlstCategory);
				objModel.setViewName("register");
				
				return objModel;  
			}
		}
	}
	
	
	
	
	//################################################ CUSTOMER 21042016 ############################################################################	
	// creating new contact of Customer
	
	@RequestMapping(value="register.form", method=RequestMethod.POST)
	public  ModelAndView createNewCustomer( @RequestParam String custName,
											@RequestParam String custMobile1,
											@RequestParam(required=false) String custMobile2,
											@RequestParam String custWork,
											@RequestParam (required=false) String custEmail,
											@RequestParam (required=false) String custPan,
											@RequestParam String custDob,
											@RequestParam String custGender,
											@RequestParam String custAddress,
											@RequestParam (required=false) String bankName,
											@RequestParam (required=false) String branch,
											@RequestParam (required=false) String accNo,
											@RequestParam (required=false) String ifsc,
											@RequestParam (required=false) String micr,
											@RequestParam int category,
											HttpServletRequest request){
		
		ModelAndView objModel=new ModelAndView(); 
		// first checking session
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			System.out.println("session is null");
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			System.out.println("session is not null");
			if(objSession.getAttribute("objUser")==null){
				System.out.println("session>objuser is null");
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
				System.out.println("session objuser is not null");
				
				// Date functionality
				 SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH);
				 Date date=null;

				 try {

			            date = formatter.parse(custDob);
			            System.out.println("1: "+date);
			            System.out.println("2 : "+formatter.format(date));

			     } catch (ParseException e) {
			            e.printStackTrace();
			     }
				 
				// bussiness logic
				
				Customer objCustomer=new Customer();
				
				objCustomer.setName(custName);
				objCustomer.setMobile1(custMobile1);
				objCustomer.setMobile2(custMobile2);
				objCustomer.setWork(custWork);
				objCustomer.setEmail(custEmail);
				objCustomer.setPan(custPan);
				objCustomer.setDob(date);
				objCustomer.setGender(custGender);
				objCustomer.setAddress(custAddress);
				objCustomer.setBankName(bankName);
				objCustomer.setBankBranch(branch);
				objCustomer.setIfsc(ifsc);
				objCustomer.setMicr(micr);
				objCustomer.setCategory(category);
				objCustomer.setAcc(accNo);
				objCustomer.setStatus("A");
				// calling to the DAO methods to save the Customer details
				
				CustomerDAO objCustomerDAO=DAOFactory.getInstanceOfCustomer();
				
				objCustomerDAO.addCustomer(objCustomer);
				
				// getting the list of the current inserted Customers category type
				
				CategoryDAO objCategoryDAO=DAOFactory.getInstancOfCategory();
				
				//List<Category> objlstCategory=objCategoryDAO.listOfCategory();
				
				objModel.setViewName("register");
				
				return objModel;    
		        
		        
			}
			
		}// end of else
	}
	
// 22042016
	
	// fetching list of Customer
	
		@RequestMapping(value="showCustomers.form", method=RequestMethod.GET)
		public ModelAndView listOfCustomers(@RequestParam (value="currentPage",required=false) String currentPage,
									  		@RequestParam (value="recperpage",required=false) String recperpage,
									  		@RequestParam (value="categoryId",required=false) String categoryId,
									  		HttpServletRequest request){
		
			System.out.println("CP: "+currentPage);
			int cPage=0;
			if (null==currentPage) {
				cPage=1;
			}else{
				cPage=Integer.parseInt(currentPage);
			}
			
			int noOfRecordsPerPage=Integer.parseInt(recperpage);
			
			// first checking session
					HttpSession objSession= request.getSession(false);
					ModelAndView objModel=new ModelAndView();
					if(objSession==null){
						String message="Time out,<br> Please login again...!";
						objModel.addObject("message", message);
						objModel.setViewName("login");
						return objModel;
					}else{
						if(objSession.getAttribute("objUser")==null){
							String message="Invalid Session,<br> Please login again...!";
							objModel.addObject("message", message);
							objModel.setViewName("login");
							return objModel;
						}else{
							System.out.println("session objuser is not null");
							
							// checking whether categoryId selected or not
							
							int offSet=0;
							int  noOfPages=0;
							
							List<Customer> listOfCustomer=null;
							
							if (null != categoryId) {
								
								int catId=Integer.parseInt(categoryId);
								CustomerDAO objCustomerDAO=DAOFactory.getInstanceOfCustomer();
								
								offSet=(cPage * noOfRecordsPerPage)-noOfRecordsPerPage;
								System.out.println("offset="+offSet);
								listOfCustomer=objCustomerDAO.listOfCustomer(catId,offSet, noOfRecordsPerPage);
								int customerCount=objCustomerDAO.customerCount(catId);
								
								
								noOfPages = (int) Math.ceil(customerCount * 1.0 / noOfRecordsPerPage);
								   
								 	System.out.println("Total Pages= "+noOfPages);
				  					System.out.println("new pageno="+cPage);
				  					
							    if(noOfPages<=0){
								   noOfPages=1;
							    }
							    if(cPage<=0){
									   cPage=1;
								}
								
							}
							
						    // fetching existing categories
							List<Category> objlstCategory=DAOFactory.getInstancOfCategory().listOfCatagory();//new ArrayList<Category>();
							//objlstCategory=DAOFactory.getInstancOfCategory().listOfCatagory();
							System.out.println("cPage "+cPage);
							
							objModel.addObject("categoryId", categoryId);
							objModel.addObject("objlstCategory", objlstCategory);
							objModel.addObject("listOfCustomer", listOfCustomer );
							objModel.addObject("noOfPages",noOfPages);
							objModel.addObject("currentPage",cPage);
							objModel.addObject("noOfRecordsPerPage",noOfRecordsPerPage);
							objModel.setViewName("showCustomers");
							return objModel;
						}
					}
		}
		
		// fetching list of All Customer of selected category
		
		@RequestMapping(value="getAllCustomers.form", method=RequestMethod.POST)
		public ModelAndView listOfAllCustomers( @RequestParam (value="categoryId",required=false) String categoryId,
												@RequestParam (value="subject",required=false) String subject,
												@RequestParam (value="message",required=false) String messageBody,
											    HttpServletRequest request){
		
			
			// first checking session
					HttpSession objSession= request.getSession(false);
					ModelAndView objModel=new ModelAndView();
					if(objSession==null){
						String message="Time out,<br> Please login again...!";
						objModel.addObject("message", message);
						objModel.setViewName("login");
						return objModel;
					}else{
						if(objSession.getAttribute("objUser")==null){
							String message="Invalid Session,<br> Please login again...!";
							objModel.addObject("message", message);
							objModel.setViewName("login");
							return objModel;
						}else{
							System.out.println("session objuser is not null");
							
							// checking whether categoryId selected or not
							
							List<Customer> listOfCustomer=null;
							
							if (null != categoryId) {
								
								int catId=Integer.parseInt(categoryId);
								CustomerDAO objCustomerDAO=DAOFactory.getInstanceOfCustomer();
								
								listOfCustomer=objCustomerDAO.listOfAllCustomer(catId);
							}
							
						    // fetching existing categories
							List<Category> objlstCategory=DAOFactory.getInstancOfCategory().listOfCatagory();//new ArrayList<Category>();
							
							objModel.addObject("subject", subject);
							objModel.addObject("messageBody", messageBody);
							objModel.addObject("categoryId", categoryId);
							objModel.addObject("objlstCategory", objlstCategory);
							objModel.addObject("listOfCustomer", listOfCustomer );
							objModel.setViewName("sendEmail");
							return objModel;
						}
					}
		}		
		
		
		@RequestMapping(value="deleteCustomer.form", method=RequestMethod.GET)
		public ModelAndView deleteCustomer( @RequestParam int customerId,
											@RequestParam int categoryId,
											HttpServletRequest request){
			
			System.out.println("In deleterCustomer() method Controller");
	        ModelAndView objModel=new ModelAndView();
			HttpSession objSession= request.getSession(false);
			if(objSession==null){
				String message="Time out,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
				if(objSession.getAttribute("objUser")==null){
					String message="Invalid Session,<br> Please login again...!";
					objModel.addObject("message", message);
					objModel.setViewName("login");
					return objModel;
				}else{
					
						int offSet=0;
						int  noOfPages=0;
						int noOfRecordsPerPage=25;
						
						CustomerDAO objCustomerDAO=DAOFactory.getInstanceOfCustomer();
						objCustomerDAO.deleteCustomer(customerId);

						List<Customer> listOfCustomer=objCustomerDAO.listOfCustomer(categoryId, offSet, noOfRecordsPerPage);
						
						int customerCount=objCustomerDAO.customerCount(categoryId);
						
						noOfPages = (int) Math.ceil(customerCount * 1.0 / noOfRecordsPerPage);
						
						
						 // fetching existing categories
						List<Category> objlstCategory=DAOFactory.getInstancOfCategory().listOfCatagory();//new ArrayList<Category>();
						
						objModel.addObject("categoryId", categoryId);
						objModel.addObject("objlstCategory", objlstCategory);
						objModel.addObject("listOfCustomer", listOfCustomer);
						objModel.setViewName("showCustomers");	
						
					return objModel;	
				}
			}
		}
		
		
		@RequestMapping(value="editCustomer.form", method=RequestMethod.GET)
		public ModelAndView editCustomer(@RequestParam int customerId,
										 HttpServletRequest request){
			
			ModelAndView objModel=new ModelAndView();
			
			HttpSession objSession= request.getSession(false);
			
			if(objSession==null){
				String message="Time out,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
				if(objSession.getAttribute("objUser")==null){
					String message="Invalid Session,<br> Please login again...!";
					objModel.addObject("message", message);
					objModel.setViewName("login");
					return objModel;
				}else{
						CustomerDAO objCustomerDAO=DAOFactory.getInstanceOfCustomer();
						Customer objCustomer=new Customer();
						objCustomer=objCustomerDAO.getCustomer(customerId); //  fetched whole record of the deleting user
						
						 // fetching existing categories
						List<Category> objlstCategory=DAOFactory.getInstancOfCategory().listOfCatagory();//new ArrayList<Category>();
						
						objModel.addObject("objlstCategory", objlstCategory);
						objModel.addObject("objCustomer", objCustomer);
						objModel.setViewName("editCustomer");
						
						return objModel;
				}
			}
			
		}
		
		
		
		@RequestMapping(value="updateCustomer.form", method=RequestMethod.POST)
		public ModelAndView updateCustomer( @RequestParam int custId,
											@RequestParam String custName,
											@RequestParam String custMobile1,
											@RequestParam(required=false) String custMobile2,
											@RequestParam String custWork,
											@RequestParam (required=false) String custEmail,
											@RequestParam (required=false) String custPan,
											@RequestParam String custDob,
											@RequestParam String custGender,
											@RequestParam String custAddress,
											@RequestParam (required=false) String bankName,
											@RequestParam (required=false) String branch,
											@RequestParam (required=false) String accNo,
											@RequestParam (required=false) String ifsc,
											@RequestParam (required=false) String micr,
											@RequestParam int category,
											@RequestParam String status,
											HttpServletRequest request){
			
			
			ModelAndView objModel=new ModelAndView();
			
			HttpSession objSession= request.getSession(false);
			
			if(objSession==null){
				String message="Time out,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
				if(objSession.getAttribute("objUser")==null){
					String message="Invalid Session,<br> Please login again...!";
					objModel.addObject("message", message);
					objModel.setViewName("login");
					return objModel;
				}else{

					List<Customer> listOfCustomer=null;
					int cPage=1;
					
					// Date functionality
					 SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd", Locale.ENGLISH);
					 Date date=null;

					 try {

				            date = formatter.parse(custDob);
				            System.out.println("1: "+date);
				            System.out.println("2 : "+formatter.format(date));

				     } catch (ParseException e) {
				            e.printStackTrace();
				     }
						
				        Customer objCustomer=new Customer();
						
						objCustomer.setCustId(custId);
						objCustomer.setName(custName);
						objCustomer.setMobile1(custMobile1);
						objCustomer.setMobile2(custMobile2);
						objCustomer.setWork(custWork);
						objCustomer.setEmail(custEmail);
						objCustomer.setPan(custPan);
						objCustomer.setDob(date);
						objCustomer.setGender(custGender);
						objCustomer.setAddress(custAddress);
						objCustomer.setBankName(bankName);
						objCustomer.setBankBranch(branch);
						objCustomer.setIfsc(ifsc);
						objCustomer.setMicr(micr);
						objCustomer.setCategory(category);
						objCustomer.setAcc(accNo);
						objCustomer.setStatus(status);
						
						// calling to the DAO methods to update the Customer details
						
						CustomerDAO objCustomerDAO=DAOFactory.getInstanceOfCustomer();
						
						objCustomerDAO.updateCustomer(objCustomer);
						
						// fetching list of all customers of selected category.
						
						int offSet=0;
						int  noOfPages=0;
						
							int catId=category;
							
							offSet=(1 * 25)-25;
							System.out.println("offset="+offSet);
							listOfCustomer=objCustomerDAO.listOfCustomer(catId,offSet, 25);
							int customerCount=objCustomerDAO.customerCount(catId);
							
							
							noOfPages = (int) Math.ceil(customerCount * 1.0 / 25);
							   
							 	System.out.println("Total Pages= "+noOfPages);
			  					
						    if(noOfPages<=0){
							   noOfPages=1;
						    }
						    if(cPage<=0){
								   cPage=1;
							}
							
						    // fetching existing categories
							List<Category> objlstCategory=DAOFactory.getInstancOfCategory().listOfCatagory();//new ArrayList<Category>();
							//objlstCategory=DAOFactory.getInstancOfCategory().listOfCatagory();
							System.out.println("cPage "+cPage);
							
							objModel.addObject("categoryId", category);
							objModel.addObject("objlstCategory", objlstCategory);
							objModel.addObject("listOfCustomer", listOfCustomer );
							objModel.addObject("noOfPages",noOfPages);
							objModel.addObject("currentPage",cPage);
							objModel.addObject("noOfRecordsPerPage",25);
							objModel.setViewName("showCustomers");
							
							return objModel;
						}
						
				}
			
		}
	
//###################################################### CUSTOMER ##########################################################################
	
	
// 30032016 17:04  new user ****************************USER MODULE CODE********************************************************************
	
	@RequestMapping(value="newUser.form", method=RequestMethod.POST)
	public  ModelAndView createNewUser( @RequestParam String firstName,
										@RequestParam String lastName,
										@RequestParam String gender,
										@RequestParam String userMobile1,
										@RequestParam(required=false) String userMobile2,
										@RequestParam String userPassword,
										@RequestParam String userEmail,
										@RequestParam String roll,
										@RequestParam String userStatus,
										HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
		
		ModelAndView objModel=new ModelAndView(); 
		// first checking session
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			System.out.println("session is null");
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			System.out.println("session is not null");
			if(objSession.getAttribute("objUser")==null){
				System.out.println("session>objuser is null");
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
				System.out.println("session objuser is not null");

				// bussiness logic
				
				User objUser=new User();
				
				objUser.setFirstName(firstName);
				objUser.setLastName(lastName);
				objUser.setRoll(roll);
				objUser.setUserEmail(userEmail);
				objUser.setUserMobile1(userMobile1);
				objUser.setUserMobile2(userMobile2);
				objUser.setUserGender(gender);
				objUser.setUserPassword(new CryptoUtil().encrypt(userPassword));
				objUser.setUserStatus(userStatus);
				
				UserDAO objUserDAO=DAOFactory.getInstanceOfUser();

				objUserDAO.addUser(objUser);
				
				// fetching list of all the users
				
				List<User> listOfUser=objUserDAO.listOfUser(0,15);
				
				objModel.addObject("listOfUser", listOfUser);
				objModel.setViewName("showUser");
				return objModel;    
		        
		        
			}
			
		}// end of else
	}
	
	// fetching list of users
	@RequestMapping(value="showUsers.form", method=RequestMethod.GET)
	public ModelAndView showUsers(@RequestParam (value="currentPage",required=false) String currentPage,
								  @RequestParam (value="recperpage",required=false) String recperpage,
								  HttpServletRequest request){
	
		int cPage=0;
		if (null==currentPage) {
			cPage=1;
		}else{
			cPage=Integer.parseInt(currentPage);
		}
		
		int noOfRecordsPerPage=Integer.parseInt(recperpage);
		
		// first checking session
				HttpSession objSession= request.getSession(false);
				ModelAndView objModel=new ModelAndView();
				if(objSession==null){
					String message="Time out,<br> Please login again...!";
					objModel.addObject("message", message);
					objModel.setViewName("login");
					return objModel;
				}else{
					if(objSession.getAttribute("objUser")==null){
						String message="Invalid Session,<br> Please login again...!";
						objModel.addObject("message", message);
						objModel.setViewName("login");
						return objModel;
					}else{
						System.out.println("session objuser is not null");
						
						UserDAO objUserDAO=DAOFactory.getInstanceOfUser();
						
						int offSet=(cPage * noOfRecordsPerPage)-noOfRecordsPerPage;
						System.out.println("offset="+offSet);
						List<User> listOfUser=objUserDAO.listOfUser(offSet, noOfRecordsPerPage);
						int userCount=objUserDAO.userCount();
						
						 int  noOfPages = (int) Math.ceil(userCount * 1.0 / noOfRecordsPerPage);
						   
						 	System.out.println("Total Pages= "+noOfPages);
		  					System.out.println("new pageno="+cPage);
		  					
					    if(noOfPages<=0){
						   noOfPages=1;
					    }
					    
						objModel.addObject("listOfUser", listOfUser );
						objModel.setViewName("showUser");
						objModel.addObject("noOfPages",noOfPages);
						objModel.addObject("currentPage",cPage);
						objModel.addObject("noOfRecordsPerPage",noOfRecordsPerPage);
						return objModel;
					}
				}
	}

	// fetch the user details of the selected userId and displays on the ediUser.jsp page
	@RequestMapping(value="editUser.form", method=RequestMethod.GET)
	public ModelAndView editUser(@RequestParam int userId, HttpServletRequest request){
		
		ModelAndView objModel=new ModelAndView();
		
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			if(objSession.getAttribute("objUser")==null){
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
					UserDAO objUserDAO=DAOFactory.getInstanceOfUser();
					User objUser=new User();
					objUser=objUserDAO.getUser(userId);  //  fetched whole record of the deleting user
					
					objModel.addObject("objUser", objUser);
					objModel.setViewName("editUser");
					
					return objModel;
			}
		}
		
	}
	
	
	// updating the selected user
	@RequestMapping(value="updateUser.form", method=RequestMethod.POST)
	public ModelAndView updateUser( @RequestParam int userId,
									@RequestParam String firstName,
									@RequestParam String lastName,
									@RequestParam String gender,
									@RequestParam String userMobile1,
									@RequestParam(required=false) String userMobile2,
									@RequestParam String userEmail,
									@RequestParam String roll,
									@RequestParam String userStatus,
									HttpServletRequest request){
							
		ModelAndView objModel=new ModelAndView();
		
		HttpSession objSession= request.getSession(false);
		
		int cPage=1;
		int noOfRecordsPerPage=25;
		
		if(objSession==null){
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			if(objSession.getAttribute("objUser")==null){
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
					UserDAO objUserDAO=DAOFactory.getInstanceOfUser();
					User objUser=new User();
					
					// keeping all values of updated user into the User object
					objUser.setUserId(userId);
					objUser.setFirstName(firstName);
					objUser.setLastName(lastName);
					objUser.setRoll(roll);
					objUser.setUserEmail(userEmail);
					objUser.setUserMobile1(userMobile1);
					objUser.setUserMobile2(userMobile2);
					objUser.setUserGender(gender);
					objUser.setUserStatus(userStatus);
					
					objUserDAO.updateUser(objUser);
					
					List<User> listOfUser=objUserDAO.listOfUser(0,25);
					
					int offSet=(cPage * noOfRecordsPerPage)-noOfRecordsPerPage;
					System.out.println("offset="+offSet);
					
					int userCount=objUserDAO.userCount();
					
					 int  noOfPages = (int) Math.ceil(userCount * 1.0 / noOfRecordsPerPage);
					   
					 	System.out.println("Total Pages= "+noOfPages);
	  					System.out.println("new pageno="+cPage);
	  					
				    if(noOfPages<=0){
					   noOfPages=1;
				    }
				    
					objModel.addObject("listOfUser", listOfUser);
					objModel.addObject("noOfPages",noOfPages);
					objModel.setViewName("showUser");
					
					return objModel;
			}
		}
		
	}
	
	// deleting the selected user (nothing but update the status flag from 'A' to 'I')
	
	@RequestMapping(value="deleteUser.form", method=RequestMethod.GET)
	public ModelAndView deleteUser( @RequestParam int userId,
									HttpServletRequest request){
		
		System.out.println("In deleterUser() method Controller");
        ModelAndView objModel=new ModelAndView();
		HttpSession objSession= request.getSession(false);
		
		int cPage=1;
		int noOfRecordsPerPage=25;
		
		if(objSession==null){
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			if(objSession.getAttribute("objUser")==null){
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
					UserDAO objUserDAO=DAOFactory.getInstanceOfUser();
					
					objUserDAO.deleteUser(userId);
					
					List<User> listOfUser=objUserDAO.listOfUser(0,15);
					
					int offSet=(cPage * noOfRecordsPerPage)-noOfRecordsPerPage;
					System.out.println("offset="+offSet);
					
					int userCount=objUserDAO.userCount();
					
					 int  noOfPages = (int) Math.ceil(userCount * 1.0 / noOfRecordsPerPage);
					   
					 	System.out.println("Total Pages= "+noOfPages);
	  					System.out.println("new pageno="+cPage);
	  					
				    if(noOfPages<=0){
					   noOfPages=1;
				    }
				    
					objModel.addObject("listOfUser", listOfUser);
					objModel.addObject("noOfPages",noOfPages);
					objModel.setViewName("showUser");	
					
				return objModel;	
			}
		}
		
		
		
		
	}
	
// *******************************************************USER MODULE CODE COMPLETED********************************************************
	
	
	
	@RequestMapping(value="showCategory.form", method=RequestMethod.GET)
	public ModelAndView showCategory(HttpServletRequest request){
		
		
		ModelAndView objModel=new ModelAndView();
		
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			if(objSession.getAttribute("objUser")==null){
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
					CategoryDAO objCategoryDAO=DAOFactory.getInstancOfCategory();
					
					List<Category> listOfCategory=objCategoryDAO.listOfCategory();
					
					objModel.addObject("listOfCategory", listOfCategory);
					objModel.setViewName("showCategory");
					
					return objModel;
			}
		}
		
	}
	// adding new category 
	@RequestMapping(value="addCategory.form", method=RequestMethod.POST)
	public ModelAndView addCategory( @RequestParam String categoryName,
									 HttpServletRequest request){
		
		
		ModelAndView objModel=new ModelAndView();
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			if(objSession.getAttribute("objUser")==null){
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
					CategoryDAO objCategoryDAO=DAOFactory.getInstancOfCategory();
					Category objCategory=new Category();

					objCategory.setCategoryName(categoryName);
				
					objCategoryDAO.addCategory(objCategory);
					
					List<Category> listOfCategory=objCategoryDAO.listOfCategory();
					
					objModel.addObject("listOfCategory", listOfCategory);
					objModel.setViewName("showCategory");
					
					return objModel;
			}
		}
		
	}
	// updating category by using Id
	@RequestMapping(value="updateCategory.form", method=RequestMethod.POST)
	public ModelAndView updateCategory(@RequestParam int categoryId,
									   @RequestParam String categoryName,
									   HttpServletRequest request){
		
		
		ModelAndView objModel=new ModelAndView();
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			if(objSession.getAttribute("objUser")==null){
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
					CategoryDAO objCategoryDAO=DAOFactory.getInstancOfCategory();
					Category objCategory=new Category();

					objCategory.setCategoryId(categoryId);
					objCategory.setCategoryName(categoryName);
				
					objCategoryDAO.updateCategory(objCategory);
					
					List<Category> listOfCategory=objCategoryDAO.listOfCategory();
					
					objModel.addObject("listOfCategory", listOfCategory);
					objModel.setViewName("showCategory");
					
					return objModel;
			}
		}
		
	}
	
	
	// deleting category by using Id
	
	@RequestMapping(value="deleteCategory.form", method=RequestMethod.POST)
	public ModelAndView deleteCategory(@RequestParam int deleteId,
									   HttpServletRequest request){
		
		
		ModelAndView objModel=new ModelAndView();
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			if(objSession.getAttribute("objUser")==null){
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
					CategoryDAO objCategoryDAO=DAOFactory.getInstancOfCategory();
					Category objCategory=new Category();

					objCategory.setCategoryId(deleteId);
					objCategoryDAO.deleteCategory(objCategory);
					
					List<Category> listOfCategory=objCategoryDAO.listOfCategory();
					
					objModel.addObject("listOfCategory", listOfCategory);
					objModel.setViewName("showCategory");
					
					return objModel;
			}
		}
		
	}
	
	// Logout Page
	@RequestMapping(value="logout.form", method=RequestMethod.GET)
	public ModelAndView logoutPage(HttpServletRequest request) {
		
		ModelAndView objModel=new ModelAndView(); 
		// first checking session
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			System.out.println("session is null");
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			System.out.println("session is not null");
			if(objSession.getAttribute("objUser")==null){
				System.out.println("session>objuser is null");
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
				System.out.println("session objuser is not null");
				
				objSession.invalidate();
				
				objModel.addObject("message", "<font color='red' align='center'><h3>Logged out Successfully.  </h3></font>");
				objModel.setViewName("logout");
				
				return objModel;  
			}
		}
	}
	
	// Change Password Page
	@RequestMapping(value="changePasswordPage.form", method=RequestMethod.GET)
	public ModelAndView changePasswordPage(HttpServletRequest request){
		
		ModelAndView objModel=new ModelAndView(); 
		// first checking session
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			System.out.println("session is null");
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			System.out.println("session is not null");
			if(objSession.getAttribute("objUser")==null){
				System.out.println("session>objuser is null");
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
				System.out.println("session objuser is not null");
				
				objModel.setViewName("changePassword");
				
				return objModel;  
			}
		}
	}
	
	// Change Password
	@RequestMapping(value="changePassword.form", method=RequestMethod.POST)
	public ModelAndView changePassword( @RequestParam String currentPassword,
										@RequestParam String newPassword,
										HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
										 InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException,
										 IOException{
		
		ModelAndView objModel=new ModelAndView(); 
		// first checking session
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			System.out.println("session is null");
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			System.out.println("session is not null");
			if(objSession.getAttribute("objUser")==null){
				System.out.println("session>objuser is null");
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
				System.out.println("session objuser is not null");
				
				UserDAO objUserDAO=DAOFactory.getInstanceOfUser();
				
				User objUser=(User) objSession.getAttribute("objUser");
				
				boolean reply=false;
				
				reply=objUserDAO.changePassword(objUser, currentPassword, newPassword);
				
				if (reply) {
					
					objSession.invalidate();
					
					objModel.addObject("message", "<font color='blue' align='center'><h3> Password changed Successfully" +
												  " </h3></font>");
					objModel.setViewName("logout");
					
					return objModel;  
					
				} else {
					
					objModel.addObject("message", "<font color='red' align='center'><h3>" +
												  " Something wrong, please try again...</h3></font>");
					objModel.setViewName("changePassword");
					
					return objModel;

				}
			}
		}
	}
	
	// Send Email page
	
	@RequestMapping(value="sendEmailPage.form", method=RequestMethod.GET)
	public ModelAndView sendEmailPage(HttpServletRequest request){
		
		
		ModelAndView objModel=new ModelAndView();
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			if(objSession.getAttribute("objUser")==null){
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
					CategoryDAO objCategoryDAO=DAOFactory.getInstancOfCategory();
					List<Category> listOfCategory=objCategoryDAO.listOfCategory();
					
					objModel.addObject("objlstCategory", listOfCategory);
					objModel.setViewName("sendEmail");
					
					return objModel;
			}
		}
		
	}
	
	// Sending Emails
	
	@RequestMapping(value="sendEmail.form", method=RequestMethod.POST)
	public ModelAndView sendEmail(HttpServletRequest request){
		
		
		ModelAndView objModel=new ModelAndView();
		HttpSession objSession= request.getSession(false);
		
		if(objSession==null){
			String message="Time out,<br> Please login again...!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
			return objModel;
		}else{
			if(objSession.getAttribute("objUser")==null){
				String message="Invalid Session,<br> Please login again...!";
				objModel.addObject("message", message);
				objModel.setViewName("login");
				return objModel;
			}else{
					CategoryDAO objCategoryDAO=DAOFactory.getInstancOfCategory();
					List<Category> listOfCategory=objCategoryDAO.listOfCategory();
					
					objModel.addObject("objlstCategory", listOfCategory);
					objModel.setViewName("sendEmail");
					
					return objModel;
			}
		}
		
	}
	
}
