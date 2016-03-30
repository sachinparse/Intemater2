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
	<form name="showForm" action="showUsers.form" method="get">
		
		<!-- <input type="hidden" name="recperpage" value="1"/> -->
		<table class="responstable" align="center">
				<tr> <td>No of Records/Page : </td>
				<td> 
					 <select	name="recperpage" id="idrecperpage" onchange="this.form.submit()">
					    <%-- <option value="2" ${noOfRecordsPerPage==2?'selected':'' }>2</option> --%>
						<option value="25" ${noOfRecordsPerPage==25?'selected':'' }>25</option>
						<option value="50" ${noOfRecordsPerPage==50?'selected':'' }>50</option>
						<option value="100" ${noOfRecordsPerPage==100?'selected':'' }>100</option>
						<option value="200" ${noOfRecordsPerPage==200?'selected':'' }>200</option>
						<option value="300" ${noOfRecordsPerPage==300?'selected':'' }>300</option>
						<option value="500" ${noOfRecordsPerPage==500?'selected':'' }>500</option>
						<option value="800" ${noOfRecordsPerPage==800?'selected':'' }>800</option>
						<option value="1000" ${noOfRecordsPerPage==1000?'selected':'' }>1000</option>
						<option value="2000" ${noOfRecordsPerPage==2000?'selected':'' }>2000</option>
					</select>
				</td>
			</tr>
		</table>
		<br>

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
		<br>
		<table align="center">
		  <tr>
		  		 <td>Pages :</td>
			<c:forEach begin="1" end="${noOfPages}" var="i">
			      <td></td>
			      <td>
					<c:choose>
						<c:when test="${currentPage eq i}">
						    <a href="showUsers.form?currentPage=${i}&recperpage=${noOfRecordsPerPage}"><font color="red">${i}</font></a>
						</c:when>
						<c:otherwise>
							<a href="showUsers.form?currentPage=${i}&recperpage=${noOfRecordsPerPage}">${i}</a>
						</c:otherwise>
					</c:choose>
   				  </td>				
			</c:forEach>
		</tr>	
		</table>
	</form>
</body>
</html>