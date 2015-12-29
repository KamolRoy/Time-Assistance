/*global $, console, alert */

$(document)
		.ready(
				function() {
					"use strict";

					var $login_here = $('#login_here'), 
						$login_form = $('#login_form'), 
						$welcome_note = $('#welcome_note'), 
						$project_description = $('#project_description'), 
						$create_account = $('#create_account'), 
						$register = $('#register'), 
						$login_error = $('#login_error'), 
						$password_ini = $('#password-ini'), 
						$password_again = $('#password-again');

					$create_account.prop('disabled', true);
					$password_ini.keyup(matchPassword);
					$password_again.keyup(matchPassword);
					$('.reg_form').submit(canSubmit);

					if ($('#error_value').val() === 'true') {
						$login_form.show();
						$register.hide();
						$project_description.hide();
						/* $welcome_note.hide(); */
						$login_here.hide();
						$login_error.removeClass('hidden');

					} else {
						$login_form.hide();
						$register.hide();
						/* $welcome_note.hide(); */
						$login_error.addClass('hidden');
					}

					// ****************** Click cancle login here
					$login_here.click(function() {

						$project_description.fadeOut('fast', function() {
							$login_form.slideDown('slow', function() {
								$('#username').focus();
							});
							$login_here.hide();
							$welcome_note.show();
						});
					});

					$('#cancel').click(function() {
						$login_form.slideUp('slow', function() {
							$welcome_note.hide();
							$login_here.show();
							$project_description.fadeIn();
							$login_error.fadeOut('fast');
						});
					});

					// ******************* Finish Click Cancel login here

					if ($('#show_register').val() === 'true') {
						$('.create_account_area').removeClass('hidden');
						$register.show();
						$create_account.hide();
						$login_here.hide();
						$welcome_note.show();
						$project_description.hide();
					}

					// ******************* Click Create Account Link
					// *******************
					$create_account.click(function() {

						$project_description.fadeOut('fast', function() {
							$login_form.slideUp('slow',
									function() {
										$('.create_account_area').removeClass(
												'hidden');
										$register.slideDown('slow', function() {
											$('#fullname').focus();
										});
										$create_account.hide();
										$login_here.hide();
										$login_error.fadeOut('fast');
										$welcome_note.show();
									});
						});
					});

					// ******************* Click cancel create account
					// *********************
					$('#cancel-account').click(function() {

						$register.slideUp('slow', function() {
							$project_description.fadeIn('fast');
							$welcome_note.hide();
							$login_here.show();
							$create_account.show();
						});
					});

				});

// ******************* Working with Creat Account Pass and ConfirmPass field
// ******************

function canSubmit() {
	$password_ini = $('#password-ini').val(), $password_again = $(
			'#password-again').val();

	if ($password_ini == $password_again) {
		return true;
	} else {
		alert("Password and Confirm Password doesn't match");
		return false;
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