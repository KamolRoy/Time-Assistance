package com.devcomol.ta.schedulejob;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.devcomol.ta.dao.TimeData;
import com.devcomol.ta.service.TAMailService;



@Component
public class MailHandler {

	@Autowired
	@Qualifier(value="mailSender")
	private MailSender mailSender;
	
	@Autowired
	@Qualifier(value = "taMailService")
	TAMailService taMailService;
	
	
	
	public void sendMail(String receiverAddress, String subject, String text, int id){
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("comolroy@gmail.com");
		mail.setTo(receiverAddress);
		mail.setSubject(subject);
		mail.setText(text);
		
		try {
			mailSender.send(mail);
			System.out.println("\nMail Send Successfull with timedata id: " + id );
		} catch (MailException e) {
			System.out.println("Something worng!!! Can't send mail");
			e.printStackTrace();
		}
		
	}
	
	
/*	@Scheduled(cron = "0 0/5 * * * ?")
	public void changeActiveStatus() {
		taMailService.changeActiveStatus();
	}
	*/
	
	@Scheduled(cron = "0 0/1 * * * ?")
	public void sendingMail() {
		System.out.println("*");
		final long ONE_MINUTE_IN_MILLIS = 60000;
		List<TimeData> allTimeData=taMailService.getTimeData();
		
		if(allTimeData.size()>0) System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		for(TimeData timeData: allTimeData){
			SimpleDateFormat localDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
			
			
			Long event_Time= timeData.getEvent_time().getTime();
			int duration = timeData.getDuration();
			Date mailingTime = new Date(event_Time - (duration*ONE_MINUTE_IN_MILLIS));
			Date currentTime = new Date();
			
			
			System.out.print(currentTime + " :>> User: " + timeData.getUsername() + " || EventName: " + timeData.getEvent_name() + " || EventTime: " + timeData.getEvent_time() + " || MailingTIme: " + mailingTime + " || ");
			
			
			if((currentTime.compareTo(mailingTime))>0){
				System.out.print("Time to Send Mail");
				StringBuilder mailBody=new StringBuilder();
					mailBody.append("You have a saved Event \n\n");
					mailBody.append("Event Description: " + timeData.getEvent_description() + "\n");
					mailBody.append("On Time: " + localDateFormat.format(timeData.getEvent_time()) + "\n\n");
					mailBody.append("Thanks, \n-Time Assistance \nA Sample Spring Project \n www.timeassistance.com.au");
					
					try {
						sendMail(timeData.getUser().getEmail(), "Reminder: " + timeData.getEvent_name(), mailBody.toString(), timeData.getId());
						taMailService.updateMailSent(timeData.getId());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Something worng!! can't send email");
					}
			}else if((currentTime.compareTo(mailingTime))<0){
				System.out.print(">> Still have time to send mail \n" );
			}
		}
		if(allTimeData.size()>0) System.out.println("=============================================================");
	
	
	}
	
	
}
