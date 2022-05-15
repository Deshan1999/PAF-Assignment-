<%@page import="com.Payment"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/payment.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Payment DETAILS</h1>
				<form id="formPayment" name="formPayment" method="post" action="PaymentJ.jsp">  
					Customer Name:  
 	 				<input id="CustomerName" name="CustomerName" type="text"  class="form-control form-control-sm">
					<br> Bill Amount:   
  					<input id="BillAmount" name="BillAmount" type="text" class="form-control form-control-sm">   
  					<br> Account Number:   
  					<input id="AccountNo" name="AccountNo" type="text"  class="form-control form-control-sm">
					
					<br> 
					<input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary">  
					<input type="hidden" id="hidPaymentIDSave" name="hidPaymentIDSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"> </div>
				
			   <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divPaymentGrid">
					<%
						Payment payObj = new Payment();
						out.print(payObj.readPayment());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>