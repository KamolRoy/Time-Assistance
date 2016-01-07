<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

<section class="content_area floatleft">

	<div class="home_content">

		<div id="login_error" class="login_error hidden">
			<input type="hidden" id="error_value" value="${param.loginerror }"></input>
			<span style="color: #E2252B">Error: Bad username or password !</span>
		</div>

		<!--This token is required for authentication and remember the browser -->
		<form name="f" id="login_form" class="login_form hidden"
			action='${pageContext.request.contextPath}/login?${_csrf.parameterName}=${_csrf.token}'
			method='POST'>

			<div class="login-block">
				<h3>Login</h3>
				<input type="text" name="username" id="username"
					placeholder="Username" required /> <input type="password"
					name="password" id="password" placeholder="Password" required />

				<!-- <div class="remember">
					<label><input type="checkbox" name="remember-me"
						id="rememberme" checked="checked" /> Remember Me</label><br /> <a
						href="#">Forgotten username or password?</a>
				</div> -->


				<input name="submit" type="submit" value="Submit" /> <input
					id="cancel" name="cancle" type="button" value="Cancel" />

			</div>
		</form>

		<div class="create_account_area hidden">
			<input type="hidden" id="show_register" value="${showRegister }"></input>
			<div id="register" class="register">
				<h3>Create your account</h3>

				<sf:form class="reg_form"
					action="${pageContext.request.contextPath}/createuser?${_csrf.parameterName}=${_csrf.token}"
					method="post" commandName="user">
					<div>
						<label for="fullname">Name</label>
						<sf:input type="text" path="fullname" name="fullname"
							id="fullname" spellcheck="false" placeholder="Kamol Roy" />
						<sf:errors path="fullname" cssClass="form_error" />
					</div>
					<div>
						<label for="email">Email*</label>
						<sf:input type="email" id="email" path="email" name="email"
							spellcheck="false" placeholder="comolroy@gmail.com" />
						<sf:errors path="email" cssClass="form_error" />
					</div>
					<div>
						<label for="name">Username*</label>
						<sf:input type="text" id="name" path="username" name="username"
							spellcheck="false" placeholder="kkroy" />
						<sf:errors path="username" cssClass="form_error" />
					</div>
					<div>
						<label for="password">Password*</label>
						<sf:input type="password" path="password" name="password"
							id="password-ini" />
						<sf:errors path="password" cssClass="form_error" />
					</div>
					<div>
						<label for="password-again">Password Again</label> <input
							type="password" id="password-again" /><br>
						<div id="passmatch" class="form_error"></div>
					</div>
					<div>
						<label></label> <input type="submit" value="Create Account"
							id="create-account" class="button" /> <input type="button"
							value="Cancel" id="cancel-account" class="button" />
					</div>
				</sf:form>
			</div>
		</div>

		<div class="slider_area">
			<div id="da-slider" class="da-slider">
				<div class="da-slide">
					<h2>Java Desktop Application</h2>
					<p>Get customized java desktop application as per you
						requirement.</p>
					<a
						href="${pageContext.request.contextPath }/aboutme?${_csrf.parameterName}=${_csrf.token}&aid=1"
						class="da-link">Read more</a>
					<div class="da-img">
						<img
							src="${pageContext.request.contextPath }/gallery/images/10.jpg"
							alt="image01" />
					</div>
				</div>
				<div class="da-slide">
					<h2>Java Web Application</h2>
					<p>Need a Java based website? With spring framework I can develop that for you.</p>
					<a
						href="${pageContext.request.contextPath }/aboutme?${_csrf.parameterName}=${_csrf.token}&aid=2"
						class="da-link">Read more</a>
					<div class="da-img">
						<img
							src="${pageContext.request.contextPath }/gallery/images/20.jpe"
							alt="image01" />
					</div>
				</div>
				<div class="da-slide">
					<h2>HTML5, CSS and JavaScript</h2>
					<p>Looking for a static front-end website? Get it done with HTML5, CSS3 and jQuery.</p>
					<a
						href="${pageContext.request.contextPath }/aboutme?${_csrf.parameterName}=${_csrf.token}&aid=3"
						class="da-link">Read more</a>
					<div class="da-img">
						<img
							src="${pageContext.request.contextPath }/gallery/images/30.jpg"
							alt="image01" />
					</div>
				</div>
				<div class="da-slide">
					<h2>Professional Development</h2>
					<p>You can trust on my professionalism as unsupervised or as a team player.</p>
					<a
						href="${pageContext.request.contextPath }/aboutme?${_csrf.parameterName}=${_csrf.token}&aid=4"
						class="da-link">Read more</a>
					<div class="da-img">
						<img
							src="${pageContext.request.contextPath }/gallery/images/40.jpg"
							alt="image01" />
					</div>
				</div>
				<nav class="da-arrows">
					<span class="da-arrows-prev"></span> <span class="da-arrows-next"></span>
				</nav>
			</div>
		</div>
		<div id="project_description" class="project_description">
			<h3>Project Description</h3>

			<p>This project is basically a demonstration of spring 4
				framework's web development. Here one can create an account and give
				entry for an event to get a email notification prior to the time set
				by user. User can also update his profile information and change,
				delete the event information.</p>

			<p>I have followed here MVC design pattern and included spring
				security 4 login system which can simply be integrated to more
				complex project. I should mention here the key features have
				integrated with this project.</p>

			<p>
				<span style="font-weight: bold">Spring Security 4:</span> Login,
				Logout, Remember Me function and Password encryption. Role based
				home page implementation and also role based method level access
				control. This will clearly specify the boundary where the user can
				surf.
			</p>

			<p>
				<span style="font-weight: bold">MVC Design Pattern:</span> The
				project design structure include Controller Layer, Service Layer and
				Data Access Object with the help of Java Bean. Configuration and
				communication between beans is written with Spring bean
				configuration file.
			</p>

			<p>
				<span style="font-weight: bold">Hibernate:</span> Here I used Mysql
				Database (Any Database can be used) and to access the DB I used
				Hibernate to turn the database into object. Which replace the
				requirement of hand written complex query.
			</p>

			<p>
				<span style="font-weight: bold">Database Pool Connection:</span> To
				optimize the db connection like load shearing, disconnect idle
				connection I implement here DHCP basic data-source pool connection.
			</p>

			<p>
				<span style="font-weight: bold">Form Validation:</span> Spring
				standard form validation is used while creating new user account and
				when a user create a new event for notification. Here I used two
				validation group, Form and Hibernate validation group.
			</p>

			<p>
				<span style="font-weight: bold">Apache Tiles:</span> To manage the
				JSP pages I used Apache tiles as view resolver. Header, footer, news
				and menu section is designed and kept separately for re-usability.
			</p>
			<p>
				<span style="font-weight: bold">RSS Feed:</span> Local news are
				being parsed from www.abc.net.au rss feed.
			</p>
			<p>
				<span style="font-weight: bold">Mail Service:</span> Spring simple
				mail service in integrated to mail the user about his/her saved
				event prior to the selected duration.
			</p>
			<p>
				<span style="font-weight: bold">Schedule Task:</span> Schedule demon
				design for periodically read and update the news feed and check the
				active status of different events.
			</p>
			<p>
				<span style="font-weight: bold">Help:</span> This site is very
				simple to use. For visual help and project description click <a
					href="${pageContext.request.contextPath }/help?${_csrf.parameterName}=${_csrf.token}">here</a>.
			</p>
		</div>
	</div>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/gallery/js/modernizr.custom.28468.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/gallery/js/jquery.cslider.js"></script>


	<script type="text/javascript">
		$(function() {
			$('#da-slider').cslider({
				autoplay : true,
				bgincrement : 450
			});

		});
	</script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/gallery/js/custom.js">
		
	</script>

</section>
