<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
<title>Edit User</title>
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
b    {color:#A4A4A4;}

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

div {
    width: 100%;
    height: 10px;
    border: 3px solid #73AD21;
}
</style>
  <h5 align="left"> 
			<a href="newUser.form">View Contacts</a> |
		    <a href="newUser.form">Send Email</a> |
			<a href="register.form">Create Contact</a> |
			<a href="newUser.form">Create User</a> |
		    <a href="newUser.form">Change Password</a> |
		    <a href="showUser.form">Show Users</a> |
		    <a href="newUser.form">Logout</a>
		</h5>
<header class="main-header" role="banner">
  <img src="images/user.png" alt="Banner Image" align="center"  height="15%" width="15%"/>
</header>

</head>
<body>
		<!-- <h1 align="center"><font color="#808000"><u>Create New Contact</u></font></h1> -->
		<h2 align="center"><u>Update User</u></h2>

	<form name="updateUserPage" action="updateUser.form" method="post">
		
		<input type="hidden" name="userId" value="${objUser.userId}">
		
		
		<table align="center" border="0">
			<tr><td></td><td><font size="2 px"><b>First Name:</b></font></td> <td><font size="2 px"><b>Last Name:</b></font></td></tr>
			<tr> <td></td>
				<td><input type="text" name="firstName" id="idMale" placeholder="First Name" value="${objUser.firstName}"></td> 
				<td><input type="text" name="lastName" placeholder="Last Name" value="${objUser.lastName}"></td> </tr>
			<tr> <td></td>
				<td></td>
			</tr>	
			<tr><td></td><td><font size="2 px"><b>Contact 1:</b></font></td> <td><font size="2 px"><b>Contact 2:</b></font></td></tr>	
			<tr> <td></td>
				<td><input type="text" name="userMobile1" placeholder=" 1st Mobile " value="${objUser.userMobile1}"></td>
				<td><input type="text" name="userMobile2" placeholder=" 2nd Mobile " value="${objUser.userMobile2}"></td> </tr>
			
			<tr><td></td><td><font size="2 px"><b>Email Id:</b></font></td> <td><font size="2 px"><b>Status:</b></font></td></tr>	
			<tr> <td></td>
				<td><input type="text" name="userEmail" placeholder=" Email Id" value="${objUser.userEmail}"></td>
				<td>
					<select class="select-style gender" name="userStatus">
				            <option value="select">Select Status</option>
				           
				            <%-- <c:if test="${objUser.userStatus eq 'A'}">
				            		<option value="A" selected>Active</option>
				            		<option value="I" >Inactive</option>
				            </c:if>
				            <c:if test="${objUser.userStatus eq 'I'}">
				            		<option value="A" >Active</option>
				            		<option value="I" selected>Inactive</option>
				            </c:if> --%>
				            
				            <option value="A" ${objUser.userStatus=="A"? 'selected':'' }>Active</option>
						  <option value="I" ${objUser.userStatus=="I"? 'selected':'' }>Inactive</option>
						  
				      </select>
				
				</td> </tr>
				
				<tr><td></td><td><font size="2 px"><b>Role:</b></font></td> <td><font size="2 px"><b>Normal:</b></font></td></tr>			
				<tr>
						<td></td>
					<td>
					 <select class="select-style gender" name="roll">
				            <option value="select">Select User Roll</option>
				           
				            <c:if test="${objUser.roll eq 'admin'}">
				            		<option value="admin" selected>Admin</option>
				            </c:if>
				            <c:if test="${objUser.roll eq 'normal'}">
				            		<option value="admin" selected>Normal</option>
				            </c:if>
				            
				      </select>
				     </td>
				     <td>
				     	<select class="select-style gender" name="gender">
				            <option value="select">Select Gender</option>
				            
				            <c:if test="${objUser.userGender eq 'male'}">
				            		<option value="male" selected>Male</option>
				            </c:if>
				            <c:if test="${objUser.userGender eq 'female'}">
				            		<option value="female" selected>Female</option>
				            </c:if>
				            <c:if test="${objUser.userGender eq 'others'}">
				            		<option value="others" selected>Others</option>
				            </c:if>
				     	</select>
				  </td>	
				</tr>
				
				<tr> <td></td><td></td><td></td>
					<td colspan="4" align="center"> <input type="submit" name="save" value="Update User"> </td> </tr>
		</table>
	
	</form>

</body>
</html>