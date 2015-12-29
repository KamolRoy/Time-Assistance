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
	
	
	
	public void sendMail(String receiverAddress, String subject, String text){
		
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("comolroy@gmail.com");
		mail.setTo(receiverAddress);
		mail.setSubject(subject);
		mail.setText(text);
		
		try {
			mailSender.send(mail);
			System.out.println("Mail Send Successfull");
		} catch (MailException e) {
			System.out.println("Something worng!!! Can't send mail");
			e.printStackTrace();
			
		}
		
	}
	
	
	@Scheduled(cron = "0 0/5 * * * ?")
	public void changeActiveStatus() {
		taMailService.changeActiveStatus();
	}
	
	@Scheduled(cron = "0 0/5 * * * ?")
	public void sendingMail() {
		final long ONE_MINUTE_IN_MILLIS = 60000;
		List<TimeData> allTimeData=taMailService.getTimeData();
		
		for(TimeData timeData: allTimeData){
			SimpleDateFormat localDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
			
			
			Long event_Time= timeData.getEvent_time().getTime();
			int duration = timeData.getDuration();
			Date mailingTime = new Date(event_Time - (duration*ONE_MINUTE_IN_MILLIS));
			
			
			System.out.println(timeData.getEvent_name());
			
			
			Date currentTime = new Date();
			if((currentTime.compareTo(mailingTime))>0){
				System.out.println(localDateFormat.format(timeData.getEvent_time()));
				System.out.println(mailingTime);
				System.out.println("Time to Send Mail");
				try {
					StringBuilder mailBody=new StringBuilder();
					mailBody.append("You have a saved Event \n\n");
					mailBody.append("Event Description: " + timeData.getEvent_description() + "\n");
					mailBody.append("On Time: " + localDateFormat.format(timeData.getEvent_time()) + "\n\n");
					mailBody.append("Thanks, \n-Time Assistance \nA Sample Spring Project");
					
					sendMail(timeData.getUser().getEmail(), "Reminder: " + timeData.getEvent_name(), mailBody.toString());
					taMailService.updateMailSent(timeData.getId());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Something worng!! can't send email");
				}
			}else if((currentTime.compareTo(mailingTime))<0){
				System.out.println("Still have time to send mail");
			}
			
			System.out.println("");
		}
		
		System.out.println("=========================================================");
	}
	
	
}
