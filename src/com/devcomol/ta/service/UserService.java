package com.devcomol.ta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.devcomol.ta.dao.User;
import com.devcomol.ta.dao.UserDAO;

@Service("userService")
public class UserService {

	@Autowired
	@Qualifier("userDao")
	private UserDAO userDAO;
	
	/*
	 * Get all user from user table
	 */
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	/*
	 * Create a user
	 */
	public void createUser(User user) {
		userDAO.createUser(user);
	}
	
	/*
	 * Update Profile
	 */
	public void updateProfile(User user){
		userDAO.updateProfile(user);
	}
	
	/*
	 * Update Password
	 */
	public void updatePassword(User user){
		userDAO.updatePassword(user);
	}

	/*
	 * Check a user exist or not
	 */
	public boolean exists(String username) {
		
		return userDAO.exists(username);
	}
	
	/*
	 * Get a user using username
	 */
	public User getUser(String username){
		return userDAO.getUser(username);
	}
	/*
	 * Check old Password
	 */
	public boolean checkOldPwd(String username, String password){
		return userDAO.checkOldPwd(username, password);
	}
	
	/*
	 * Update User Password
	 */
	@Secured("ROLE_ADMIN")
	public void updateUserPassword(String username, String password){
		userDAO.updateUserPassword(username, password);
	}
	
	/*
	 * Delete User
	 */
	@Secured("ROLE_ADMIN")
	public void deleteUser(String username){
		if(userDAO.deleteUser(username)){
			System.out.println(username + " deleted successfully");
		}else{
			System.out.println("Cann't delete " + username);
		}
	}
	/*
	 * Delete User's time data
	 */
	@Secured("ROLE_ADMIN")
	public void deleteUserTimeData(String username){
		if(userDAO.deleteUserTimeData(username)){
			System.out.println("TimeData of user " + username + " successfully deleted");
		}else{
			System.out.println("No TimeData found for user " + username );
		}
	}
}
