<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7; IE=EmulateIE9">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
<title>Show User</title>
<%@ include file="genericinclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="js/stdlib/jquery-1-9-1-min.js"></script>


  <h5 align="left"> 
			<a href="showCustomer.form">View Contacts</a> |
		    <a href="sendEmail.form">Send Email</a> |
			<a href="register.form">Create Contact</a> |
			<a href="newUser.form">Create User</a> |
		    <a href="changePassword.form">Change Password</a> |
		    <a href="logout.form">Logout</a>
		</h5>
<!-- <header class="main-header" role="banner">
  <img src="images/user.png" alt="Banner Image" align="center"  height="12%" width="12%"/>
</header> -->

	<style>
	
		th {
	        background-color: #4CAF50;
	        color: white;
	    }
	    
	    tr:nth-child(even) {background-color: #f2f2f2}
	
		h2   {color: #6a6363;}
		
	</style>
</head>
<body>
		<h2 align="center"><u>User List</u></h2>

		<table  class="responstable" align="center">
		
			<tr>
			   <th>User ID</th> <th>First Name</th> <th>Last Name</th> <th>Gender</th> <th>Role</th> <th>Contact 1</th> <th>Contact 2</th> 
			   <th>Email ID</th> <th>Status</th> <th>Edit / Delete</th>
			</tr>
		
			<c:forEach var="userList" items="${listOfUser}">
			  <tr align="center">	
				<td>${userList.userId }</td>
				<td>${userList.firstName }</td>
				<td>${userList.lastName}</td>
				<td>${userList.userGender }</td>
				<td>${userList.roll }</td>
				<td>${userList.userMobile1 }</td>
				<td>
					<c:if test="${userList.userMobile2 eq null or  userList.userMobile2 eq ''}">
						-
					</c:if>
					<c:if test="${userList.userMobile2 ne null or  userList.userMobile2 ne ''}">
						${userList.userMobile2}
					</c:if>	
						
				</td>
				<td>${userList.userEmail }</td>
				
				<td> ${userList.userStatus }</td>
				
				<td>  <a href='editUser.form?userId=${userList.userId }'>Edit</a>  
					  
					   <c:if test="${userList.userStatus eq 'A'}">
					    	&nbsp;/ &nbsp;  
					  		<a href='deleteUser.form?userId=${userList.userId }'><font color="red">Delete</font></a>   
					  </c:if>
			    </td>
		 	 </tr>		
		   </c:forEach>
							   
		</table>

</body>
</html>