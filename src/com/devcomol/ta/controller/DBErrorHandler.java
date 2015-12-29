package com.devcomol.ta.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DBErrorHandler {
	
	@ExceptionHandler(DataAccessException.class)
	public String handleDBException(DataAccessException ex){
		System.out.println(ex.getMessage());
		return "dberror";
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public String handleAccessException(AccessDeniedException ex) {
		return "denied";
	}
}
