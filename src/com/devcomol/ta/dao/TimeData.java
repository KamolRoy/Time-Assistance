package com.devcomol.ta.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.devcomol.ta.dao.interfaces.FValidationGroup;
import com.devcomol.ta.dao.interfaces.HValidationGroup;

@Repository
@Entity
@Table(name = "timedata")
public class TimeData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	@NotBlank(groups={FValidationGroup.class, HValidationGroup.class})
	@Size(min=5, max=95,groups={FValidationGroup.class, HValidationGroup.class})
	private String event_name;
	
	@NotBlank(groups={FValidationGroup.class, HValidationGroup.class})
	@Size(min=5, max=295,groups={FValidationGroup.class, HValidationGroup.class})
	private String event_description;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.NONE)
	private Date event_time;
	
	private int duration;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.NONE)
	private Date saved_time;
	
	private boolean send_email;
	private boolean add_gcal;
	private boolean active_status;
	
	

	public TimeData() {
		this.user = new User();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return user.getUsername();
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_description() {
		return event_description;
	}

	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}

	public Date getEvent_time() {
		return event_time;
	}

	public void setEvent_time(Date event_time) {
		this.event_time = event_time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getSaved_time() {
		return saved_time;
	}

	public void setSaved_time(Date saved_time) {
		this.saved_time = saved_time;
	}

	public boolean isSend_email() {
		return send_email;
	}

	public void setSend_email(boolean send_email) {
		this.send_email = send_email;
	}

	public boolean isAdd_gcal() {
		return add_gcal;
	}

	public void setAdd_gcal(boolean add_gcal) {
		this.add_gcal = add_gcal;
	}

	public boolean isActive_status() {
		return active_status;
	}

	public void setActive_status(boolean active_status) {
		this.active_status = active_status;
	}

	@Override
	public String toString() {
		return "TimeData [id=" + id + ", user=" + user + ", event_name=" + event_name + ", event_description="
				+ event_description + ", event_time=" + event_time + ", duration=" + duration + ", saved_time="
				+ saved_time + ", send_email=" + send_email + ", add_gcal=" + add_gcal + ", active_status="
				+ active_status + "]";
	}

	

}
