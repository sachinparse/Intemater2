<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<%@ include file="genericinclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="js/stdlib/jquery-1-9-1-min.js"></script>

<link rel="stylesheet" href="css/loginCss.css" />

<script type="text/javascript">

	$(document).ready(function(){
		
	    $('#idUserId').bind('keyup paste', function(){ 
		        this.value = this.value.replace(/[^0-9]/g, '');
		});
		
		$("#idButton").click(function(){
			
			if($("#idUserId").val().trim().length != 0 && $("#idPassword").val().trim().length == 0){
				alert("Please Enter Password...");
			}else if($("#idUserId").val().trim().length == 0 && $("#idPassword").val().trim().length != 0){
				alert("Please Enter User ID...");
			}else if($("#idUserId").val().trim().length == 0 && $("#idPassword").val().trim().length == 0){
				alert("Please Enter User ID and Password...");
			}else{
				$('#idLoginForm').submit();
			}
		});
	});

</script>

<style>
	html,
body {
   margin:0;
   padding:0;
   height:100%;
}
#container {
   min-height:100%;
   position:relative;
}
#header {
   background:#ff0;
   padding:10px;
}
#body {
   padding:10px;
   padding-bottom:60px;   /* Height of the footer */
}
#footer {
   position:absolute;
   bottom:0;
   width:95%;
   height:60px;   /* Height of the footer */ 
   text-align: right ;
   font-size: 15px ;
   font-family: 'Lato' ;
}
</style>

</head>
<div id="container">
 <div id="body">
<body>

	<div>
			
	</div>

	<div class="login">
		<div class="heading">
				
				<c:if test="${message ne null or message ne '' }">
					<font color="red" align="center"><h3>${message }</h3></font><br>
			</c:if>
		
		
			<h2>Sign in</h2>
			<form name="loginForm" id="idLoginForm" action="LoginAuthenticate.form" method="post">

				<div class="input-group input-group-lg">
					<span class="input-group-addon"><i class="fa fa-user"></i></span> 
					<input type="text" name="userId" id="idUserId" class="form-control" placeholder="User Id">
				</div>

				<div class="input-group input-group-lg">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span> 
					  <input type="password" name="password" id="idPassword" class="form-control" placeholder="Password">
				</div>

				<button type="button" id="idButton" name="login" class="float">Login</button>
			</form>
		</div>
	</div>
</body>
 </div>
    <div id="footer">
		Mars Enterprises Pvt. Ltd. All Rights are Reserved &#169; 2017 
		&nbsp;&nbsp;Contact: sachin.parse@gmail.com
	</div>
</div>
</html>