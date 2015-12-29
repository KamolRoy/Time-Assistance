package com.devcomol.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.devcomol.ta.dao.TimeData;
import com.devcomol.ta.dao.TimeDataDAO;
import com.devcomol.ta.dao.User;

@Service("timeDataService")
public class TimeDataService {
	
	@Autowired
	@Qualifier(value="timeDataDao")
	TimeDataDAO timeDataDao;
	
	/*
	 * Get full timedata table
	 */
	public List<TimeData> getAllTimeData(){
		return timeDataDao.getAllTimeData();
	}
	
	/*
	 * Get TImeData using id 
	 */
	public TimeData getTimeData(int id){
		return timeDataDao.getTimeData(id);
	}
	
	/*
	 * Get TImeData using username
	 */
	public List<TimeData> getTimeData(String username){
		return timeDataDao.getTimeData(username);
	}
	
	/*
	 * Save or Update TimeData 
	 */
	public void saveOrUpdateTimeData(TimeData timeData) {
		timeDataDao.saveOrUpdateTimeData(timeData);
	}
	
	/*
	 * Delete TimeData using id
	 */
	public boolean deleteTimeData(int id){
		return timeDataDao.DeleteTimeData(id);
	}
	
	/*
	 * Delete TimeData using User
	 */
	public boolean deleteTimeData(User user){
		return timeDataDao.DeleteTimeData(user);
	}
	
	/*
	 * Delete TimeData using timeData
	 */
	public boolean deleteTimeData(TimeData timeData) {
		return timeDataDao.DeleteTimeData(timeData);
	}
	
}
