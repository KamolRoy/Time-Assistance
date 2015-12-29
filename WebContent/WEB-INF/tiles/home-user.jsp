<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<section class="content_area floatleft">


	<div class="home_content">

		<div class="user_choice">
			<p>
				<a href="#ta" id="create_cancel_event" class="user_option" >Create event</a>
			</p>
			<p>
				<a href="${pageContext.request.contextPath }/updateuser?${_csrf.parameterName}=${_csrf.token}" id="update_profile" class="user_option">Update profile</a>
			</p>
			<sec:authorize access="hasRole('ADMIN')">
				<p>
					<a href="${pageContext.request.contextPath }/adminhome?${_csrf.parameterName}=${_csrf.token}" id="adminhome" class="user_option">Admin Page</a>
				</p>
			</sec:authorize>
		</div>

		<input type="hidden" id="showCEvent" value="${showCEvent }"></input>
		<c:if test="${changeinfo !=null }">
			<div id="change_info" class="info_banner">
				<c:out value="${changeinfo }"></c:out>
			</div>
		</c:if>

		<div class="event_entry_block hidden">
			<div id="event_entry" class="event_entry">
				<h3>Event Entry</h3>
				<sf:form class="event_entry_from"
					action="${pageContext.request.contextPath }/createevent?${_csrf.parameterName}=${_csrf.token}"
					method="post" commandName="timeData">
					<div>
						<label for="event-name">Event Name<span>*</span></label>
						<sf:input path="event_name" name="event_name" type="text"
							id="event-name" spellcheck="false" placeholder="Meet a client" />
						<sf:errors path="event_name" cssClass="form_error"></sf:errors>
					</div>
					<div>
						<label for="event-description">Event Description</label>
						<sf:input path="event_description" name="event_description"
							type="text" id="event-description" spellcheck="false"
							placeholder="ABC company meeting" />
						<sf:errors path="event_description" cssClass="form_error"></sf:errors>
					</div>
					<div>
						<label for="event_datetime">Event DateTime</label>
						<sf:input type="text" path="event_time" name="my_event_time"
							id="event_date_time" spellcheck="false"
							placeholder="DD MMM YYYY HH:mm" />
						<sf:errors path="event_time" cssClass="form_error"></sf:errors>
					</div>
					<div>
						<label for="suration">Notify</label>
						<sf:select path="duration" name="duration"
							items="${durationList }" id="duration" />
						min before.
						<sf:errors path="duration" cssClass="form_error"></sf:errors>
					</div>
					<div class="event_otions">
						<div>
							<input name="send_email" type="checkbox" id="send-email" checked />
							<label for="send-email">Send Email</label>

						</div>
						<div>
							<input name="add_gcal" type="checkbox" id="gcal" /> <label
								for="gcal">Add to Google Calendar</label>
						</div>
					</div>
					<div>
						<label></label> <input type="submit" value="Submit"
							id="create_event" class="button" /> <input type="button"
							value="Cancel" id="cancel_event" class="button" />
					</div>
				</sf:form>

			</div>

		</div>

		<div class="table_area">
			<h3>Event Table</h3>
			<table class="event_table">

				<c:choose>
					<c:when test="${userTimeData.size() >0 }">
						<tr>
							<th>Name</th>
							<th>Description</th>
							<th>Date Time</th>
							<th>Send Email</th>
							<th>Add GCal</th>
							<th>Edit</th>
						</tr>
						<c:forEach var="row" items="${userTimeData }">
							<tr class='datarow'>
								<td><input class='e_name' type='text' name='e_name'
									value='${row.event_name }' readonly="readonly"></td>
								<td><input class='e_description' type='text'
									name='e_description' value='${row.event_description }'
									readonly="readonly"></td>
								<td><input class='e_datetime' type='text' name='e_datetime'
									value='${row.event_time }' readonly="readonly"></td>
								<td class="e_option"><c:choose>
										<c:when test="${row.send_email }">
											<input class='e_sendmail' type='checkbox' name='e_sendmail'
												checked disabled>
										</c:when>
										<c:otherwise>
											<input class='e_sendmail' type='checkbox' name='e_sendmail'
												disabled>
										</c:otherwise>

									</c:choose></td>
								<td class="e_option"><c:choose>
										<c:when test="${row.add_gcal }">
											<input class='e_gcal' type='checkbox' name='e_gcal' checked
												disabled>
										</c:when>
										<c:otherwise>
											<input class='e_gcal' type='checkbox' name='e_gcal' disabled>
										</c:otherwise>
									</c:choose></td>
									
								<td class="e_option">
								
								<c:choose>
										<c:when test="${row.active_status }">
											<a class="edit_event" href="${pageContext.request.contextPath }/updateevent?${_csrf.parameterName}=${_csrf.token}&id=${row.id }">Edit</a>
										</c:when>
										<c:otherwise>
											<a class="edit_event" style="pointer-events: none;" 
											href="${pageContext.request.contextPath }/updateevent?${_csrf.parameterName}=${_csrf.token}&id=${row.id }" >
											<span style="color:silver;">Edit</span></a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div class="noData">No saved event.</div>
					</c:otherwise>
				</c:choose>





				<!-- <tr class='newdatarow'>
					<td><input class='e_name' type='text' name='e_name'
						value='ABC' disabled></td>
					<td><input class='e_description' type='text'
						name='e_description' value='DEF' disabled></td>
					<td><input class='e_datetime' type='text' name='e_datetime'
						value='DEF' disabled></td>
					<td class="e_option"><input class='e_sendmail' type='checkbox'
						name='e_sendmail' disabled></td>
					<td class="e_option"><input class='e_gcal' type='checkbox'
						name='e_gcal' disabled></td>
					<td class="e_option">Icon</td>
				</tr> -->

			</table>
		</div>
	</div>





	<script type="text/javascript"
		src="${pageContext.request.contextPath}/gallery/js/user.custom.js">
		
	</script>

</section>