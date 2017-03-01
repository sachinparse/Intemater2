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
<title>Customers List</title>

<%@ include file="genericinclude.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="js/stdlib/jquery-1-9-1-min.js"></script>

<style>
	p{ 	font-family: "Times New Roman", Times, serif;
	font-style: oblique;
	font-size:15px;}
	footer {
    clear: both;
    position: relative;
    z-index: 10;
    height: 3em;
    margin-top: 30.5em;
    }
    
    footer{display: block;}
	.Footer {
    	background: none repeat scroll 0% 0% #3A3A3A;
    	background-color: #3A3A3A;
		background-image: none;
		background-repeat: repeat;
		background-attachment: scroll;
		background-position: 0% 0%;
		background-clip: border-box;
		background-origin: padding-box;
		background-size: auto auto;
    	color: #FFF;
    	padding-bottom: 30px;
    	clear: both;
	}
	
	th {
	        background-color: #4CAF50;
	        color: white;
	    }
	    
	    tr:nth-child(even) {background-color: #f2f2f2}
	
		h2   {color: #6a6363;}
		div,h5 {
			align: left;
    		width: 100%;
    		height: 20px;
    		/* border: 2px solid #73AD21; */
    		/* background:#ccc; */
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


<script type="text/javascript">

$(document).ready(function(){
	
	$("#idCategoryId").change(function(){
		
		 if(($("#idCategoryId").val() !=0) ){
			 this.form.submit();
		 }
		
		});
	
});

 function onload(){
	 
	// var t1=document.getElementById("idCategoryId").value;
	 //document.getElementById("idcurrentPage").value=t1;
	 document.getElementById("idcurrentPage").value=${currentPage};
	 
	 
 }

</script>
</head>
<body onLoad="onload()">


	<h5 align="center">
		<!-- <a href="showCustomer.form">View Contacts</a> | --> <a
			href="sendEmail.form">Send Email</a>  
			<a href="register.jsp">Create Contact</a> 
			<a href="newUser.jsp">Create New User</a>
			<a	href="changePassword.form">Change Password</a>
			<a	href="showUsers.form?recperpage=25">Show Users</a> 
			<a href="showCategory.jsp">Category</a>
			<a href="logout.form">Logout</a>
		    <a href="saveMessage.jsp">Save Message</a>
	</h5>
			
		<form name="showCustomerForm" action="showCustomers.form" method="get">
			
			<input type="hidden" name="currentPage" id="idcurrentPage" value="0"/>
			
		<center>
		</center>	
			<table align="center">
			  <tr>
			     <!-- <td width="400px"></td> -->
			     <td width="100px" align="left">
					<select name="categoryId" id="idCategoryId">
								<option value="0">Select Category</option>					
					    <c:forEach var="category" items="${objlstCategory}">
							  	<option value="${category.categoryId}" ${category.categoryId==categoryId ? 'selected':'' }>${category.categoryName}</option>
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
		 <br>
		  <!-- show the customer data -->
			<table  class="responstable" align="center">
				<tr>
				  <th>Customer ID</th> 
				  <th>Customer Name</th> <th>Mobile</th> <th>Email ID</th> <th>PAN No.</th> <th>Address</th> <th>DOB</th> <th>Edit / Delete</th>
				</tr>
			
			  <c:forEach var="custList" items="${listOfCustomer}">
							  	<br>
		        <tr align="center">
		          <td>${custList.custId} </td>  <td>${custList.name } </td>  <td>${custList.mobile1} </td>  <td>${custList.email} </td>  <td>${custList.pan} </td>
		          <td>${custList.address}</td> 
		          <!-- JSTL split() function to separate the date and time 05092016-->
		          <td>&nbsp;${fn:split(custList.dob,' ')[0]}&nbsp;&nbsp;</td>
		          
		          <td>  <a href='editCustomer.form?customerId=${custList.custId}'>Edit</a>  
					  
					   <c:if test="${custList.status eq 'A'}">
					    	&nbsp;/ &nbsp;  
					  		<a href='deleteCustomer.form?customerId=${custList.custId}&categoryId=${custList.category}'><font color="red">Delete</font></a>   
					  </c:if>
			      </td>
		        
		        </tr>
		      </c:forEach>
		    </table>
		
			<table align="center">
		 		 <tr>
		  			 	<td>Pages :</td>
				   <c:forEach begin="1" end="${noOfPages}" var="i">
			      	<td></td>
			      	<td>
					  <c:choose>
						<c:when test="${currentPage eq i}">
						    <a href="showCustomers.form?currentPage=${i}&recperpage=${noOfRecordsPerPage}"><font color="red">${i}</font></a>
						    
						</c:when>
						<c:otherwise>
							<a href="showCustomers.form?currentPage=${i}&recperpage=${noOfRecordsPerPage}">${i}</a>
						</c:otherwise>
					  </c:choose>
   				  	</td>				
			       </c:forEach>
				 </tr>	
			 </table>
		
		</form>	
			
</body>
</html>
<%
}
%>