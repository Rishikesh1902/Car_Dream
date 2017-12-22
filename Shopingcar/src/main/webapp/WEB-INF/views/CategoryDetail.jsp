<%@ include file="template/Header.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page isELIgnored="false" %>
    <%@taglib uri="http://www.springframework.org/tags/form"  prefix="fm"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
<head>

<style>
body { 
/* background-image: url("resources/image/f8.gif"); */
background-size:cover;
 background-position: 50% 50%;

 max-width: 100%;
    height: auto;
}
table, th, td {
    border: 3px solid black;
     border-collapse: collapse;
    
}
th, td {
    padding: 15px;
}
table {
    border-spacing: 15px;
}

th,td {
    text-align: center;
}
caption{
text-align:center;
h1{
text-aign:center;


}

}

</style>

</head>

<h1 style="color:navy;"><center>Category Details</center></h1>

<body >

 <c:if test="${not empty msg}">
           <h3 style="text-align:center;">${msg}</h3>
 </c:if>

<div class="container">

<div class="row col-md-6 col-md-offset-5 custyle">
 
  <fm:form action="${pageContext.request.contextPath}/addCategory" commandName="category" method="post" class="form-horizontal">
    
      <c:if test="${not empty category.cname }"> 
       <div class="form-group">
       <label class="control-label col-sm-3" >CatId:</label>
      	<div class="col-sm-3">
  		<fm:input type="text" class="form-control" path="catId" disabled="true" readonly="true"></fm:input>
  		<fm:hidden path="catId"/>
        
        </div>
        </div>
     </c:if>

     <div class="form-group">
      <label class="control-label col-sm-3" >CategoryName:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="cname"></fm:input>
       
      </div>
   
    
       <div class="col-sm-3">
       <fm:errors path="cname" cssStyle="color:#ff0000;"></fm:errors>
       </div>
        </div>
       <div class="form-group">
      <label class="control-label col-sm-3" >Description:</label>
      <div class="col-sm-3">   
           
        <fm:input input="text" class="form-control"  path="cdescp"></fm:input>
       
      </div>
    </div>
 
    <div class="form-group">        
      <div class="col-sm-offset-3 col-sm-3">
      <c:if test="${empty category.cname }">
        <fm:button type="submit" class="btn btn-default">Register</fm:button>
        </c:if>
      </div>
      <div class="col-sm-offset-2 col-sm-3">
      <c:if test="${not empty category.cname }">
        <fm:button type="submit" class="btn btn-default">Update</fm:button>
        </c:if>
      </div>
    </div>
  </fm:form>
 
 <c:if test="${not empty listCategory}">
<table style="width:100%;">
    <caption><h3>Category List</h3></caption>
            <tr>
            <th>Cat-ID</th>
            <th>Category Name</th>
            <th>Description</th>
            <th colspan="2" >Action</th>
        </tr>



<c:forEach items="${listCategory}" var="cat">

<tr>
<td>${cat.catId}</td>
<td>${cat.cname}</td>
<td>${cat.cdescp}</td>
<td><a href="<c:url value='updateCategory/${cat.catId}'/>"  class="btn btn-info btn-xs"><span class="glyphicon glyphicon-edit"></span> Edit</a></td> 
<td><a href="<c:url value='deleteCategory/${cat.catId}'/>" class="btn btn-danger btn-xs"><span class="glyphicon glyphicon-remove"></span>Del</a></td>
</tr>

</c:forEach>

</table>
</c:if>

</div>
</div>


  


</body>
</html>