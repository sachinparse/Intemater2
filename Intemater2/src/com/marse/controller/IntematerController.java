package com.marse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.marse.daofactory.DAOFactory;
import com.marse.model.User;
import com.marse.user.UserDAO;

@Controller
public class IntematerController {

	@RequestMapping(value="login.form", method=RequestMethod.POST)
	public ModelAndView loginPage(){
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="LoginAuthenticate.form", method=RequestMethod.POST)
	public ModelAndView validateUser(@RequestParam int userId,
									 @RequestParam String password,
									 HttpServletRequest request){
		
		ModelAndView objModel=new ModelAndView(); 

		// Checking authentication
		
		UserDAO objUserDAO=DAOFactory.getInstanceOfUser();
		
		boolean loginStatus=objUserDAO.authenticateUser(userId, password);
		
		if (loginStatus==true) {
			
			User objUser=new User();
			
			objUser=objUserDAO.getUser(userId);  //  fetched whole record of the current user
			
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
	
	
	//2 
	
	@RequestMapping(value="register.form", method=RequestMethod.POST)
	public @ResponseBody ModelAndView createNewUser( HttpServletRequest request){
		
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
	
}
