	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<%@ include file="header.jspf"%>
<%@ include file="navigation.jspf"%>

<div class="container">
	<div class="panel panel-primary">
		<div class="panel-heading">
			<div class="panel-body">
				Welcome ${name }! <a href="/list-todos">Click here</a> to manage your To-dos. 
			</div>
		</div>
	</div>

</div>
<%@ include file="footer.jspf"%>
</body>
</html>