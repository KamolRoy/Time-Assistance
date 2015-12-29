package com.devcomol.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.devcomol.ta.dao.MailDAO;
import com.devcomol.ta.dao.TimeData;

@Service("taMailService")
public class TAMailService {
	
	@Autowired
	@Qualifier(value="mailDAO")
	MailDAO mailDAO;
	
	
	/*
	 * Get All time data that didn't proceed for sending mail
	 */
	public List<TimeData> getTimeData() {
		return mailDAO.getTimeData();
	}
	
	/*
	 * Update mail sent true. This method will call after sending mail to a user
	 */
	public void updateMailSent(int id) {
		mailDAO.updateMailSent(id);
	}
	
	/*
	 * Update timedata events to active that event time is greater than current
	 * time
	 */
	public void changeActiveStatus(){
		mailDAO.changeActiveStatus();
	}
	
}
