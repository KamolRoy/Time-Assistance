<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content_area floatleft">
	<div class="home_content">

		<c:if test="${param.loginerror != null }">
			<h3>Login Error</h3>
		</c:if>

		<div class="info_banner"><span style="color:green">Congratulation !!! </span><br/>New User Created. Please login now.</div>

		<form name="f" id="login_form" class="login_form"
			action='${pageContext.request.contextPath}/login?${_csrf.parameterName}=${_csrf.token}'
			method='POST'>
			<div id="login_error" class="login_error hidden">
				<input type="hidden" id="error_value" value="${param.loginerror }"></input>
				Error: Bad username or password !
			</div>
			<div class="login-block">
				<h3>Login</h3>
				<input type="text" name="username" id="username"
					placeholder="Username" autofocus="autofocus" required /> <input type="password"
					name="password" id="password" placeholder="Password" required />

				<div class="remember">
					<label><input type="checkbox" name="remember-me"
						id="rememberme" checked="checked" /> Remember Me</label><br /> <a
						href="#">Forgotten username or password?</a>
				</div>

				<input name="submit" type="submit" value="Submit" />
				<a href="${pageContext.request.contextPath}/login?${_csrf.parameterName}=${_csrf.token}">
				<input id="cancel2" name="cancle" type="button"  value="Cancel" />
				</a>

			</div>
		</form>

	</div>
</section>