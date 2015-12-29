package com.devcomol.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devcomol.ta.service.TimeDataService;

@Controller
public class TimeDataController {
	
	@Autowired
	@Qualifier(value="timeDataService")
	TimeDataService timeDataService;
	
	@RequestMapping("/tadata")
	public String showTimeData(){
		
		
		
		return "tadata";
	}
}
