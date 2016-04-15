package com.marse.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
import org.springframework.web.servlet.ModelAndView;

import com.marse.category.CategoryDAO;
import com.marse.crypto.CryptoUtil;
import com.marse.daofactory.DAOFactory;
import com.marse.model.Category;
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
			
			/*// fetching list of all the users
			List<User> listOfUser=objUserDAO.listOfUser();
			objModel.addObject("listOfUser", listOfUser );*/
			
			HttpSession objSession=request.getSession(false);
			objSession.setAttribute("objUser", objUser);   // kept current user object into HttpSession for session handling
			
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
					
					List<User> listOfUser=objUserDAO.listOfUser(0,15);
					
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
					
					List<User> listOfUser=objUserDAO.listOfUser(0,15);
					
					objModel.addObject("listOfUser", listOfUser);
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
	
	
	
}
