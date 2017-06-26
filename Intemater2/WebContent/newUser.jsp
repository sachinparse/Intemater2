<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if (null == session.getAttribute("objUser")) {
		response.sendRedirect("login.jsp");
	} else {
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
<title>Home</title>
<%@ include file="genericinclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="js/stdlib/jquery-1-9-1-min.js"></script>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
body {background-color:white;}
h2   {color:#2E9AFE;}
h3   {color:#2E9AFE;}

input[type=text], textarea {
  -webkit-transition: all 0.30s ease-in-out;
  -moz-transition: all 0.30s ease-in-out;
  -ms-transition: all 0.30s ease-in-out;
  -o-transition: all 0.30s ease-in-out;
  outline: none;
  padding: 3px 0px 3px 3px;
  margin: 5px 1px 3px 0px;
  border: 1px solid #DDDDDD;
}
 
input[type=text]:focus, textarea:focus {
  box-shadow: 0 0 5px rgba(81, 203, 238, 1);
  padding: 3px 0px 3px 3px;
  margin: 5px 1px 3px 0px;
  border: 1px solid rgba(81, 203, 238, 1);
}

select {
    padding:5px 15px; 
    background:#ccc;
   /*  background:#26A2E0; */
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 5px;
    border-radius: 5px; 
}
input[type=submit] {
    padding:5px 15px; 
    /* background:#ccc; */ 
    background:#8AB6CB;
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 5px;
    border-radius: 5px; 
}

.main-header {
  text-align: center;

   /* height: 10px;
  width: 28px;
  position: fixed;
  top: 0;
  left: 80%;
  margin-left: -499px; */
}
p{ 	font-family: "Times New Roman", Times, serif;
	font-style: oblique;
	font-size:15px;}
div,h5 {
	align: left;
    width: 100%;
    height: 20px;
    /* border: 2px solid #73AD21; */
   /*  background:#ccc; */
}
a:link, a:visited {
    background-color: #088D79;
    color: white;
    padding: 10px 25px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
}


a:hover, a:active {
    background-color: #FF8000;
}
</style>

<script>
	$(document).ready(function(){
		
		$("#idConfirmPassword").focusout(function(){
	
			if($("#idUserPassword").val() != $("#idConfirmPassword").val()){
				alert("Password not matching, try again: "+ $("#idUserPassword").val() +"& "+ $("#idConfirmPassword").val());
				$("#idConfirmPassword").focus();
			}		
		});
		
		$("#idSaveButton").click(function(){
			
			if($("#idUserPassword").val().length !=0 && $("#idFirstName").val().length !=0 && $("#idUserStatus").val() != "select"
				 && $("#idGender").val() != "select"){
				
				$("#idHomePage").submit();
				
			}else{
				alert("Please, Check Inputs...");	
			}
		});
	});

</script>

  <h5 align="center"> 
  
  			<a href="sendEmailPage.form">Send Email</a>
			<a href="showCustomers.form?recperpage=25">View Contacts</a>
			<a href="registerPage.form">Create Contact</a>
			<a href="showCategory.form">Category</a>
			<a href="msgReportPage.form">Message Report</a>
			<c:if test="${objUser.roll eq 'admin' }">
				<a href="showUsers.form?recperpage=25">Show Users</a>
			</c:if>
			<!-- <a href="newUser.form">Create New User</a> -->
			<a href="changePasswordPage.form">Change Password</a>
			<a href="logout.form">Logout</a>
  
  
			<!-- <a href="showCustomers.form?recperpage=25">View Contacts</a> 
		    <a href="newUser.jsp">Send Email</a> 
			<a href="register.jsp">Create Contact</a> 
			<a href="newUser.jsp">Create User</a> 
		    <a href="newUser.jsp">Change Password</a> 
		    <a href="showUsers.form?recperpage=25">Show Users</a>  
		    <a href="newUser.jsp">Logout</a> -->
  </h5>
<header class="main-header" role="banner">
  <img src="images/user.png" alt="Banner Image" align="center"  height="15%" width="15%"/>
</header>

</head>
<body>
		<!-- <h1 align="center"><font color="#808000"><u>Create New Contact</u></font></h1> -->
		<h2 align="center"><u>Add User</u></h2>

	<form name="homePage" id="idHomePage" action="newUser.form" method="post">
		
		<table align="center" border="0">
		
			<tr> <td></td>
				<!-- <td >First Name</td><td>:</td> --><td><input type="text" id="idFirstName" name="firstName" placeholder="First Name"></td> 
				<!-- <td >Last Name</td><td>:</td> --><td><input type="text" name="lastName" placeholder="Last Name"></td> </tr>
			<tr> <td></td>
				<!-- <td >Last Name</td><td>:</td><td><input type="text" name="lastName" placeholder="Last Name"></td> -->
			<!-- <td align="center">Gender</td><td>:</td> --><td>
					
				</td></tr>	
				
			 </tr>	
				
			<tr> <td></td>
				<!-- <td>Mobile No.</td><td>:</td> --> <td><input type="text" name="userMobile1" placeholder="1st Mobile " ></td>
				                              <td><input type="text" name="userMobile2" placeholder="2nd Mobile " ></td> </tr>
				
			<tr> <td></td>
				<td><input type="text" id="idUserPassword" name="userPassword" placeholder="Password"> </td>
				<td><input type="text" id="idConfirmPassword" name="confirmPassword" placeholder="Confirm Password"> </td> </tr>
				
			<tr> <td></td>
				<!-- <td>Email Id</td><td>:</td> --><td><input type="text" name="userEmail" placeholder=" Email Id" ></td> 
				
						
				  <td>
					 <select class="select-style gender" id="idUserStatus" name="userStatus">
				            <option value="select">Select Status</option>
				           
				            		<option value="A">Active</option>
				            		<option value="I">Inactive</option>
				      </select>
				  </td>
			  </tr>
							
				<tr>
						<!-- <td align="center">User Roll</td><td>:</td>-->
						<td></td>
					<td>
					 <select class="select-style gender" name="roll">
				            <option value="select">Select User Roll</option>
				            <option value="admin">Admin</option>
				            <option value="normal">Normal</option>
				      </select>
				     </td>
				     <td>
				     	<select class="select-style gender" id="idGender" name="gender">
				            <option value="select">Select Gender</option>
				            <option value="male">Male</option>
				            <option value="female">Female</option>
				            <option value="others">Other</option>
				     	</select>
				  </td>	
				</tr>
				
				<tr> <td></td><td></td><td></td>
					<td colspan="4" align="center"> <input type="button" id="idSaveButton" name="save" value="Create User"> </td> </tr>
		</table>
	
	</form>

	<div>
		<p align="right">Copyright &copy; 2016 &middot; All Rights Reserved. Contact information: 
		<!-- <a href="mailto:sachin.parse@gmail.com"> --><font color="blue">sachin.parse@gmail.com</font><!-- </a>. -->&nbsp;</p>
	</div>
	
</body>
</html>
<%
}
%>