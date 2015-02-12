<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<jsp:include page="header.jsp" />

<title>Sign-In</title>
</head>


<body>

	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please Sign-In</h3>
					</div>
					<div class="panel-body">

						<c:if test="${not empty message}">
							<div class="alert alert-danger alert-dismissable">
								${message}
							</div>
						</c:if>

						<form role="form" action="signin" method="POST"
							data-toggle="validator">
							<fieldset>
								<div class="form-group">
									<label class="control-label">Name</label> <input
										class="form-control" placeholder="Name" name="name"
										type="text" data-delay="10" required>
									<div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label class="control-label">E-mail</label> <input
										class="form-control" placeholder="E-mail" name="email"
										type="email"
										pattern="[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,4}"
										required>
									<div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label class="control-label">Phone</label> <input
										class="form-control" placeholder="Phone" name="phone"
										type="tel"
										pattern="^0[1-6]{1}(([0-9]{2}){4})|((\s[0-9]{2}){4})|((-[0-9]{2}){4})$"
										value="" required>
									<div class="help-block with-errors"></div>
								</div>
								<div class="form-group">
									<label class="control-label">Password</label> <input
										id="password" class="form-control" placeholder="Password"
										name="password" type="password" value="" data-minlength="5"
										data-error="Minimum of 5 characters" required>
									<div class="help-block with-errors"></div>
								</div>

								<div class="form-group">
									<label class="control-label">Password Confirm</label> <input
										type="password" class="form-control" name="passwordConfirm"
										id="inputPasswordConfirm" data-match="#password"
										data-match-error="Whoops, these don't match"
										placeholder="Confirm" required>
									<div class="help-block with-errors"></div>
								</div>

								<div class="form-group">
									<button type="submit" class="btn btn-lg btn-success btn-block">Submit</button>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>



</body>