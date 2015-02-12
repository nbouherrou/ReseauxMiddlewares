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

<title>All Utilisateurs</title>
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

						<h1 class="page-header text-center">All Utilisateurs</h1>
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
								<c:forEach items="${listUsers}" var="listUsers">
								<% i++; %>
									<tr>
										<td><%= i %></td>
										<td>${listUsers.pseudo}</td>
										<td>
										<c:set var="USER" value="${user}"></c:set>
										<c:set var="CONTACT" value="${USER.findContactByUser(listUsers)}"></c:set>
										<c:if test="${empty CONTACT}" >
											<a href="AddContact?idcontact=${listUsers.id}" class="fa fa-plus"></a>
										</c:if>
										
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