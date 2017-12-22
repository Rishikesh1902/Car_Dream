<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isELIgnored="false" %>
    <%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
       <%@ include file="template/Header.jsp" %>
 <c:url value="/resources/image/" var="image"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<body>

		 <div class="space"></div>
<div class="container">
	<h2 class="heading-one">Cart</h2>
	<c:if test="${not empty success }">
		<div class="alert alert-success">${success}</div>
	</c:if>
	<c:if test="${empty cartList }">
		<div class="alert alert-warning">You have not added any product.
			into cart.</div>
	</c:if>
	<c:if test="${not empty error }">
		<div class="alert alert-danger">${error }</div>
	</c:if>
	<c:if test="${not empty cartList}">
		<table class="table table-bordered">
			<tr>
				<th>Product Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th width="135">Actions</th>
			</tr>
		
			<c:forEach items="${cartList}" var="cart">
				<tr>
				<td>${cart.productName}</td>
					<td>&#8377 ${cart.price}</td>
					<td>${cart.quantity }</td>
					<td><a href="<c:url value='/myCart/deleteItem/${cart.id}'/>"> <span class="glyphicon glyphicon-trash"></span></a></td>
				</tr>
			</c:forEach>
			
		</table>
		</c:if>
		
		<hr>
		<div class="pull-right">
		
			Total Amount: <em><b class="text-success">&#8377 ${totalAmount }</b></em>
			&nbsp;
			 <a href="<c:url value="/order"/>">CheckOutC</a>
		</div>
		<a href="<c:url value="/myCart/clearCart" />"
			class="btn btn-outline-danger">Clear Cart</a>
	
			 
		</div>
		
		</div>
				
			
			

</body>
</html>