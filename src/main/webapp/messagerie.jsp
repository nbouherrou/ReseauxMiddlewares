<%@page import="org.ocpsoft.prettytime.PrettyTime"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="org.ikya.dao.UserDao"%>
<%@page import="org.ikya.entities.Message"%>
<%@page import="org.ikya.utils.OpenJPAUtils"%>
<%@page import="org.ikya.entities.User"%>
<%@page import="java.util.Set"%>
<%@page import="org.ikya.dao.MessagerieDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<jsp:include page="header.jsp" />

<title>Messagerie</title>
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
						<h1 class="page-header text-center">Messagerie :</h1>


						<div class="chat-panel panel panel-default">
							<div class="panel-heading">
								<i class="fa fa-comments fa-fw"></i>
								<c:forEach items="${listUser}" var="list">
								<button class="btn btn-primary" >
									<c:out value="${list}" />
								</button>
								</span>
								</c:forEach>
								<div class="btn-group pull-right">
									<button type="button"
										class="btn btn-default btn-xs dropdown-toggle"
										data-toggle="dropdown">
										<i class="fa fa-chevron-down"></i>
									</button>
									<ul class="dropdown-menu slidedown">
										<li><a href="#"> <i class="fa fa-refresh fa-fw"></i>
												Refresh
										</a></li>
										<li><a href="AddUser?idMessagerie=${idMessagerie}"> <i
												class="fa fa-plus-square"></i> Ajouter des amis
										</a></li>
									</ul>
								</div>
							</div>
							<!-- /.panel-heading -->
							<div id="div1" class="panel-body">
							<%
								UserDao userDao = new UserDao();
								MessagerieDao messagerieDao = new MessagerieDao();
								
								Set<Message> messagerieList = (Set<Message>) request.getAttribute("listMessage");
								
								
								
								List<Message> list = new ArrayList(messagerieList);
								
								Collections.sort(list, new Comparator<Message>(){
		
									public int compare(Message c1, Message c2){
							 
										return c1.getId().compareTo(c2.getId());
							 
									}
							 
								});
								
							 %>
							
							
							<%
							if(list != null){
							
							PrettyTime p = new PrettyTime();
							
							for( Message message : list){ %>
								<ul class="chat">
									<li class="left clearfix">
									
									
										<div class="clearfix">
											<div class="header">
											
											
												<strong class="primary-font"><%= userDao.findByUserID(message.getId_user()).getPseudo() %></strong> <small
													class="pull-right text-muted"> <i
													class="fa fa-clock-o fa-fw"></i> <%= p.format(messagerieDao.getMessageDate(message.getId())) %>
												</small>
											</div>
											<p><%= message.getMessage() %></p>
										</div></li>
										
								</ul>
							<%  }
							   } %>
							</div>
							<!-- /.panel-body -->
							<div class="panel-footer">
								<div class="input-group">
									<input id="btn-input" type="text" class="form-control input-sm"
										placeholder="Type your message here..." /> <span
										class="input-group-btn"> 
										
									<input class="btn btn-warning btn-sm" id="btn-chat" type="button" value="Submit"/>

									</span>
								</div>
							</div>


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
	</div>


	<script>
   
	
	$("#btn-chat").click(function() {

	    var mge = $('#btn-input').val();
	    var idMessagerie = "<c:out value='${idMessagerie}'/>";
	    var chat_div = document.getElementById('div1');
	  

	    $.ajax({
	        type: "POST",
	        url: "AddMessage",
	        data: { message : mge, idMessagerie : idMessagerie},
	        success: function( result ){
	        	 $('#btn-input').val('');

 	  			chat_div.scrollTop = chat_div.scrollHeight;
              },
              complete: function (data) {
				chat_div.scrollTop = chat_div.scrollHeight;
				
		      }
	      })
		
	 });
	 
	    var interval = 1000;  
		function doAjax() {
		
		var mge = $('#btn-input').val();
	    var idMessagerie = "<c:out value='${idMessagerie}'/>";

	    $.ajax({
	        type: "POST",
	        url: "RefreshServlet",
	        data: { idMessagerie : idMessagerie},
	        success: function( result ){
	        	console.log(result);
	        	
	        	$( "div.panel-body" ).html(result);

              },
              complete: function (data) {
		                    // Schedule the next
		         setTimeout(doAjax, interval);
		         

		       }
	      })
			
		}
	    
		
	
	setTimeout(doAjax, interval);
	 
	 

	
    
	  var $t = $('.panel-body');
	  $t.animate({"scrollTop": $('.panel-body')[0].scrollHeight}, "slow");

    </script>



</body>
