/**
 * This script is used by home-user.jsp
 */

/* global $, console, alert */


/*
 * When user click delete a event
 */
function onDeleteClick(event){
	
	
	var doDelete = confirm("Do you want to delete?");
	
	if(doDelete == false){
		event.preventDefault();
	}
}

/*
 * Working with Creat Account Pass and ConfirmPass field
 */
function canSubmit() {
	$password_ini = $('#password-ini').val(), $password_again = $(
			'#password-again').val();
	if (document.getElementById("changepass").checked){
		if ($password_ini == $password_again) {
			return true;
		} else {
			alert("Password and Confirm Password doesn't match");
			return false;
		}
	}else{
		return true;
	}
	
}

function matchPassword() {
	$password_ini = $('#password-ini').val(), $password_again = $(
			'#password-again').val();

	if ($password_ini.lenght < 5 || $password_again.length < 5) {
		return;
	} else {
		if ($password_ini == $password_again) {
			$('#passmatch').text("");
			$('#create_account').prop('disabled', false);
		} else {
			$('#passmatch').text("Password and Confirm Password doesn't match");
		}

	}
}

function passFields() {
	if (document.getElementById("changepass").checked) {
		$('.reg_form').find('input[type=password]').prop("disabled", false)
				.css("background", "#FEE5AC");
		$('#password-ini').val('');
	} else {
		$('.reg_form').find('input[type=password]')
				.attr('disabled', 'disabled').css("background",
						"rgba(0, 0, 0, 0.07)");
		$('#password-ini').val('123456');
	}
	
}

$(document)
		.ready(
				function() {
					"use strict";

					var $login_here = $('#login_here'), 
						$create_account = $('#create_account'), 
						$welcome_note = $('#welcome_note'), 
						$logout = $('#logout'), 
						$create_cancel_event = $('#create_cancel_event'), 
						$event_entry = $('#event_entry'), 
						$register = $('#register'), 
						$password_ini = $('#password-ini'), 
						$password_again = $('#password-again');
					
					
					$login_here.hide();
					$create_account.hide();
					$welcome_note.show();
					$logout.show();
					$('#delete_event').click(onDeleteClick);
					$('#delete-account').click(onDeleteClick);
					
					$('#event_date_time').datetimepicker({
						dateFormat : 'dd M yy',
						minDate : 0
					});

					$password_ini.keyup(matchPassword);
					$password_again.keyup(matchPassword);

					if ($('#updatetype').val() === 'event') {
						$event_entry.show();
						$register.hide();
					} else if ($('#updatetype').val() === 'user') {
						$register.show();
						$event_entry.hide();
						
						if ($('#checkvalue').val() == 'checked') {
							document.getElementById("changepass").checked = true;
						} else {
							document.getElementById("changepass").checked = false;
						}
						
					}

					passFields();

					$('#changepass').click(passFields);
					$('.reg_form').submit(canSubmit);
				});
