<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="local_news_area">
	<ul>
		<c:forEach var="lnews" items="${localNews}">
			<li><a href="${lnews.link }" target="_blank"><c:out
						value="${lnews.title}" /></a>
				<p class="news_description">${lnews.description }
				<p></li>
		</c:forEach>

		<c:if test="${news.size() ==0 }">
			<li><a href="http://www.abc.net.au/" target="_blank"><c:out
						value="Couldn't retrieve news from abc.net. Click here to redrict." /></a></li>
		</c:if>
	</ul>

</section>
