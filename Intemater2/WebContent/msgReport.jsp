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
<title>Intemeter2: Message Report</title>
<%@ include file="genericinclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="js/stdlib/jquery-1-9-1-min.js"></script>

<!-- <link rel="stylesheet" href="css/loginCss.css" /> -->

<link rel="stylesheet"	href="development-bundle/themes/base/jquery.ui.all.css">
<script src="development-bundle/ui/jquery.ui.core.js"></script>
<script src="development-bundle/ui/jquery.ui.widget.js"></script>
<script src="development-bundle/ui/jquery.ui.mouse.js"></script>
<script src="development-bundle/ui/jquery.ui.button.js"></script>
<script src="development-bundle/ui/jquery.ui.draggable.js"></script>
<script src="development-bundle/ui/jquery.ui.position.js"></script>
<script src="development-bundle/ui/jquery.ui.resizable.js"></script>
<script src="development-bundle/ui/jquery.ui.button.js"></script>
<script src="development-bundle/ui/jquery.ui.dialog.js"></script>

<script src="development-bundle/ui/jquery.ui.effect.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="development-bundle/demos/demos.css">

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
			
			 $("#idSubmit").click(function(){
			    	
		    	 if ($("#projectName").val()=="") {
					
		    		 alert("Please, select the Project..!");
		    		 event.preventDefault();
				}
		    	 
		     });
			 
			// default date
		     $(function() {
		    	 if ("${startDate }".length == 0) {
						
		    		    var d= new Date();
		      		    d.setDate(d.getDate() - 7);
		        		$( "#datepickerStart" )
		        					  		.datepicker({
		        							  	  maxDate: new Date(), 
		        						    	  dateFormat: 'yy-mm-dd'	  
		        					   		})
		    			    				.datepicker("setDate", d);
		        		
		        		var d2= new Date();
		      		    d2.setDate(d2.getDate() - 1);
		        		$( "#datepickerEnd" )
		        							.datepicker({
		    				  							  maxDate: new Date(), 
		    			      							  dateFormat: 'yy-mm-dd'	  
		    		   						})
		    								.datepicker("setDate", d2);
		     		 
		 		} else {

		    		var d= new Date();
		   		    d.setDate(d.getDate() - 6);
		     		$( "#datepickerStart" )
		     					  		.datepicker({
		     							  	  maxDate: new Date(), 
		     						    	  dateFormat: 'yy-mm-dd'	  
		     					   		})
		 			    				.datepicker("setDate", "${startDate }");
		     		
		     		$( "#datepickerEnd" )
		     							.datepicker({
		 				  							  maxDate: new Date(), 
		 			      							  dateFormat: 'yy-mm-dd'	  
		 		   						})
		 								.datepicker("setDate", "${endDate }");
		 		}
		  		 
		  		    /* var d= new Date();
		  		    d.setDate(d.getDate() - 6);
		    		$( "#datepickerStart" )
		    					  		.datepicker({
		    							  	  maxDate: new Date(), 
		    						    	  dateFormat: 'yy-mm-dd'	  
		    					   		})
					    				.datepicker("setDate", d);
		    		
		    		$( "#datepickerEnd" )
		    							.datepicker({
						  							  maxDate: new Date(), 
					      							  dateFormat: 'yy-mm-dd'	  
				   						})
										.datepicker("setDate", new Date()); */
			  });
			 
		    
		     $( "#datepickerStart","#datepickerEnd" ).focusout(function(){
		    	
		    	 var d= new Date();
		    	 
		    	 if ($( "#datepickerStart" ).val() > $( "#datepickerEnd" ).val()) {
					
		    		 alert("Start Date should not be greater than End Date..!");
				} else {

				}
		     });
		});

</script>


</head>
<body>

	<h5 align="center"> 
			<a href="sendEmailPage.form">Send Email</a>
			<a href="showCustomers.form?recperpage=25">View Contacts</a>
			<!-- <a href="register.form">Create Contact</a> -->
			<a href="showCategory.form">Category</a>
			<!-- <a href="msgReport.jsp">Message Report</a> -->
			<c:if test="${objUser.roll eq 'admin' }">
				<a href="showUsers.form?recperpage=25">Show Users</a>
				<a href="newUser.form">Create New User</a>
			</c:if>
			<a href="changePasswordPage.form">Change Password</a>
			<a href="logout.form">Logout</a>
			
		
	</h5>
	
 <form action="#" method="post">
  <center>
  		<caption><h2><b><u>Sent Message Report</u></b></h2></caption>
	<table align="center">
		<tr>
		    <td  width="31%" valign="top" nowrap bgcolor="#C5EFFA"><span class="regtext">Start: &nbsp;&nbsp;</span>
                <input type="text" name="startDate" id="datepickerStart" readonly value="${startDate }"></td>
            <td  width="17%" valign="top" nowrap bgcolor="#C5EFFA"><span class="regtext">End:</span>
                <input type="text" name="endDate" id="datepickerEnd" readonly></td>
            <td width="14%" valign="top" bgcolor="#C5EFFA"><input type="submit" name="submit" id="idSubmit" value="Go"/></td>
		</tr>
	</table>
  </center>
 </form>		







</body>
</html>
<%
}
%>