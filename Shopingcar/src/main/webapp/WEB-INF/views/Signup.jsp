<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isELIgnored="false" %>
    <%@taglib uri="http://www.springframework.org/tags/form"  prefix="fm"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ include file="template/Header.jsp" %>
<html>
<head>
<style>
body {
    
// background-image: url("resources/image/f7.gif"); 

  background-size:cover;
 background-position: 100% 100%;
    


    
}
h3{

text-align:left;
font-family:arial;

}
</style>

</head>
<center>
<h1 style="color">Registration Page</h1>
<img src="resources/image/kk.jpg" alt="Mountain View" style="width:304px;height:228px;">
</center>
<body >

<c:if test="${not empty msg}">
           <h3 style="text-align:center;">      ${msg}</h3>
</c:if>

<div class="container">
  
  <fm:form action="addUser" commandName="customer" modelAttribute="customer" class="form-horizontal" >
    <div class="form-group">
     <div class="col-sm-3">
       <c:if test="${not empty user.name }"> 
        <label class="control-label col-sm-3" >UId:</label>
      
        <fm:input type="text" class="form-control" path="userId"></fm:input>
        </c:if>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-3" >Name:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="username"></fm:input>
         <fm:errors path="username" cssStyle="color:#ff0000;"></fm:errors>
        </div>
       </div>
       
      
       
    <div class="form-group">
      <label class="control-label col-sm-3" >Email:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="email"></fm:input>
       
      </div>
    </div>
   
    <div class="form-group">
      <label class="control-label col-sm-3" >Password:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="password"></fm:input>
       
      </div>
    </div>
    <h3>Billing Address :</h3>
    
    <div class="form-group">
      <label class="control-label col-sm-3" >Street Name:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="billingAddress.streetName" ></fm:input>
       
      </div>
    </div>
  
   <div class="form-group">
      <label  class="control-label col-sm-3" >Apartment No:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="billingAddress.apartNo" ></fm:input>
       
      </div>
    </div> 
     
     <div class="form-group">
     
      <label  class="control-label col-sm-3" >City:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="billingAddress.city"></fm:input>
       
      </div>
    </div> 
    <div class="form-group">
  <label class="col-sm-3 control-label" for="selectbasic">State :</label>
  <div class="col-sm-3">
    <fm:select id="selectbasic"  path="billingAddress.state" class="form-control">
      <option value="1">ArunachalPradesh</option>
      <option value="2">New-Delhi</option>
      <option value="3">Haryana</option>
      <option value="4">HimachalPradesh</option>
       <option value="5">UttarPradesh</option>
      <option value="6">Mumbai</option>
    </fm:select>
  </div>
</div>
      
     
 <div class="form-group">
  <label class="col-sm-3 control-label" for="selectbasic">Country :</label>
  <div class="col-sm-3">
    <fm:select id="selectbasic"  path="billingAddress.country" class="form-control">
      <option value="1">India</option>
      <option value="2">England</option>
      <option value="3">Germany</option>
      <option value="4">Australia</option>
    </fm:select>
  </div>
</div>

  
				
			<div class="form-group">
     
      <label  class="control-label col-sm-3" >Zipcode:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="billingAddress.zipcode"></fm:input>
       
      </div>
    </div>
				

		
                             <h3>Shipping Address :</h3>
                             			<div class="form-group">
      <label class="control-label col-sm-3" >Street Name:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="shippingAddress.streetName" ></fm:input>
       
      </div>
    </div>
		
		<div class="form-group">
      <label  class="control-label col-sm-3" >Apartment No:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="shippingAddress.apartNo" ></fm:input>
       
      </div>
    </div>
								
						<div class="form-group">
     
      <label  class="control-label col-sm-3" >City:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="shippingAddress.city"></fm:input>
       
      </div>
    </div>
					
		   <div class="form-group">
  <label class="col-sm-3 control-label" for="selectbasic">State :</label>
  <div class="col-sm-3">
    <fm:select id="selectbasic"  path="shippingAddress.state" class="form-control">
      <option value="1">AndhraPradesh</option>
      <option value="2">New-Delhi</option>
      <option value="3">Haryana</option>
      <option value="4">HimachalPradesh</option>
      <option value="5">UttarPradesh</option>
      <option value="6">Mumbai</option>
    </fm:select>
  </div>
</div>
 
						 <div class="form-group">
  <label class="col-sm-3 control-label" for="selectbasic">Country :</label>
						<div class="col-sm-3">
    <fm:select id="selectbasic"  path="shippingAddress.country" class="form-control">
      <option value="1">India</option>
      <option value="2">England</option>
      <option value="3">Germany</option>
      <option value="4">Australia</option>
    </fm:select>
  </div>
</div>
						<div class="form-group">
     
      <label  class="control-label col-sm-3" >Zipcode:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="shippingAddress.zipcode"></fm:input>
       
      </div>
    </div>
		
		
	
		
    <div class="form-group">        
      
      <label class="control-label col-sm-3" >Mobile:</label>
        <div class="col-sm-3">
        <fm:input type="text" class="form-control"  path="mobileno"></fm:input>
        </div>
      </div>
    </div>
   
    <div class="form-group">        
      <div class="col-sm-offset-1 col-sm-5">
      
        <fm:button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-send"></span>&nbsp Register</fm:button>
        
       
      </div>
      
      </div>
        </fm:form>
  
 <%--  <c:if test="${not empty userList}">



<table>
<tr>

<td>name</td>
<td>address</td>
<td>Edit</td>
<td>Delete</td>
</tr>
<c:forEach items="${userList}" var="usr">
<tr>

<td>${usr.name}</td>
<td>Edit</td>
<td>Delete</td>
</tr>


</c:forEach>

</table>



</c:if>
  
  
</div>
 --%>
 
</body>

</html>
