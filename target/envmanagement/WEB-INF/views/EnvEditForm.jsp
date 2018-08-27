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
		<form:form action="saveEnvdetail" method="post" modelAttribute="envdetail">
		<table>
			<form:hidden path="id"/>
			
			<tr>				
				<td><form:label path = "portfolio" >Portfolio :</form:label></td>
				
                    <td><form:input path="portfolio" readonly="true" required="required" /></td>   	 
               
			</tr> 
			
			<tr>				
				<td><form:label path = "pm" >PM :</form:label></td>
				
                    <td><form:input path="pm" readonly="true" required="required" /></td>   	 
               
			</tr> 
			
			 <tr>				
				<td><form:label path = "application" >Application :</form:label></td>
				
                    <td><form:input path="application" readonly="true" required="required" /></td>   	 
              
			</tr> 
			
			<tr>				
			   <td><form:label path = "environment">Environment :</form:label></td>
               <td>
                  <form:select path = "environment" required="required">
                     <form:option value = "" label = "Select"/>
                     <form:options items = "${envList}" />
                  </form:select>     	
               </td>
			</tr>
			
			
			
			<tr>
				<td>IP :</td>
				<td><form:input path="ip" required="required"/></td>
			</tr>
			<tr>
				<td>Hostname :</td>
				<td><form:input path="hostname" required="required"/></td>
			</tr>
			<tr>
				<td>Username :</td>
				<td><form:input path="username" required="required"/></td>
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