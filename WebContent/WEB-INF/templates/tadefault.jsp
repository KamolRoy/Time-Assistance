<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Time Assistance</title>

<link rel="shortcut icon"
	href="${pageContext.request.contextPath }/gallery/images/spring.png">
<link type="text/css"
	href="${pageContext.request.contextPath}/gallery/css/style.css"
	rel="stylesheet" />
<link type="text/css"
	href="${pageContext.request.contextPath}/gallery/css/slider.css"
	rel="stylesheet" />
<link type="text/css"
	href="${pageContext.request.contextPath}/gallery/css/login.css"
	rel="stylesheet" />
<link rel="stylesheet" media="all" type="text/css"
	href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css" />
<link type="text/css"
	href="${pageContext.request.contextPath}/gallery/css/jquery-ui-timepicker-addon.css"
	rel="stylesheet" />


<script type="text/javascript"
	src="//code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="http://code.jquery.com/ui/1.11.0/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/gallery/js/jquery-ui-timepicker-addon.js"></script>


</head>


<body>
	<div class="container">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>

		<tiles:insertAttribute name="news"></tiles:insertAttribute>

		<tiles:insertAttribute name="tacontent"></tiles:insertAttribute>

		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>



</body>
</html>