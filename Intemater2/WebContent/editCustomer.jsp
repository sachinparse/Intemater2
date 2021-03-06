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
input[type=submit] {
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

div,h5 {
	align: left;
    width: 100%;
    height: 20px;
    /* border: 2px solid #73AD21; */
    /* background:#ccc; */
}


h5 a:link, h5 a:visited {
    background-color: #088D79;
    color: white;
    padding: 10px 25px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
}


h5 a:hover, h5 a:active {
    background-color: #FF8000;
}

</style>

<script>
$(document).ready("onfocus", function() {
        $( "#idCustDob" ).datepicker({
            dateFormat : 'mm/dd/yy',
            changeMonth : true,
            changeYear : true,
            yearRange: '-100y:c+nn',
            maxDate: '-1d'
        });
});
</script>

	<h5 align="center" > 
	
			<a href="sendEmailPage.form">Send Email</a>
			<a href="showCustomers.form?recperpage=25">View Contacts</a>
			<a href="registerPage.form">Create Contact</a>
			<a href="showCategory.form">Category</a>
			<a href="msgReportPage.form">Message Report</a>
			<c:if test="${objUser.roll eq 'admin' }">
				<a href="showUsers.form?recperpage=25">Show Users</a>
				<a href="newUser.form">Create New User</a>
			</c:if>
			<a href="changePasswordPage.form">Change Password</a>
			<a href="logout.form">Logout</a>
			
			<!-- <a href="showCustomers.form?recperpage=25">View Contacts</a> |
		    <a href="sendEmail.form">Send Email</a> |
			<a href="register.form">Create Contact</a> |
			<a href="newUser.jsp">Create New User</a> |
		    <a href="changePassword.form">Change Password</a> |
		    <a href="showUsers.form?recperpage=25">Show Users</a> |
		    <a href="showCategory.form">Category</a> |
		    <a href="logout.form">Logout</a> |
		    <a href="saveMessage.form">Save Message</a> -->
	</h5>
<!-- <header class="main-header" role="banner">
  <img src="images/Contact-Banner.png" alt="Banner Image" align="center"/>
</header> -->
    
</head>
<body>
		
		<h2 align="center"><u>Edit Contact</u></h2>

	<form name="homePage" action="updateCustomer.form" method="post">
		
		<input type="hidden" name="currentPage" value="0"/>
		
		<input type="hidden" name="custId" value="${objCustomer.custId}">
		
		<table align="center" border="0">
		
		 <tr><td><h3>Customer Details</h3></td></tr>	
			<tr> <td></td>
				<td >Name</td><td>:</td><td><input type="text" name="custName" placeholder="Enter Customer Name" value="${objCustomer.name}"></td> </tr>
				
			<tr> <td></td>
				<td>Mobile No.</td><td>:</td> <td><input type="text" name="custMobile1" placeholder=" 1st Mobile " value="${objCustomer.mobile1}"></td>
				                              <td><input type="text" name="custMobile2" placeholder=" 2nd Mobile " value="${objCustomer.mobile2}"></td> </tr>
				
			<tr> <td></td>
				<td>Occupation</td><td>:</td><td><input type="text" name="custWork" placeholder="Occupation" value="${objCustomer.work}"> </td> </tr>
				
			<tr> <td></td>
				<td>Email Id</td><td>:</td><td><input type="text" name="custEmail" placeholder=" Email Id" value="${objCustomer.email}"></td> 
				<td align="center">Gender</td><td>:</td><td>
					 <select class="select-style gender" name="custGender">
				            <option value="select">I am..</option>
				            <option value="male" ${objCustomer.gender=="male"? 'selected':'' }>Male</option>
				            <option value="female" ${objCustomer.gender=="female"? 'selected':'' }>Female</option>
				            <option value="others" ${objCustomer.gender=="others"? 'selected':'' }>Other</option>
				     </select>
				</td></tr>
				
			<tr> <td></td>
				<td>PAN No.</td><td>:</td><td><input type="text" name="custPan" placeholder=" PAN Number"  value="${objCustomer.pan}"></td> 
				<td align="center">Date of Birth</td><td>:</td><td><input class="birthday" type="text" id="idCustDob" name="custDob" placeholder="YYYY-MM-DD" value="${fn:split(objCustomer.dob, ' ')[0]}"></td><td> </td> </tr>
																												
			<tr> <td></td>
				<td>Address</td><td>:</td><td><input type="text" name="custAddress" placeholder=" Address" value="${objCustomer.address}"> </td>
				<td align="center">Status</td><td>:</td>
				<td>
				
				   <select class="select-style gender" name="status">
				            <option value="select">Select Status</option>
				            <option value="A" ${objCustomer.status=="A"? 'selected':'' }>Active</option>
				            <option value="I" ${objCustomer.status=="I"? 'selected':'' }>In-Active</option>
				   </select> 
			    </td>
			</tr>
				
		<!-- <tr><td><div style="color:#B914FF">Bank Details</div></td></tr> -->	
		<tr><td><h3>Bank Details</h3></td></tr>
		
		 	<tr> <td></td>
				<td>Bank Name</td><td>:</td><td><input type="text" name="bankName" placeholder=" Bank Name" value="${objCustomer.bankName}"> </td>
			    <td align="center">Branch</td><td>:</td><td><input type="text" name="branch" placeholder=" Branch" value="${objCustomer.bankBranch}"> </td> </tr>
			
			<tr> <td></td>
				<td>Account No. </td><td>:</td><td><input type="text" name="accNo" placeholder=" Account Number" value="${objCustomer.acc}"></td> </tr>    
		
			<tr> <td></td>
				<td>IFSC</td><td>:</td> <td><input type="text" name="ifsc" placeholder=" IFSC Code" value="${objCustomer.ifsc}"></td>
				<td align="center">MICR</td><td>:</td> <td><input type="text" name="micr" placeholder=" MICR Code" value="${objCustomer.micr}"></td> </tr>
				<tr></tr><tr></tr>
				<tr> <td></td>
					<td>Select Category</td><td>:</td> 
					<td>
						<select name="category">
								<option value="0">Select Category</option>
																
							<c:forEach var="category" items="${objlstCategory}">
							  	<option value="${category.categoryId}" ${category.categoryId== objCustomer.category? 'selected':'' }>${category.categoryName}</option>
							</c:forEach>											
						</select>
					</td>
				
				</tr>
				
				<tr> <td></td><td></td><td></td>
					<td colspan="4" align="center"> <input type="submit" name="update" value="Update Customer"> </td> </tr>
		</table>
	
	
		
	</form>
	<div>
		<p align="right">Copyright &copy; 2016 &middot; All Rights Reserved. Contact information: 
		<font color="blue">sachin.parse@gmail.com</font></p>
	</div>
</body>
</html>
<%
}
%>