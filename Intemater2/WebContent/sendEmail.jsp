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
<title>Send Message</title>
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
    /* background:#26A2E0; */
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
</head>
<body align="center">

     <h5> 
			<!-- <a href="sendEmail.jsp">Send Email</a> -->
			<a href="showCustomers.form?recperpage=25">View Contacts</a>
			<!-- <a href="register.form">Create Contact</a> -->
			<a href="showCategory.form">Category</a>
			<a href="saveMessage.form">Messages</a>
			<a href="showUsers.form?recperpage=25">Show Users</a>
			<a href="newUser.form">Create New User</a>
			<a href="changePasswordPage.form">Change Password</a>
			<a href="logout.form">Logout</a>
	</h5>
	
		<form action="sendEmail.form" method="post">
		
		<table>
			<tr>
			 <td>1</td><td>2</td> <td>3</td>
			</tr>
		</table>

		<table>
			<tr>
				<td>4</td><td>5</td><td>6342</td>
			</tr>
		</table>



		<table align="center" border="1">
		  <tr>
		   <td width="40%" >
			<div align="left" style="width: 50%; height:40%">
							<select name="categoryId" id="idCategoryId">
								<option value="0">Select Category</option>					
					    		<c:forEach var="category" items="${objlstCategory}">
							  		<option value="${category.categoryId}" ${category.categoryId==categoryId ? 'selected':'' }>${category.categoryName}</option>
					    		</c:forEach>
							</select>
			</div>
		   </td>
		   <td width="60%" rowspan="">	
			<div align="right">
				 <table>
					<tr>
						<td>Subject</td><td>:</td>
						<td><input type="text" name="subject" id="idSubject" size="50"> </td>							 
					</tr>
					
					<tr style="overflow:atuo">
						<td>Message</td><td>:</td>
						   <td><!-- <input type="textarea" name="message" id="idMessage" height="100px">  -->
						    <textarea name="Text1" cols="49" rows="8"></textarea>
						</td>							 
					</tr>
									 
				 </table>
			</div>
		   </td>
		  </tr>
		  
		  <tr>
		    <td>
		       <div align="left" style="width: 50%; height:10%">
		          &nbsp;
		       </div>
		      </td>
		  	
		  	<td>
		  		<div align="right" style="width: 50%; height:10%">
						<input type="submit" name="submit" value="Send Emails" id=idSubmit"/>		  		
		  		</div>
		  	</td>
		  </tr>
		  <tr>
		   <td>
		  	<div align="left" style="width: 80%; height:60%">
		          <center>
		            <h3>Email has sent Successfully :</h3>
		          </center>
		          
				  <table align="center">
		            <tr>
		              <th>Sr. No.</th>
		              <th>Cust. ID</th>
		              <th>Cust. Name</th>
		              <th>Email Status</th>
		            </tr>
		            
		            
		          </table>
		  	</div>
		   </td>
		  </tr>	
		</table>
		
		
		
		
		
		

		</form>

</body>
</html>
<%
}
%>