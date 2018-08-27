<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Environment Details</title>
<link href="<c:url value="/resources/css/style1.css" />" rel="stylesheet">

</head>
<body>
	<div align="center">
		<h1>New/Edit Environment Details</h1>
		<form:form action="saveEnvdetail" commandName="envdetail" method="post" modelAttribute="envdetail">
		<table>
			<form:hidden path="id"/>
			
			<tr>
				<td>Portfolio :</td>
				<td><form:input path="portfolio" value="${portfolio}" readonly="true"/></td>
				
			</tr>
			<tr>
				<td>PM :</td>
				<td><form:input path="pm" value="${PM}" readonly="true"/></td>
			</tr>
			<%-- <tr>
				<td>PM :</td>
				<td><form:input path="pm" required="required"/></td>
				<form:errors path="pm" />
			</tr> --%>
			 <tr>				
				<td><form:label path = "application">Application :</form:label></td>
				
                     <td><form:input path = "application" value="${app}" readonly="true"/></td>   	 
                <%-- <td>
                  <form:select path = "application">
                     <form:option value = "NONE" label = "Select"/>
                     <form:options items = "${appList}" />
                  </form:select>     	
               </td>   --%>
			</tr> 
			
			<tr>				
			   <td><form:label path = "environment">Environment :</form:label></td>
               <td>
                  <form:select path = "environment" required="required">
                     <form:option value = "" label = "Select" />
                     <form:options items = "${envList}" />
                  </form:select>     	
               </td>
			</tr>
			
			
			
			<tr>
				<td>IP :</td>
				<td><form:input path="ip" required="required"/></td>
				<form:errors path="ip"  />
				
			</tr>
			<tr>
				<td>Hostname :</td>
				<td><form:input path="hostname" required="required"/></td>
				<form:errors path="hostname" />
			</tr>
			<tr>
				<td>Username :</td>
				<td><form:input path="username" required="required"/></td>
				<form:errors path="username" />
			</tr>
			<tr>
				 <td><form:label path = "type">Type :</form:label></td>
               <td>
                  <form:select path = "type" required="required">
                     <form:option value = "" label = "Select"/>
                     <form:options items = "${typeList}" />
                  </form:select>     	
               </td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>