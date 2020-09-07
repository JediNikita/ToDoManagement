<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="header.jspf"%>
	<%@ include file="navigation.jspf"%>
	<div class="container">
		<div>
			<a type="button" class="btn btn-primary btn-md" href="/add-todo">
				Add Todo</a>
		</div>
		<br>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3>List of Todo's</h3>
			</div>
			<div class="panel-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th width="40%">Description</th>
							<th width="40%">Target Date</th>
							<th width="20%"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${todos}" var="todo">
							<tr>
								<td>${todo.description}</td>
								<td><fmt:formatDate value="${todo.targetDate}"
										pattern="dd-MM-yyyy" /></td>
								<td><a type="button" class="btn btn-success"
									href="/update-todo?id=${todo.id}">Update</a> <a type="button"
									class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>

	<%@ include file="footer.jspf"%>
</body>
</html>