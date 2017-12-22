<!DOCTYPE html>
<html lang="en">
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:url value="resources/bootstrap/css" var="bootcss"/>
<head>

  <title>Shop-Kart</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 
  
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">   
  
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 <!--  <link rel="stylesheet" href="${bootcss}/bootstrap.min.css">--> 
  
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand"  href="${pageContext.request.contextPath}/Home">Shop-Kar</a>
    </div>
    <ul class="nav navbar-nav">
   
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Product<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <c:forEach items="${listCategory}" var="cat">
            <li><a href="${pageContext.request.contextPath}/productbyCategory/${cat.catId}">${cat.catId}</a></li>
          </c:forEach>
        </ul>
      </li>
   
    </ul>
             <ul class="nav navbar-nav navbar-center">
             
              <sec:authorize access="hasRole('ROLE_ADMIN')">
      <li class="active"><a href="${pageContext.request.contextPath}/adminHome"><span
							class="glyphicon glyphicon-user"></span></a></li>
							</sec:authorize>
            
             
             </ul>      
 
					
  
        
           
      <ul class="nav navbar-nav navbar-right">
       <sec:authorize access="isAuthenticated()"> 
	<li class="nav-item" style="color:powderblue"> <br>Welcome <i>${pageContext.request.userPrincipal.name}</i></li>
	   </sec:authorize>
	   
		<sec:authorize access="hasRole('ROLE_USER')" >			
					<li class="nav-item"> <a href="${pageContext.request.contextPath}/myCart/all"><span class="glyphicon glyphicon-shopping-cart"></span><span class="badge badge-pill badge-primary">${numberProducts }</span></a></li>
				</sec:authorize>
      
      
	   
		<sec:authorize access="isAuthenticated()" >			
					<li class="nav-item"> <a href="${pageContext.request.contextPath}/LogOut">Logout</a></li>
				</sec:authorize>
      
        <sec:authorize access="isAnonymous()">
			             <li class="nav-item"><a href="${pageContext.request.contextPath}/Signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
			       		 <li class="nav-item"><a href="${pageContext.request.contextPath}/Login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
         </sec:authorize>
       
       
         
      </ul>
      
    </div>
    
 
</nav>

 

  
  
  

  </body>
</html>