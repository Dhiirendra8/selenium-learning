<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Infra Tool Page</title>
<link href="<c:url value="/resources/css/style1.css" />"
	rel="stylesheet">
</head>
<body>

	<div align="center">
		<h1>PORTFOLIO</h1>
		<h3><a href="newPortfolioDetail">Add Portfolio</a></h3>
		<table>
			<c:forEach items="${portfolioList }" var="portfolio">
				<tr>
					<td><a href="Portfolio?portfolio=${portfolio }">${portfolio }</a></td>
			    </tr>
			</c:forEach>

		</table>

	</div>
</body>

</html>