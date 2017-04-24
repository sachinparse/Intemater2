<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if (null == session.getAttribute("objUser")) {
		response.sendRedirect("login.jsp");
	} else {
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />

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

<style>
html, body { height: 70%; width: 100%; padding: 0; margin: 30; }
div { width: 40%; height: 30%; float: left; }
#div1 {  }
#div2 {  }
#div3 { }
#div4 { }
.style1 {
	color: #006699;
	font-weight: bold;
}
</style>

<script>
	$(document).ready(function(){
		
		$("#idCategoryId").change(function(){
			$('#idSemdEmailForm').attr('action', 'getAllCustomers.form'); 
			this.form.submit();	 
		});
		
		$('#idButton').click(function(){
			$('#idSemdEmailForm').attr('action', 'sendEmail.form');
			this.form.submit();
		});
		
		$('#idSelectAllCust').click(function(event) {
			//alert("clicked");
			if(	$('#idSelectAllCust').prop("checked")==true){
				$('[id^=idCust_]').prop("checked",true);	
			}else{
				$('[id^=idCust_]').prop("checked",false);
			}
			
		});
		
		$(document).on("keydown", disableF5);
		
		
	});

</script>

<script>
	function disableF5(e) { 
		if ((e.which || e.keyCode) == 116) 
			e.preventDefault(); 
	};


</script>
</head>

<body>
	   <!-- Link : http://www.raistudies.com/spring/spring-mvc/ajax-spring-mvc-3-annonations-jquery/ -->
	   
	<h5 align="center"> 
			<!-- <a href="sendEmail.jsp">Send Email</a> -->
			<a href="showCustomers.form?recperpage=25">View Contacts</a>
			<a href="registerPage.form">Create Contact</a>
			<a href="showCategory.form">Category</a>
			<a href="saveMessage.form">Messages</a>
			<a href="showUsers.form?recperpage=25">Show Users</a>
			<a href="newUser.form">Create New User</a>
			<a href="changePasswordPage.form">Change Password</a>
			<a href="logout.form">Logout</a>
	</h5>	
	
	<!-- <form action="sendEmail.form" method="post"> -->
	<form action="" name="semdEmailForm" id="idSemdEmailForm" method="post">
<div id="div1" align="center">
    <br /><br /><br /><br />
							<select name="categoryId" id="idCategoryId">
								<option value="0">Select Category</option>					
					    		<c:forEach var="category" items="${objlstCategory}">
							  		<option value="${category.categoryId}" ${category.categoryId==categoryId ? 'selected':'' }>${category.categoryName}</option>
					    		</c:forEach>
							</select>
</div>
<div id="div2">
      <table width="360" border="0">
  <caption>
    Email
  </caption>
  <tr>
    <td width="66" height="22">Subject</td>
    <td width="10">:</td>
    <td width="390"><input type="text" name="subject" id="idSubject" size="49" value="${subject}"/></td>
  </tr>
  <tr>
    <td>Message</td>
    <td>:</td>
    <td>  <textarea name="message"  id="idMessage" cols="49" rows="8" value="${messageBody}"></textarea> </td>
  </tr>
</table>

</div>
<br /><br />
<div id="div3" style='height: 370px; overflow: auto;overflow-x:hidden;'>
  <table width="100%" border="1" align="left" id="idCustTable">
  <caption>
    List
  </caption>
  <tr>
    <td width="45" height="47"><span class="style1">Sr. No. </span></td>
    <td width="65"><span class="style1">Cust. ID </span></td>
    <td width="296"><span class="style1"><input type="checkbox" name="selectAllCust" id="idSelectAllCust" ${status eq 'N'?'disabled':'' } > Cust. Names </span></td>
    <td width="86"><span class="style1">Email Status </span></td>
  </tr>
  
  <c:forEach var="cust" items="${listOfCustomer }" varStatus="rec">
  
    <tr>
  		<td>
			<c:out value="${rec.index+1}"/>
		</td>
		<td align="center">
  			${cust.custId}
  		</td>
		<td>
			<input type="checkbox" name="emp" id="idCust_${cust.custId }" class="test" value="${cust.custId}">
			${cust.name} 
  		</td>
  		<td>
  			
  		</td>
    </tr>
  
  </c:forEach>
 
</table>

</div>
<div id="div4"  align="center">
    <br /><br /><br />
				<input type="button" name="button" value="Send Emails" id="idButton"/>
</div>
	</form>
</body>
</html>

<%
}
%>