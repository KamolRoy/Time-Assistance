package com.devcomol.ta.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Repository;

import com.devcomol.ta.dao.interfaces.FValidationGroup;
import com.devcomol.ta.dao.interfaces.HValidationGroup;

@Repository
@Entity
@Table(name="users")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotBlank(groups={FValidationGroup.class, HValidationGroup.class})
	@Size(min=5, max=55, groups={FValidationGroup.class, HValidationGroup.class})
	@Pattern(regexp="^\\w{5,}$", message="Username can only contain letters, numbers or underscore character", groups={FValidationGroup.class, HValidationGroup.class})
	@Id
	@Column(name="username")
	String username;
	
//	@NotBlank(groups={FValidationGroup.class, HValidationGroup.class})
	@Size(min=5, max=15, groups={FValidationGroup.class})
	@Pattern(regexp="^\\S+$", groups={FValidationGroup.class, HValidationGroup.class})
	String password;
	
	@Size(max=100, groups={FValidationGroup.class, HValidationGroup.class})
	String fullname;
	
	boolean enabled=false;
	
	@NotBlank(groups={FValidationGroup.class, HValidationGroup.class})
	@Email(groups={FValidationGroup.class, HValidationGroup.class})
	String email;
	
	String authority;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password + ", fullname=" + fullname + ", enabled="
				+ enabled + ", email=" + email + ", authority=" + authority + "]";
	}

}
