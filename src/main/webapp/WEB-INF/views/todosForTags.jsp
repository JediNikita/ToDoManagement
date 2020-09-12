<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>To-do List</title>
</head>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<link href="/css/stylesheet.css" type="text/css">
<script>
var contexPath = "<%=request.getContextPath() %>";
function increaseValue() {
	  var value = parseInt(document.getElementById('number').value, 10);
	  value = isNaN(value) ? 0 : value;
	  value++;
	  document.getElementById('number').value = value;
	}

	function decreaseValue() {
	  var value = parseInt(document.getElementById('number').value, 10);
	  value = isNaN(value) ? 0 : value;
	  value < 0 ? value = 1 : '';
	  value < 1 ? document.getElementById('number').disabled()=true : '';
	  value--;
	  document.getElementById('number').value = value;
	}


	function doAjaxPost() {
		$.ajax({
		        type: "POST",
		        url: contexPath + "/postponeTodo",
		        data: JSON.stringify({
			        "postponeDays" :document.getElementById('number').value,
			        "todoId": document.getElementById('todoId').value,
		        }),
		        error: function(e) {
		            console.log(e);
		          },
		          dataType: "json",
		          contentType: "application/json"
		    });
	}  
</script>
<body>
	<%@ include file="header.jspf"%>
	<%@ include file="navigation.jspf"%>
	<div class="container">
		<div>
			<a type="button" class="btn btn-primary btn-md" href="/add-todo">
				Add To-do</a>
		</div>
		<br>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3>List of Todo's for </h3>
			</div>
			<div class="panel-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th width="40%">Description</th>
							<th width="40%">Target Date</th>
							<th width="40%">Tags</th>
							<th width="20%"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${todoList}" var="todo">
							<tr>
								<td>${todo.description}</td>
								<td><fmt:formatDate value="${todo.targetDate}"
										pattern="dd-MM-yyyy" /></td>
								<td><c:forEach items="${todo.tags}" var="todotag">
										<a href="/showTodosForTags?id=${todotag.getId() }">#${todotag.getTagname()  }</a>
									</c:forEach>
										</td>
								<td><a type="button" class="btn btn-success"
									href="/update-todo?id=${todo.id}">Update</a> <br> <a type="button"
									class="btn btn-warning" href="/delete-todo?id=${todo.id}">Delete</a> <br>
									<a type="button" class="btn btn-info" href="#modalPostpone"
									rel="modal:open" data-id="${todo.id}">Postpone</a></td>
								<div id="modalPostpone" class="modal" style="width:400 px; height:150px; position:relative; ">
										<input type="hidden" name="todoId" id="todoId" value="${todo.id}"/>
										<label path="postpone">Postpone for</label>
										<div class="value-button" id="decrease"
											onclick="decreaseValue()" value="Decrease Value">-</div>
										<input type="text" id="number" value="0" /> Days
										<div class="value-button" id="increase"
											onclick="increaseValue()" value="Increase Value">+</div>
										<input type="button" onClick="doAjaxPost()" value="Postpone"/>
								</div>

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