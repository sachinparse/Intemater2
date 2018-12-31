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
<title>Edit User</title>
<%@ include file="genericinclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="js/stdlib/jquery-1-9-1-min.js"></script>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
body {background-color:white;}
h2   {color:#2E9AFE;}
h3   {color:#2E9AFE;}
b    {color:#A4A4A4;}

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
}
input[type=submit] {
    padding:5px 15px; 
    /* background:#ccc; */ 
    background:#8AB6CB;
    border:0 none;
    cursor:pointer;
    -webkit-border-radius: 5px;
    border-radius: 5px; 
}

.main-header {
  text-align: center;

   /* height: 10px;
  width: 28px;
  position: fixed;
  top: 0;
  left: 80%;
  margin-left: -499px; */
}
p{ 	font-family: "Times New Roman", Times, serif;
	font-style: oblique;
	font-size:15px;}
div,h5 {
	align: left;
    width: 100%;
    height: 20px;
    /* border: 2px solid #73AD21; */
    background:#ccc;
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

<script type="text/javascript">
		
function activateUpdateButton(id){

	  document.getElementById("idUpdateButton").disabled=false;
	
	 // getting values from the perticular row of the table from the showCategory.jsp
	   
	  //newemaildetailsid.value=id;
	 document.getElementById("idCategoryId").value=id;
              var rows = document.getElementById("mytable2").rows;
                    
              for(var i = 0, ceiling = rows.length; i < ceiling; i++) {
                  rows[i].onclick = function() {

		            for(var j=0;j<2;j++){
		            	
		            	idCategoryId.value=this.cells[0].innerHTML;
		            	idCategoryName.value=this.cells[1].innerHTML;
		            	
					}
               };
     }
	 
}

function activateSaveButton(){
	  
	  var text1=document.getElementById("idCategoryName");
	  
	  if(document.getElementById("idUpdateButton").disabled==true){
	       
		  if(text1.value.trim().length!=0 ){
		    
			       document.getElementById("idAddButton").disabled=false;
		  }else{
			  	   document.getElementById("idAddButton").disabled=true;
		  }
	  }
}

   function deleteCategory(id){
	   alert("Category No. :"+id+" will be deleted");
	   document.getElementById("idDeleteId").value=id;
	   $('#idCategoryForm').prop('action', 'deleteCategory.form');
	   $('#idCategoryForm').submit();
   }





$(document).ready(function(){

	$("#idAddButton").click(
			function(){
				
				$('#idCategoryForm').prop('action', 'addCategory.form');
    });
	
	$("#idUpdateButton").click(
			function(){
				
				$('#idCategoryForm').prop('action', 'updateCategory.form');
    });
	
	$("#idDeleteButton").click(
			function(){
				
				$("#idDeleteVar").val=
				$('#idCategoryForm').prop('action', 'deleteCategory.form');
    });
 
	 
	 
});


</script>
<style type="text/css">
th {
	        background-color: #4CAF50;
	        color: white;
	    }
	    
	    tr:nth-child(even) {background-color: #f2f2f2}
	
		h2   {color: #6a6363;}
		footer {
    		clear: both;
    		position: relative;
    		z-index: 10;
    		height: 3em;
    		margin-top: 30.5em;
    	}
</style>		
</head>
<body>

 <h5 align="center"> 
 			
 			<a href="sendEmailPage.form">Send Email</a>
			<a href="showCustomers.form?recperpage=25">View Contacts</a>
			<a href="registerPage.form">Create Contact</a>
			<!-- <a href="showCategory.form">Category</a> -->
			<a href="msgReportPage.form">Message Report</a>
			<c:if test="${objUser.roll eq 'admin' }">
				<a href="showUsers.form?recperpage=25">Show Users</a>
				<a href="newUser.form">Create New User</a>
			</c:if>
			<a href="changePasswordPage.form">Change Password</a>
			<a href="logout.form">Logout</a>
			
			<!-- <a href="newUser.form">View Contacts</a>
		    <a href="newUser.form">Send Email</a>
			<a href="register.form">Create Contact</a>
			<a href="newUser.form">Create User</a>
		    <a href="newUser.form">Change Password</a>
		    <a href="showUser.form">Show Users</a>
		    <a href="newUser.form">Logout</a> -->
</h5>
	
	<form name="categoryForm" id="idCategoryForm" action="actionCategory.form" method="post">
	
	
		<input type="hidden" name="deleteId" vlaue="" id="idDeleteId"/>
	
	
		 <table class=responstable id="mytable1" align="center">
	 
	 		<!-- <tr>
			   <th>Category ID</th> <th>Category Name</th> <th>Save / Update</th>
			</tr> -->
	 		 <tr>
	      			<td> <input type="text" name="categoryId" id="idCategoryId" placeholder="Category ID" value="" readOnly/>  </td>
	      			<td> <input type="text" name="categoryName" id="idCategoryName" placeholder="Category Name" onblur="activateSaveButton()"/>  </td>
	  	 
	  	  			<td colspan="2" align="center"> <input type="submit" name="commonButton" id="idAddButton" disabled value="Add"/> / 
	  	                                  <input type="submit" name="commonButton" id="idUpdateButton" disabled value="Update"/> </td>
	  </tr>
	 </table>
	
	
		<table  class="responstable" align="center" id="mytable2" border="0">
		
			<c:forEach var="categoryList" items="${listOfCategory}">
			  <tr align="center">	
				<td  style="width: 160px;"  align="left">${categoryList.categoryId }</td>
				<td  style="width: 210px;"  align="left">${categoryList.categoryName }</td>
				
				<td align="center"  style="width: 110px;"> 
				                <input type="button" name="editButton" value="Edit" id="${categoryList.categoryId }" onClick="activateUpdateButton(this.id)"/>
				                <input type="button" name="deleteButton" value="Delete" id="${categoryList.categoryId }" onClick="deleteCategory(this.id)"/>
				</td>
					  
					   
			    </td>
		 	 </tr>		
		   </c:forEach>
							   
		</table>

	</form>
</body>
</html>
<%
}
%>