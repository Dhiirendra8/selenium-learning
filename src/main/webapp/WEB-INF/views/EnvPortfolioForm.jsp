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
		<form:form action="savePortfoliodetail" commandName="envdetail" method="post" modelAttribute="envdetail">
		<table>
			
			<tr>
				<td>Portfolio :</td>
				<td><form:input path="portfolio" required="required"/></td>
				
			</tr>
			<tr>
				<td>Application :</td>
				<td><form:input path="application" required="required"/></td>
				
			</tr>
			<tr>
				<td>PM :</td>
				<td><form:input path="pm" required="required"/></td>
				
			</tr>
			<tr>
				<td>Token :</td>
				<td><form:input path="token" required="required"/></td>
				
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
		</table>
		</form:form>
	</div>
</body>
</html>