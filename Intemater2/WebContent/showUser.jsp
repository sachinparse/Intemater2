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
<title>Show User</title>
<%@ include file="genericinclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="js/stdlib/jquery-1-9-1-min.js"></script>
<style>
	p{ 	font-family: "Times New Roman", Times, serif;
	font-style: oblique;
	font-size:15px;}
	footer {
    clear: both;
    position: relative;
    z-index: 10;
    height: 3em;
    margin-top: 30.5em;
    }
    
    footer{display: block;}
	.Footer {
    	background: none repeat scroll 0% 0% #3A3A3A;
    	background-color: #3A3A3A;
		background-image: none;
		background-repeat: repeat;
		background-attachment: scroll;
		background-position: 0% 0%;
		background-clip: border-box;
		background-origin: padding-box;
		background-size: auto auto;
    	color: #FFF;
    	padding-bottom: 30px;
    	clear: both;
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

a:link, a:visited {
    background-color: #088D79;
    color: white;
    padding: 10px 25px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
}
</style>

 
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
		div,h5 {
			align: left;
    		width: 100%;
    		height: 20px; 
    		/* border: 2px solid #73AD21; */
    		/* background:#ccc; */
    		
}
	</style>
</head>
<body align="center">

 <h5 align="center"> 
 
 			<a href="sendEmail.form">Send Email</a>
			<a href="showCustomers.form?recperpage=25">View Contacts</a>
			<a href="registerPage.form">Create Contact</a>
			<a href="showCategory.form">Category</a>
			<a href="saveMessage.form">Messages</a>
			<!-- <a href="showUsers.form?recperpage=25">Show Users</a> -->
			<a href="newUser.form">Create New User</a>
			<a href="changePassword.form">Change Password</a>
			<a href="logout.form">Logout</a>
			
			<!-- <a href="showCustomers.form?recperpage=25">View Contacts</a>
		    <a href="sendEmail.form">Send Email</a>
			<a href="register.jsp">Create Contact</a>
			<a href="newUser.jsp">Create User</a>
		    <a href="changePassword.jsp">Change Password</a>
		    <a href="logout.jsp">Logout</a> -->
  </h5>
		
		<h2 align="center"><u>User List</u></h2>
	<form name="showForm" action="showUsers.form" method="post">
		
		<!-- <input type="hidden" name="recperpage" value="1"/> -->
		<table class="responstable" align="center">
				<tr> <td>No of Records/Page : </td>
				<td> 
					 <select	name="recperpage" id="idrecperpage" onchange="this.form.submit()">
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
	<footer class="Footer">
		<p align="right">Copyright &copy; 2016 &middot; All Rights Reserved. Contact information: 
		<font color="blue">sachin.parse@gmail.com</font>.</p>
	</footer>
</body>
</html>
<%
}
%>