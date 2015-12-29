<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section class="content_area floatleft">


	<div class="home_content">
	
		<c:if test="${changeinfo !=null }">
			<div id="change_info" class="info_banner">
				<c:out value="${changeinfo }"></c:out>
			</div>
		</c:if>

		<div class="table_area">
			<h3>User Table</h3>
			<table class="users_table">
				<tr>
					<th>Full Name</th>
					<th>User Name</th>
					<th>Email</th>
					<th>Status</th>
					<th>Authority</th>
					<th>Edit</th>
				</tr>
				<c:forEach var="row" items="${usersData }">
					<tr>
						<td><input class='u_fullname' type='text' name='u_fullname'
							value='${row.fullname }' readonly="readonly"></td>
						<td><input class='u_username' type='text' name='u_username'
							value='${row.username }' readonly="readonly"></td>
						<td><input class='u_email' type='text' name='u_email'
							value='${row.email }' readonly="readonly"></td>
						<td><input class='u_status' type='text' name='u_status'
							value='${row.enabled }' readonly="readonly"></td>
						<td><input class='u_authority' type='text' name='u_authority'
							value='${row.authority }' readonly="readonly"></td>
						<td class="e_option"><a class="edit_user"
							href="${pageContext.request.contextPath }/updateuserAsAdmin?${_csrf.parameterName}=${_csrf.token}&username=${row.username }">Edit</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		
		<c:if test="${errorinfo !=null }">
			<div id="project_description" class="project_description">
				<c:out value="${errorinfo }"></c:out>
			</div>
		</c:if>
		
		
	
	</div>



</section>