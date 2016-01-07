package com.devcomol.ta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devcomol.ta.service.UserService;

@Repository
@Transactional
@Component("mailDAO")
public class MailDAO {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private UserService userService;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	/*
	 * Get All time data that didn't proceed for sending mail
	 */

	public List<TimeData> getTimeData() {

		MapSqlParameterSource params = new MapSqlParameterSource();

		return jdbc.query("select * from timedata where mail_sent = false and active_status=true", params, new RowMapper<TimeData>() {

			public TimeData mapRow(ResultSet rs, int rowNum) throws SQLException {
				TimeData timeData = new TimeData();

				timeData.setId(rs.getInt("id"));
				timeData.setUser(userService.getUser(rs.getString("username")));
				timeData.setEvent_name(rs.getString("event_name"));
				timeData.setEvent_description(rs.getString("event_description"));
				timeData.setEvent_time(rs.getTimestamp("event_time"));
				timeData.setDuration(rs.getInt("duration"));
				timeData.setSend_email(rs.getBoolean("send_email"));

				return timeData;
			}

		});
	}

	/*
	 * Update mail sent true. This method will call after sending mail to a user
	 */
	public void updateMailSent(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		jdbc.update("update timedata set mail_sent=true, active_status=false where id=:id", params);
	}

	/*
	 * Update timedata events to active that event time is greater than current
	 * time
	 */
	public void changeActiveStatus() {
		MapSqlParameterSource params = new MapSqlParameterSource();
		jdbc.update(
				"update timedata set active_status = true where event_time >= now() and mail_sent=false and active_status=false",
				params);
		System.out.println("Change Active Status called");
	}

}
