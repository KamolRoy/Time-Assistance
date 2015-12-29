<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<header class="header_area">
	<div class="header_icon"></div>
	<div class="header_logo">
		<h1>Time Assistance</h1>
		<h2>
			<span class="spring_icon"></span>A sample spring project
		</h2>
	</div>
	<div class="login_area">

		<c:if test="${pageInfo =='unusual'}">
			<!-- pageInfo is supplied from controller, it is providing the necessity of appear the login area -->
			<c:set scope="page" var="loginClass" value="hidden"></c:set>
		</c:if>


		<sec:authorize access="isAuthenticated()">
			<c:set scope="page" var="loginClass" value="hidden"></c:set>
			<h3 id="welcome_note" class="welcome_note">
				Welcome <span style="text-transform: capitalize;"><c:out
						value="${pageUser }"></c:out></span>
			</h3>
			
			<form action="${pageContext.request.contextPath }/logout"
				method="post">
				<input id="logout" class="logout" type="submit" value="Logout" /> <input
					type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</sec:authorize>
		<div class=${loginClass }>
			<h3>
				<a href="#ta" id="login_here">Login Here</a>
			</h3>
			<h4>
				<a href="#" id="create_account" class="create_account">Create
					account</a>
			</h4>
		</div>
	</div>
</header>

<div class="menu_area">
	<div>
		<ul>
			<li><a href="${pageContext.request.contextPath }/home?${_csrf.parameterName}=${_csrf.token}">Home</a></li>
			<li><a href="${pageContext.request.contextPath }/userhome?${_csrf.parameterName}=${_csrf.token}">My Home</a></li>
			<li><a href="${pageContext.request.contextPath }/localnews?${_csrf.parameterName}=${_csrf.token}">News</a></li>
			<li><a href="${pageContext.request.contextPath }/aboutme?${_csrf.parameterName}=${_csrf.token}">About</a></li>
			<li><a href="${pageContext.request.contextPath }/help?${_csrf.parameterName}=${_csrf.token}">Help</a></li>
		</ul>
	</div>
</div>
