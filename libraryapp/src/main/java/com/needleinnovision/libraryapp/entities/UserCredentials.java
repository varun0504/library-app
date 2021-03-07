package com.needleinnovision.libraryapp.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS_CREDENTIALS")
public class UserCredentials extends BaseEntity{
	
	@OneToOne
	@JoinColumn(name="userId")
    private UserEntity user;
	
	@Column
    private String username;
	
	@Column
    private String password;
	
	@Column
    private Date passwordExpiryDate;
	
	@Column
    private Date lastLoginTime;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

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

	public Date getPasswordExpiryDate() {
		return passwordExpiryDate;
	}

	public void setPasswordExpiryDate(Date passwordExpiryDate) {
		this.passwordExpiryDate = passwordExpiryDate;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
