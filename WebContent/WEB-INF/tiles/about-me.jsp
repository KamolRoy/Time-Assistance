<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="aboutme">
	<img
		src="${pageContext.request.contextPath }/gallery/images/about_me.jpg"
		alt="image01" />
	<div class="myinfo">
		<c:choose>
			<c:when test="${param.aid==1 }">
				<h2>Java Desktop Application</h2>
				<p>Get customized java desktop application as per your condition
					for any operating system and database. Place you requirement, I
					will develop the application for you.</p>
			</c:when>
			<c:when test="${param.aid==2 }">
				<div class="infodiv">
					<h2>Java Web Application</h2>
					<p>Need java based website? With spring 4 I can develop that
						for you which including front-end design, back-end business logic,
						database management, login system, AJAX and many more.</p>
				</div>
			</c:when>
			<c:when test="${param.aid==3 }">
				<h2>HTML5, CSS and JavaScript</h2>
				<p>If you are looking for a simple website, I am here with
					creative web design skill by HTML5 and CSS and make website dynamic
					with java-scripting.</p>
			</c:when>
			<c:when test="${param.aid==4 }">
				<h2>Professional Development</h2>
				<p>You can trust on my professionalism as single or team player.
					I always focus meeting the customer requirements and delivery on
					time.</p>
			</c:when>
			<c:otherwise>
				<div class="infodiv">
					<h2>I am Kamol Roy</h2>
					Java Developer

					<p>I have a passion for solving problems and find the field of
						computer programming extremely rewarding in this sense. As long as
						I can remember I have always strived to better my understanding of
						the things around me and tried to grasp knowledge that would
						enable me to create bigger and better things.</p>

					<p>Since I've been introduced to the world of programming I
						have been engulfed by its pristinely methodical procedures. I have
						extensive experience of Java (Core), Java web development with
						Spring, Java Desktop Application, Oracle SQL, HTML, CSS,
						JavaScript, XML and Eclipse. I consider myself an expert at
						resolving application defects, and at testing, debugging, and
						refining software to produce the required product.</p>

					<p>If you're searching for a versatile, full-stack programmer
						striving for innovation, you've come to the right person! Check
						out my Profile to see what technical skills I can contribute to
						you or your organization.</p>
						
					<p>Click here to find out more: <a target="_blank" href="http://www.comolroy.com.au">www.comolroy.com.au</a></p>

				</div>
			</c:otherwise>

		</c:choose>


	</div>


</section>
