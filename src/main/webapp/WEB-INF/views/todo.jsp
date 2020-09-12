<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>To-Do Management</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="js/bootstrap-tagsinput-latest/dist/bootstrap-tagsinput.min.js"></script>
<!-- <link href="js/tagify-master/dist/tagify.css" rel="stylesheet"> -->
<script >
	
</script>
</head>

<body>
<%@ include file="header.jspf" %>
<%@ include file="navigation.jspf" %>

<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="panel panel-primary">
				<div class="panel-heading">
					Add To-do
				</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="todoDTO">
						<%-- <form:hidden path="id"/> --%>
						<fieldset class="form-group">
							<form:label path="description">Description</form:label>
							<form:input path="description" type="text" class="form-control" required="required"/>
							<form:errors path="description" cssClass="text-warning"/>
						</fieldset>
						
						<fieldset class="form-group">
							<form:label path="targetDate">Target Date</form:label>
							<form:input path="targetDate" type="text" class="form-control" required="required"/>
							<form:errors path="targetDate" cssClass="text-warning"/>
						</fieldset>
						
						<fieldset class="form-group">
							<form:label path="tags">Tags</form:label>
							<form:input path="tags" type="text" class="form-control" placeholder="write some tags" value=""
							data-role="tagsinput" />
						</fieldset>
						
						<button type="submit" class="btn btn-success" >Save</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="footer.jspf"%>
</body>
</html>