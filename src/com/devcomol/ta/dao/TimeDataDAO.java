package com.devcomol.ta.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("timeDataDao")
public class TimeDataDAO {

	@Autowired
	@Qualifier(value="dbSessionFactory")
	SessionFactory sessionFactory;
	
	private Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	/*
	 * Get full table of timedata
	 */
	@SuppressWarnings("unchecked")
	public List<TimeData> getAllTimeData(){
		return session().createQuery("from TimeData").list();
	}

	/*
	 * Get timedata using id
	 */
	public TimeData getTimeData(int id){
		Criteria crit = session().createCriteria(TimeData.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.enabled", true));
		crit.add(Restrictions.idEq(id));
		return (TimeData)crit.uniqueResult();
	}
	
	/*
	 * Get timedata using username
	 */
	@SuppressWarnings("unchecked")
	public List<TimeData> getTimeData(String username){
		Criteria crit = session().createCriteria(TimeData.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("user.username", username));
		crit.add(Restrictions.eq("u.enabled", true));
		return crit.list();
	}
	
	/*
	 * Save or Update TimeData
	 */
	public void saveOrUpdateTimeData(TimeData timeData) {
		System.out.println("TimeDataDAO:" + timeData);
		session().saveOrUpdate(timeData);
	}
	
	/*
	 * Delete TimeData using id
	 */
	public boolean DeleteTimeData(int id){
		Query query = session().createQuery("delete from TimeData where id=:id");
		query.setLong("id", id);
		return query.executeUpdate()==1;
	}
	
	
	/*
	 * Delete TimeData using User
	 */
	public boolean DeleteTimeData(User user){
		Query query = session().createQuery("delete from TimeData where user=:user");
		query.setParameter("user", user);
		return query.executeUpdate()==1;
	}

	public boolean DeleteTimeData(TimeData timeData) {
		int id = timeData.getId();
		Query query = session().createQuery("delete from TimeData where id=:id");
		query.setLong("id", id);
		return query.executeUpdate()==1;
	}
	
	
	
}
