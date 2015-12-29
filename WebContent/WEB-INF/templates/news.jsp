<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<div class="news_feed floatleft">
	<!--<img src="images/news.png" alt="News">-->
	<h2>News</h2>

	<ul>
		<c:forEach var="lnews" items="${news}">
			<li><a href="${lnews.link}" target="_blank"><c:out
						value="${lnews.title}" /></a></li>
		</c:forEach>
		<c:if test="${news.size() ==0 }">
			<li><a href="http://www.abc.net.au/" target="_blank"><c:out
						value="Couldn't retrieve news from abc.net. Click here to redrict." /></a></li>
		</c:if>
	</ul>

</div>