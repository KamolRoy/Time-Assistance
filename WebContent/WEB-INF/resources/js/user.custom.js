/**
 * This script is used by home-user.jsp
 */

/* global $, console, alert */

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
						$cancle_event = $('#cancel_event'),
						$change_info = $('#change_info');
					;

					$login_here.hide();
					$create_account.hide();
					$welcome_note.show();
					$logout.show();
					$change_info.delay(5000).slideUp('slow')

					$('#event_date_time').datetimepicker({
						dateFormat : 'dd M yy',
						minDate : 0
					});

					$('.event_entry_block').removeClass('hidden');

					if ($('#showCEvent').val() === 'true') {
						$create_cancel_event.text("Cancel event");
						$event_entry.show();
					} else {
						$event_entry.hide();
					}

					$create_cancel_event
							.click(function() {
								console.log($create_cancel_event.text());
								/*($create_cancel_event.text() === "Create event") ? ($create_cancel_event
										.text("Cancel event"))
										: ($create_cancel_event
												.text("Create event"));*/
								// $event_entry.slideToggle('slow');
								
								if ($create_cancel_event.text() === 'Create event') {
									$event_entry.slideDown('slow');
									$create_cancel_event.text('Cancel event');
								} else if($create_cancel_event.text() === 'Cancel event') {
									$event_entry.slideUp('slow');
									$create_cancel_event.text('Create event');
								}
							});

					 $('#cancel_event').click(function() {
						 $event_entry.slideUp('slow');
						 $create_cancel_event.text("Create event");
					 });

				});