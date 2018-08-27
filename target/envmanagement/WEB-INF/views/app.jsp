<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${portfolio }</title>

<link href="<c:url value="/resources/css/style1.css" />" rel="stylesheet">
</head>
<body>
<div align="center">
		<h1>${app }</h1>
		<h3><a href="newEnvDetail?app=${app }&portfolio=${portfolio }">Add</a></h3>
		
		<h2>DEV</h2>
				<table border="1">
		        <th>Portfolio</th>
		        <th>PM</th>
	        	<th>Application</th> 
	        	<th>Environment</th>
	        	<th>IP</th>
	        	<th>Hostname</th>
	        	<th>Username</th>
	        	<th>Type</th>
	        	<th>Action</th>
		<c:forEach var="env" items="${envdetail}" varStatus="status">		
		<c:if test = "${env.environment == 'DEV'}">			
	        	
	        	
				
	        	<tr>
	        		<%-- <td>${status.index + 1}</td> --%>
	        		<td>${env.portfolio}</td>
	        		<td>${env.pm}</td>
	        		<td>${env.application}</td>
					<td>${env.environment}</td>
					<td>${env.ip}</td>
					<td>${env.hostname}</td>
					<td>${env.username}</td>
					<td>${env.type}</td>
					<td>
						<a href="editEnv?id=${env.id}&portfolio=${env.portfolio}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deleteEnv?id=${env.id}&app=${env.application}&portfolio=${env.portfolio}">Delete</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						
					</td>
							
	        	
				 		
		
		</tr>
		</c:if>
		
		
		</c:forEach>
		</table>
		</br>
		</br>
		<h2>STAGING</h2>
		<table border="1">
		<!-- <th>No</th> -->
		 <th>Portfolio</th>
		        <th>PM</th>
	        	<th>Application</th> 
	        	<th>Environment</th>
	        	<th>IP</th>
	        	<th>Hostname</th>
	        	<th>Username</th>
	        	<th>Type</th>
	        	<th>Action</th>
		<c:forEach var="env" items="${envdetail}" varStatus="status">		
		<c:if test = "${env.environment == 'STAGING'}">			
	        	
	        	
				
	        	<tr>
	        		<%-- <td>${status.index + 1}</td> --%>
	        		<td>${env.portfolio}</td>
	        		<td>${env.pm}</td>
	        		<td>${env.application}</td>
					<td>${env.environment}</td>
					<td>${env.ip}</td>
					<td>${env.hostname}</td>
					<td>${env.username}</td>
					<td>${env.type}</td>
					<td>
						<a href="editEnv?id=${env.id}&portfolio=${env.portfolio}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deleteEnv?id=${env.id}&application=${env.application}&portfolio=${env.portfolio}">Delete</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						
					</td>
							
	        	
				 		
		
		</tr>
		</c:if>
		
		
		</c:forEach>
		</table>
		</br>
		</br>
		<h2>PRODUCTION</h2>
		
		<table border="1">
		<!-- <th>No</th> -->
		 <th>Portfolio</th>
		        <th>PM</th>
	        	<th>Application</th> 
	        	<th>Environment</th>
	        	<th>IP</th>
	        	<th>Hostname</th>
	        	<th>Username</th>
	        	<th>Type</th>
	        	<th>Action</th>
		<c:forEach var="env" items="${envdetail}" varStatus="status">		
		<c:if test = "${env.environment == 'PRODUCTION'}">			
	        	
	        	
				
	        	<tr>
	        		
	        		<td>${env.portfolio}</td>
	        		<td>${env.pm}</td>
	        		<td>${env.application}</td>
					<td>${env.environment}</td>
					<td>${env.ip}</td>
					<td>${env.hostname}</td>
					<td>${env.username}</td>
					<td>${env.type}</td>
					<td>
						<a href="editEnv?id=${env.id}&portfolio=${env.portfolio}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deleteEnv?id=${env.id}&app=${env.application}&portfolio=${env.portfolio}">Delete</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						
					</td>
							
	        	
				 		
		
		</tr>
		</c:if>
		
		
		</c:forEach>
		</table>
		
		
</div>
</body>
<h2><a href="/envmanagement/Portfolio?portfolio=${portfolio }">Go Back</a></h2>
</html>