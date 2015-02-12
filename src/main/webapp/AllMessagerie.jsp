<%@page import="org.ikya.entities.User"%>
<%@page import="org.ikya.entities.Messagerie"%>
<%@page import="java.util.Set"%>
<%@page import="org.ikya.dao.MessagerieDao"%>
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

<title>Send message</title>
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

						<h1 class="page-header text-center">My Messagerie list</h1>
						<br> <br>
						<table class="table table-hover" id="bootstrap-table">
							<thead>
								<tr>
									<th>#ID</th>
									<th>Users</th>
									<th>Chat</th>
									<th>Supprimer</th>
								</tr>
							</thead>
						
							<tbody>
							<% int i = 0; 
							MessagerieDao messagerieDao = new MessagerieDao();
							Set<User> usersList = null;
							
							Set<Messagerie> messagerieList = (Set<Messagerie>) request.getAttribute("messagerieList");
							System.out.println("Messagerie list from JSP" + messagerieList);
							
							for (Messagerie messagerie : messagerieList) {
				
								usersList = messagerieDao.getAllUserByMessagerieId(messagerie.getId());
								
								
							
							%>
								<% i++; %>
									<tr>
										<td><%= i %></td>
	
										<td><% 
										for(User user : usersList){
											%>
											<%= user.getPseudo() + "   " %>
											<%
										}
										
										 %></td>
										
										
										<td><a href="<%= "/ikya/mess?idMessagerie=" + messagerie.getId() %>" class="fa fa-envelope"></a></td>
										<td><a href="index" class="fa fa-trash text-center" ></a></td>
									</tr>
							<% } %>
							
							
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
