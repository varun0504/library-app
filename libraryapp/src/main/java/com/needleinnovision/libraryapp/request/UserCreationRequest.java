package com.needleinnovision.libraryapp.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.needleinnovision.libraryapp.validators.CheckAtLeastOneNotNull;

@CheckAtLeastOneNotNull(fieldNames={"mobileNo","emailId"}, message = "Both mobileNo and emailId cannot be empty")
public class UserCreationRequest {
	
	@NotBlank(message = "First Name cannot be null or empty")
	private String firstName;
	
	private String lastName;
	
	@Pattern(regexp="^[6-9]\\d{9}$", message = "Invalid Mobile number")
	private String mobileNo;
	
	@Email(message = "Invalid Email Id")
	private String emailId;
	
	@NotBlank(message = "Username cannot be null or empty")
	private String username;
	
	@NotBlank(message = "Password cannot be null or empty")
	private String password;
	
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
}
