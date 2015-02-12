<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<jsp:include page="header.jsp" />

<link href="${pageContext.request.contextPath}/css/jquery.bdt.css"
	rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery.bdt.js"></script>
<script
	src="${pageContext.request.contextPath}/js/jquery.sortelements.js"></script>

<title>Add User</title>
</head>



<body>

	<div id="wrapper">

		<!-- Navigation -->
		<jsp:include page="navigation.jsp" />

		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">

						<h1 class="page-header text-center">Add User</h1>
						<br>
						<br>
						<% int i = 0; %>
						<table class="table table-hover" id="bootstrap-table">
							<thead>
								<tr>
									<th>#ID</th>
									<th>Login</th>
									<th>Ajouter</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listContacts}" var="listContacts">
								<% i++; %>
									<tr>
										<td><%= i %></td>
										<td>${listContacts.key.getPseudo()}</td>
										<td>
											<a href="Add?idMessagerie=${idMessagerie}&idUser=${listContacts.key.getId()}" class="fa fa-plus"></a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<script>
		$(document).ready(function() {
			$('#bootstrap-table').bdt();
		});
	</script>



</body>