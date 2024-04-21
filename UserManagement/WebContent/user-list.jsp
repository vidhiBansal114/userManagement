<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>

</head>
<body bgcolor="black">

	<font color="white">
	
			
				<a href="https://www.xadmin.net" > User
					Management  </a>
			

				<li><a href="<%=request.getContextPath()%>/list"
					>Users</a></li>
			
	

	<br>

	
	

		
			<h3 >List of Users</h3>
			<hr>
	

				<a href="<%=request.getContextPath()%>/new" >Add
					New User</a>
			
			<br>
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="user" items="${listUser}">

						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.name}" /></td>
							<td><c:out value="${user.email}" /></td>
							
							<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>

</font>
</body>
</html>