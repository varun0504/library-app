package com.needleinnovision.libraryapp.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserEntity extends BaseEntity {
	@Id
    private long userId;
	
	@Column
    private String firstName;
	
	@Column
    private String lastName;
	
	@Column
    private String mobileNo;
	
	@Column
    private String emailId;
	
	@OneToOne(mappedBy="user")
	private UserEntity user;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="USER_ROLE_MAPPING", joinColumns = @JoinColumn(name="USER_ID"),
	inverseJoinColumns = @JoinColumn(name="ROLE_ID"))
	private List<Roles> roles;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
}
