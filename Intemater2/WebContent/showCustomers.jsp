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

<script type="text/javascript">

$(document).ready(function(){
	
	$("#idCategoryId").change(function(){
		
		 if(($("#idCategoryId").val() !=0) ){
			 this.form.submit();
		 }
		
		});
	
	
  });


</script>
</head>
<body>

			
		<form name="showCustomerForm" action="showCustomers.form" method="post">
			
			<input type="hidden" name="currentPage" value="0"/>
			
			<center>
			</center>
			<table align="left">
			  <tr>
			     <td width="400px"></td>
			     <td width="100px" align="left">
					<select name="categoryId" id="idCategoryId">
								<option value="0">Select Category</option>					
					    <c:forEach var="category" items="${objlstCategory}">
							  	<option value="${category.categoryId}">${category.categoryName}</option>
					    </c:forEach>
					</select>
				</td>
			  
			  <td width="200px"></td>
			  
			    <td  align="right"></td>
			    <td>Records/Page : </td>
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
		
		  <!-- show the customer data -->
			
		
		
		
		</form>	
			
			
			
</body>
</html>