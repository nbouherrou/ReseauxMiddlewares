<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="	org.ikya.entities.User,
					org.ikya.entities.Contact,
					java.util.ArrayList,
					java.util.Map,
					java.util.HashMap,
					org.ikya.constants.Constants" 
%>

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

						<h1 class="page-header text-center">My contacts list</h1>
						<br> <br>
						<table class="table table-hover" id="bootstrap-table">
							<thead>
								<tr>
									<th>#</th>
									<th>Pseudo</th>
									<th>Email</th>
									<th>Score</th>
									<th>Messagerie</th>
									<th>Supprimer</th>
								</tr>
							</thead>
							<tbody>
							<% int i = 0; %>
							<c:forEach items="${listContacts}" var="line">
								<% i++; %>
									<tr>
										<td><%= i %></td>
										<td>${line.getPseudo()}</td>
										<td>${line.getEmail()}</td>
										<td>${line.getScore()}</td>
										<td><a href="Messagerie?id=${line.getId()}" class="fa fa-envelope"></a></td>
										<td><a href="deleteContact?idcontact=${line.getId()}" class="fa fa-trash text-center" ></a></td>
									</tr>
							</c:forEach>
							
							
							</tbody>
						</table>
				<br/>
			</div>
		</div>
		
				<div class="panel panel-default">
  					<div class="panel-heading text-center">Demandes en cours</div>
  						<div class="panel-body">
  							<div class="col-xs-12 col-sm-12 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1">
	  							<br />
   									<ul class="list-group">
	    					
	    						<% 
	    							Map<User, Contact> demandeContact = (HashMap<User, Contact>) request.getAttribute("demandeContacts");
	    							
	    							for (Map.Entry<User, Contact> entry : demandeContact.entrySet()) {
	    							
	    								Contact c = entry.getValue();
										
								%>
										
											<li class="list-group-item">
											
												<div class="row">
												
													<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
													
													<%
													
																					
													Integer state  				= c.getStatut();
													
													Integer idContact 			= entry.getKey().getId();
													
													User sessionUser 			= (User) session.getAttribute("user");
													
													Integer idUser 				= ((User) session.getAttribute("user")).getId();
													
													String desc;
													
													if(c.getId_user().equals(idUser)){
													
														desc = "Demande à : ";
														
													}else{
													
														desc = "Demande de : ";
														
													}
													
													StringBuilder url_accepted = new StringBuilder();

													url_accepted.append("TraitementContact?");
													url_accepted.append("action=accepted");
													url_accepted.append("&contactLineID="+ c.getId());
													
													StringBuilder url_refused = new StringBuilder();

													url_refused.append("TraitementContact?");
													url_refused.append("action=refused");
													url_refused.append("&contactLineID="+ c.getId());
													
													
													
													
													 %>
													 
													 <u>
													 	<% out.println(desc + entry.getKey().getPseudo()); %>
													 </u>
													 
													 
													</div>
													

													<div class="col-xs-2 col-xs-offset-6 col-sm-2 col-sm-offset-6 col-md-2 col-md-offset-6 col-lg-2 col-lg-offset-6 text-center">
														<span class="badge"><% out.println(Constants.hashMapContact.get(c.getStatut())); %></span>
													</div>
												</div>
												
												<div class="row">
													
												    
												    <% //out.println("State" + state); %>
												    <% //out.println("idContact" + idContact); %>
												    <% //out.println("idSessionUser" + idUser); %></p>
												    <% //out.println("Contact Obj" + c.toString()); %>
												    
												  <% if(!sessionUser.getId().equals(c.getId_user())) { %>
												    
												    <div class="col-xs-4 col-xs-offset-4 col-sm-4 col-sm-offset-4 col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4 text-center">
												    
															<a href="<% out.println(url_accepted.toString()); %>">
																<button type="button" class="btn btn-success">Accepter</button>
															</a>
															
															<a href="<% out.println(url_refused.toString()); %>">
																<button type="button" class="btn btn-danger">Refuser</button>
															</a>
													</div>
													
												   <% } %>
													   			
												</div> 
											</li>
										<% 
		
											} 
										// end for loop
										%>
										</ul>
				  					</div>
				  				</div>
							</div>  	
					<!-- /.row -->
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
