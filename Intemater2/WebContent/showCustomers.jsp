<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customers List</title>

<%@ include file="genericinclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="js/stdlib/jquery-1-9-1-min.js"></script>

<style>
body {
	background-color: white;
}

h2 {
	color: #808000;
}

h3 {
	color: #2E9AFE;
}

p {
	font-family: "Times New Roman", Times, serif;
	font-style: oblique;
	font-size: 15px;
}

input[type=text],textarea {
	-webkit-transition: all 0.30s ease-in-out;
	-moz-transition: all 0.30s ease-in-out;
	-ms-transition: all 0.30s ease-in-out;
	-o-transition: all 0.30s ease-in-out;
	outline: none;
	padding: 3px 0px 3px 3px;
	margin: 5px 1px 3px 0px;
	border: 1px solid #DDDDDD;
}

input[type=text]:focus,textarea:focus {
	box-shadow: 0 0 5px rgba(81, 203, 238, 1);
	padding: 3px 0px 3px 3px;
	margin: 5px 1px 3px 0px;
	border: 1px solid rgba(81, 203, 238, 1);
}

select {
	padding: 5px 15px;
	background: #ccc;
	/*  background:#26A2E0; */
	border: 0 none;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	hight: "15";
}

input[type=submit] {
	padding: 5px 15px;
	/* background:#ccc; */
	background: #8AB6CB;
	border: 0 none;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	hight: "15";
}

.main-header {
	text-align: center;
}

div,h5 {
	align: left;
	width: 100%;
	height: 20px;
	/* border: 2px solid #73AD21; */
	background: #ccc;
}
</style>
<h5 align="left">
	<a href="showCustomer.form">View Contacts</a> | <a
		href="sendEmail.form">Send Email</a> | <a href="register.form">Create
		Contact</a> | <a href="newUser.jsp">Create New User</a> | <a
		href="changePassword.form">Change Password</a> | <a
		href="showUsers.form?recperpage=25">Show Users</a> | <a
		href="showCategory.form">Category</a> | <a href="logout.form">Logout</a>
	| <a href="saveMessage.form">Save Message</a>
</h5>

</head>
<body>

			
		<form name="showCustomerForm" action="showCustomers.form" method="post">
			
			
			
		
		
		
		</form>	
			
			
			
</body>
</html>