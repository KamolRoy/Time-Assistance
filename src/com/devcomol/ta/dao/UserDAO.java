package com.devcomol.ta.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("userDao")
public class UserDAO {

	@Autowired
	@Qualifier(value = "passEncoder")
	private PasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier(value = "dbSessionFactory")
	SessionFactory sessionFactory;

	public UserDAO() {
	}

	private Session session() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * Get All User from User Table
	 */
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {

		return session().createQuery("from User").list();
	}

	/*
	 * Create a User
	 */
	public void createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
		System.out.println(user);
	}

	/*
	 * Update Profile
	 */
	public void updateProfile(User user) {
		String hqlUpdate = "update User set fullname=:fullname, email=:email where username=:username";
		session().createQuery(hqlUpdate).setString("fullname", user.getFullname())
				.setString("email", user.getEmail()).setString("username", user.getUsername()).executeUpdate();
		System.out.println(user);
	}

	/*
	 * Update Password
	 */
	public void updatePassword(User user) {
		String hqlUpdate = "update User set password=:password where username=:username";
		session().createQuery(hqlUpdate)
				.setString("password", passwordEncoder.encode(user.getPassword())).setString("username", user.getUsername()).executeUpdate();
		System.out.println(user);
	}
	
	/*
	 * Update User Password
	 */
	public void updateUserPassword(String username, String password) {
		String hqlUpdate = "update User set password=:password where username=:username";
		session().createQuery(hqlUpdate)
				.setString("password", passwordEncoder.encode(password)).setString("username", username).executeUpdate();
		System.out.println("Password change successfull for user " + username);
	}
	
	

	/*
	 * Check a user exists or not
	 */
	public boolean exists(String username) {
		return getUser(username) != null;
	}

	/*
	 * Get a user using username
	 */
	public User getUser(String username) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));
		User user = (User) crit.uniqueResult();
		return user;
	}

	/*
	 * Check Old password
	 */
	public boolean checkOldPwd(String username, String password) {
		Criteria crit = session().createCriteria(User.class);
		crit.add(Restrictions.idEq(username));
		User user = (User) crit.uniqueResult();
		return (passwordEncoder.matches(password, user.getPassword()));
	}
	
	/*
	 * Delete a User
	 */
	public boolean deleteUser(String username){
		String hqlUpdate = "delete from User where username=:username";
		return session().createQuery(hqlUpdate).setString("username", username).executeUpdate() ==1;
	}
	/*
	 * Delete user TimeData
	 */
	public boolean deleteUserTimeData(String username){
		String hqlUpdate = "delete from TimeData where username=:username";
		return session().createQuery(hqlUpdate).setString("username", username).executeUpdate() > 0;
		
	}
}
