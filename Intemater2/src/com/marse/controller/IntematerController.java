package com.marse.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.marse.crypto.CryptoUtil;
import com.marse.daofactory.DAOFactory;
import com.marse.model.User;
import com.marse.user.UserDAO;

@Controller
public class IntematerController {

	// login view
	@RequestMapping(value="login.form", method=RequestMethod.POST)
	public ModelAndView loginPage(){
		return new ModelAndView("login");
	}
	
	// authentication logic
	@RequestMapping(value="LoginAuthenticate.form", method=RequestMethod.POST)
	public ModelAndView validateUser(@RequestParam int userId,
									 @RequestParam String password,
									 HttpServletRequest request) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
									 InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException,
									 IOException{
		
		ModelAndView objModel=new ModelAndView(); 

		// Checking authentication
		
		UserDAO objUserDAO=DAOFactory.getInstanceOfUser();
		
		boolean loginStatus=objUserDAO.authenticateUser(userId, password);
		
		if (loginStatus==true) {
			
			User objUser=new User();
			
			objUser=objUserDAO.getUser(userId);  //  fetched whole record of the current user
			
			/*// fetching list of all the users
			List<User> listOfUser=objUserDAO.listOfUser();
			objModel.addObject("listOfUser", listOfUser );*/
			
			HttpSession objSession=request.getSession(false);
			objSession.setAttribute("objUser", objUser);   // kept current user object into HttpSession for session handling
			
			objModel.setViewName("register");
		} else {

			String message="Invalid User Id or password<br> Please check..!";
			objModel.addObject("message", message);
			objModel.setViewName("login");
		}
		
		return objModel;
	}
	
	
	// creating new contact of Customer
	
	@RequestMapping(value="register.form", method=RequestMethod.POST)
	public  ModelAndView createNewCustomer( HttpServletRequest request){
		
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
				
				return objModel;    
		        
		        
			}
			
		}// end of else
	}
	
	// new user
	
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
				
				List<User> listOfUser=objUserDAO.listOfUser();
				
				objModel.addObject("listOfUser", listOfUser);
				objModel.setViewName("showUser");
				return objModel;    
		        
		        
			}
			
		}// end of else
	}
	
	// fetching list of users
	@RequestMapping(value="showUsers.form", method=RequestMethod.GET)
	public ModelAndView showUsers(HttpServletRequest request){
	
		
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
						List<User> listOfUser=objUserDAO.listOfUser();
						
						objModel.addObject("listOfUser", listOfUser );
						objModel.setViewName("showUser");
						
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
					
					List<User> listOfUser=objUserDAO.listOfUser();
					
					objModel.addObject("listOfUser", listOfUser);
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
					
					List<User> listOfUser=objUserDAO.listOfUser();
					
					objModel.addObject("listOfUser", listOfUser);
					objModel.setViewName("showUser");	
					
				return objModel;	
			}
		}
		
		
		
		
	}
	
}
