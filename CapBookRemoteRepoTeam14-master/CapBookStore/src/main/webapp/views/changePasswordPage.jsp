<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">

  function checkForm(form)
  {

		if (form.password.value != "") {
			if (form.password.value.length < 6) {
				alert("Error: Password must contain at least six characters!");
				form.password.focus();
				return false;
			}
			re = /[0-9]/;
			if (!re.test(form.password.value)) {
				alert("Error: password must contain at least one number (0-9)!");
				form.password.focus();
				return false;
			}
			re = /[!@#$%^&*()_+]/;
			if (!re.test(form.password.value)) {
				alert("Error: password must contain at least one special character(!@#$%^&*()_+)!");
				form.password.focus();
				return false;
			}
			re = /[a-z]/;
			if (!re.test(form.password.value)) {
				alert("Error: password must contain at least one lowercase letter (a-z)!");
				form.password.focus();
				return false;
			}
			re = /[A-Z]/;
			if (!re.test(form.password.value)) {
				alert("Error: password must contain at least one uppercase letter (A-Z)!");
				form.password.focus();
				return false;
			}
		} else {
			alert("Error: Please check that you've entered and confirmed your password!");
			form.password.focus();
			return false;
		}

	}
</script>
<!------ Include the above in your HEAD tag ---------->
<br>
<br>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<div class="form-gap"></div>
<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="text-center">
						<h3>
							<i class="fa fa-lock fa-4x"></i>
						</h3>
						<h2 class="text-center">Change Password?</h2>
						<div class="panel-body">

							<form action="changePassword1" method="post"
								onsubmit="return checkForm(this);">

								<div class="form-group">
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-envelope color-blue"></i></span> <input
											id="question" name="emailId" placeholder="Email Id"
											class="form-control" type="email"> <input
											id="question" name="password" placeholder="New Password"
											class="form-control" type="password"> <input
											id="question" name="cpassword"
											placeholder="New Confirm Password" class="form-control"
											type="password">
									</div>
								</div>
								<div class="form-group">
									<input name="recover-submit"
										class="btn btn-lg btn-primary btn-block"
										value="Change Password" type="submit">
								</div>

								<input type="hidden" class="hide" name="token" id="token"
									value="">
							</form>

						</div>
						<a href="userProfile1">Back To Home Page</a>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>