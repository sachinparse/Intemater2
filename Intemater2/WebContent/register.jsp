<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	if (null == session.getAttribute("objUser")) {
		response.sendRedirect("login.jsp");
	} else {
%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<style>
body {background-color:white;}
h2   {color:#808000;}
h3   {color:#2E9AFE;}
p{ 	font-family: "Times New Roman", Times, serif;
	font-style: oblique;
	font-size:15px;}

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
    hight:"15";
}
input[type=button] {
    padding:5px 15px; 
    /* background:#ccc; */ 
    background:#8AB6CB;
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 5px;
    border-radius: 5px; 
    hight:"15";
}

.main-header {
  text-align: center;

}

div {
	align: left;
    width: 100%;
    height: 20px;
    /* border: 2px solid #73AD21; */
    background:#ccc;
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
		
		$( window ).load(function() {
			$("#idCustName").val("");
		});
		
		$("#idSave").click(function(){
			
			if($("#idCustName").val().trim() != null){
				$('#idRegisterForm').submit();
		    }
		});
		
	});

	function disableF5(e) { 
		if ((e.which || e.keyCode) == 116){ 
			e.preventDefault(); 
		}
	};

</script>

 <script type="text/javascript">
    $(function () {
        $(document).keydown(function (e) {
            return (e.which || e.keyCode) != 116;
        });
    });
</script>

</head>
<body align="center" onkeydown="return (event.keyCode != 116)">

	<h5> 
			<a href="sendEmailPage.form">Send Email</a>
			<a href="showCustomers.form?recperpage=25">View Contacts</a>
			<!-- <a href="register.form">Create Contact</a> -->
			<a href="showCategory.form">Category</a>
			<a href="msgReportPage.form">Message Report</a>
			<c:if test="${objUser.roll eq 'admin' }">
				<a href="showUsers.form?recperpage=25">Show Users</a>
				<a href="newUser.form">Create New User</a>
			</c:if>
			<a href="changePasswordPage.form">Change Password</a>
			<a href="logout.form">Logout</a>
			
		
	</h5>
<header class="main-header" role="banner">
  <img src="images/Contact-Banner.png" alt="Banner Image" align="center"/>
</header>

		
		<h2 align="center"><u>Create New Contact</u></h2>
		<font color="blue">
			<c:if test="${message ne null or message ne '' }">
					${message }<br>
			</c:if>
		</font>
	<form name="homePage" id="idRegisterForm" action="register.form" method="post">
		
		<input type="hidden" name="currentPage" value="0"/>
		
		<table align="center" border="0">
		
		 <tr><td><h3>Customer Details</h3></td></tr>	
			<tr> <td></td>
				<td >Name</td><td>:</td><td><input type="text" id="idCustName" name="custName" placeholder="Enter Customer Name"></td> </tr>
				
			<tr> <td></td>
				<td>Mobile No.</td><td>:</td> <td><input type="text" name="custMobile1" placeholder=" 1st Mobile " ></td>
				                              <td><input type="text" name="custMobile2" placeholder=" 2nd Mobile " ></td> </tr>
				
			<tr> <td></td>
				<td>Occupation</td><td>:</td><td><input type="text" name="custWork" placeholder="Occupation"> </td> </tr>
				
			<tr> <td></td>
				<td>Email Id</td><td>:</td><td><input type="text" name="custEmail" placeholder=" Email Id" ></td> 
				<td align="center">Gender</td><td>:</td><td>
					 <select class="select-style gender" name="custGender">
				            <option value="select">I am..</option>
				            <option value="male">Male</option>
				            <option value="female">Female</option>
				            <option value="others">Other</option>
				     </select>
				</td></tr>
				
			<tr> <td></td>
				<td>PAN No.</td><td>:</td><td><input type="text" name="custPan" placeholder=" PAN Number" ></td> 
				<td align="center">Date of Birth</td><td>:</td><td><input class="birthday" type="text" name="custDob" placeholder="YYYY-MM-DD" ></td><td>(YYYY-MM-DD)</td></tr>
			
			<tr> <td></td>
				<td>Address</td><td>:</td><td><input type="text" name="custAddress" placeholder=" Address"> </td> </tr>
			
		<!-- <tr><td><div style="color:#B914FF">Bank Details</div></td></tr> -->	
		<tr><td><h3 align="left">Bank Details</h3></td></tr>
		
		 	<tr> <td></td>
				<td>Bank Name</td><td>:</td><td><input type="text" name="bankName" placeholder=" Bank Name"> </td>
			    <td align="center">Branch</td><td>:</td><td><input type="text" name="branch" placeholder=" Branch"> </td> </tr>
			
			<tr> <td></td>
				<td>Account No. </td><td>:</td><td><input type="text" name="accNo" placeholder=" Account Number"></td> </tr>    
		
			<tr> <td></td>
				<td>IFSC</td><td>:</td> <td><input type="text" name="ifsc" placeholder=" IFSC Code"></td>
				<td align="center">MICR</td><td>:</td> <td><input type="text" name="micr" placeholder=" MICR Code"></td> </tr>
				<tr></tr><tr></tr>
				<tr> <td></td>
					<td>Select Category</td><td>:</td> 
					<td>
						<select name="category">
								<option value="0">Select Category</option>
								<!-- <option value="sale">Sale</option>
								<option value="service">Service</option>
								<option value="excise">Excise</option>
								<option value="income">Income</option>
								<option value="wealth">Wealth</option>
								<option value="frined">Friend</option>	 -->
								
							<c:forEach var="category" items="${objlstCategory}">
							  	<option value="${category.categoryId}">${category.categoryName}</option>
							</c:forEach>											
						</select>
					</td>
				
				</tr>
				
				<tr> <td></td><td></td><td></td>
					<td colspan="4" align="center"> <input type="button" id="idSave" name="save" value="Save Contact"> </td> </tr>
		</table>
	
	
		
	</form>
	<div>
		<p align="right">Copyright &copy; 2016 &middot; All Rights Reserved. Contact information: 
		<!-- <a href="mailto:sachin.parse@gmail.com"> --><font color="blue">sachin.parse@gmail.com</font><!-- </a> -->&nbsp;</p>
	</div>
</body>
</html>
<%
}
%>