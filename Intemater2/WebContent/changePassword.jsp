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
<title>Change Password...</title>
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

<style>
	.button2 {
	   background-color: #008CBA; /* Blue */
	   font-size: 14px;			
	   padding: 5px 30px;  
	   border-radius: 4px;  
	   border: none;
	}
	
	.button1 {
	   background-color:  #e7e7e7; color: black; /* Blue */
	   font-size: 16px;			
	   padding: 3px 30px;  
	   border-radius: 4px;
	   border: none;  
	}

	.textbox {
	   background-color: #e8f8f5; /* Blue */
	   font-size: 16px;			
	   padding: 5px 30px;  
	   border-radius: 4px;  
	   border: none;
	}
	div {
	   width: 450px;
	   height: 200px;
	   background-color:  #fdfefe ;
	   box-shadow: 10px 10px 80px #888888;
	   
    }
	
</style>

<script type="text/javascript">

	$(document).ready(function(){
		
		$("#idClearBtn").click(function(){
			
			$("#idCurrentPassword").val("");
			$("#idNewPassword").val("");
			$("#idConfirmPassword").val("");
		});
		
	});

</script>

</head>
<body>

	<h5 align="center"> 
			<a href="sendEmailPage.form">Send Email</a>
			<a href="showCustomers.form?recperpage=25">View Contacts</a>
			<a href="registerPage.form">Create Contact</a>
			<a href="showCategory.form">Category</a>
			<a href="saveMessage.form">Messages</a>
			<c:if test="${objUser.roll eq 'admin' }">
				<a href="showUsers.form?recperpage=25">Show Users</a>
				<a href="newUser.form">Create New User</a>
			</c:if>
			<!-- <a href="changePassword.jsp">Change Password</a> -->
			<a href="logout.form">Logout</a>
	</h5>
<br>
		<form align="center" action="changePassword.form" method="post">
                <center><b>
                
                <c:if test="${message ne null or message ne '' }">
					${message }<br>
			    </c:if>
                
                <h2><u><font color= '#566573'>Change Password</font></u></h2></b><br>
          <div align="center">      
          <br>
                <table border="0" align="center">
                	
                	<tr>
                		<td>Current Password</td> <td>:</td> <td><input type="password" class='textbox' id="idCurrentPassword" name="currentPassword"/></td>
                	</tr>
                	<tr>
                		<td>New Password</td> <td>:</td> <td><input type="password" class='textbox' id="idNewPassword" name="newPassword"/></td>
                	</tr>
                	<tr>
                		<td>Confirm Password</td> <td>:</td> <td><input type="password" class='textbox' id="idConfirmPassword" name="confirmPassword" /></td>
                	</tr>
                	<tr>
                		<td>&nbsp;</td><td>&nbsp;</td>
                	</tr>
                	<tr>
                		<td align="right"> <input type="button" class='button1' name="clear" id="idClearBtn" value="Clear"/></td>
                		<td></td>
                		<td align="center"> <input type="submit" class='button2' name="submit" id="idSaveBtn" value="Save"/></td>
                	</tr>
                	
                </table>
             </div>   
          </center>      
        </form>
    
</body>
</html>
<%
}
%>