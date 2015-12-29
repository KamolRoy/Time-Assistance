<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<section class="content_area floatleft">

	<div class="home_content">

		<input type="hidden" id="updatetype" value="${updatetype }"></input>


		<div class="event_entry_block">
			<div id="event_entry" class="event_entry">
				<h3>Update Event Information</h3>
				<sf:form class="event_entry_from"
					action="${pageContext.request.contextPath }/doupdate?${_csrf.parameterName}=${_csrf.token}"
					method="post" commandName="timeData">
					<sf:input path="id" type="hidden" name="id" />
					<div>
						<label for="event-name">Event Name<span>*</span></label>
						<sf:input path="event_name" name="event_name" type="text"
							id="event-name" spellcheck="false" placeholder="Meet a client" />
						<sf:errors path="event_name" cssClass="form_error"></sf:errors>
					</div>
					<div>
						<label for="event-description">Event Description*</label>
						<sf:input path="event_description" name="event_description"
							type="text" id="event-description" spellcheck="false"
							placeholder="ABC company meeting" />
						<sf:errors path="event_description" cssClass="form_error"></sf:errors>
					</div>
					<div>
						<label for="event_datetime">Event DateTime*</label>
						<sf:input type="text" path="event_time" name="my_event_time"
							id="event_date_time" spellcheck="false" />
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
						<label></label> <input type="submit" name="action" value="Update"
							id="update_event" class="button" /> <input type="submit"
							name="action" value="Delete" id="delete_event" class="button" />
						<input type="submit" name="action" value="Cancel"
							id="cancel_event" class="button" />
					</div>
				</sf:form>

			</div>

		</div>

		<div class="create_account_area">

			<div id="register" class="register">
				<h3>Update User Profile</h3>
				<c:choose>
					<c:when test="${updateAttribute == 'asadmin' }">
						<c:set var="formAction"
							value="${pageContext.request.contextPath}/doupdateuserAsAdmin?${_csrf.parameterName}=${_csrf.token}"
							scope="page">
						</c:set>
					</c:when>
					<c:otherwise>
						<c:set var="formAction"
							value="${pageContext.request.contextPath}/doupdateuser?${_csrf.parameterName}=${_csrf.token}"
							scope="page">
						</c:set>
					</c:otherwise>
				</c:choose>
				<sf:form class="reg_form" action="${formAction }" method="post"
					commandName="user">
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
						<label for="name">Username</label>
						<sf:input type="text" id="name" path="username" name="username"
							spellcheck="false" placeholder="kkroy" readonly="true" />
						<sf:errors path="username" cssClass="form_error" />
					</div>
					<input type="hidden" id="checkvalue" value="${checkvalue }">
					<div>
						<h3>
							<input id="changepass" name="changepass" type="checkbox"
								id="send-email" checked="${checkvalue }" /> Change Password
						</h3>
					</div>
					<div>
						<label for="password">Old Password*</label> <input type="password"
							name="oldpassword" id="password-old" disabled="disabled" />
						<c:if test="${matchpass == 'notmatch' }">
							<span class="form_error">Old password doesn't match</span>
						</c:if>
					</div>
					<div>
						<label for="password">New Password*</label>
						<sf:input type="password" path="password" name="password"
							id="password-ini" value="123456" disabled="true" />
						<sf:errors path="password" cssClass="form_error" />
					</div>
					<div>
						<label for="password-again">Password Again*</label> <input
							type="password" id="password-again" disabled="disabled" /><br>
						<div id="passmatch" class="form_error"></div>
					</div>
					<div>
						<label></label> <input type="submit" name="action" value="Update"
							id="create-account" class="button" />
						<sec:authorize access="hasRole('ADMIN')">
							<input type="submit" name="action" value="Delete"
								id="delete-account" class="button" />
						</sec:authorize>
						<input type="submit" name="action" value="Cancel"
							id="cancel-account" class="button" />
					</div>
				</sf:form>
			</div>
		</div>

	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/gallery/js/updateinfo.custom.js"></script>

</section>