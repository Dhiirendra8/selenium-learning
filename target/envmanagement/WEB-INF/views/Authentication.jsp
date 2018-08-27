<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authentication</title>
<link href="<c:url value="/resources/css/style1.css" />" rel="stylesheet">
</head>
<body>
<div align="center">
		<h1>Please Enter Access Token for <%out.println(request.getParameter("app")); %> Application To Proceed</h1>
		  <form:form action="checkAccessToken" method="POST" > 
		<table>
			<%String portfolio=request.getParameter("portfolio"); %>
			<%String app=request.getParameter("app"); %>
			<tr>
			<td><form:hidden path = "application" value = "<%=app%>" /></td>
			<td><form:hidden path = "portfolio" value = "<%=portfolio%>" /></td>	
			</tr>
			<tr>
				<td>Token ID :</td>
				 <td><form:input path="token" /></td>
			</tr>
			
			
			
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Submit"></td>
			</tr>
		</table>
		 </form:form>  
	</div>
</body>
<center><a href="/envmanagement/">Go Home</a></center>
</html>