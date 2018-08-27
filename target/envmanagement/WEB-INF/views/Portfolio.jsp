<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${portfolio } Home</title>
<link href="<c:url value="/resources/css/style1.css" />" rel="stylesheet">

</head>
<body>
<div align="center">
		<h1>Please select the ${portfolio } Applications</h1>

		<table>
			<c:forEach items="${applications }" var="app">
				<tr>
					<td><a href="authentication?app=${app }&portfolio=${portfolio }">${app }</a></td>
				</tr>
			</c:forEach>
			
			<tr>
				 <td><a href="download?portfolio=${portfolio }">Download Excel</a></td>					           
			</tr>
			
		</table>

	</div>

</body>
<h2><a href="/envmanagement/">Go Back</a></h2>
</html>