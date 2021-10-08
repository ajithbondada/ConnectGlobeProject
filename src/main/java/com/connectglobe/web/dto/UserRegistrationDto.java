package com.connectglobe.web.dto;

import java.util.*;
public class UserRegistrationDto {
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	
	private String password;
	
	private Date regdate;
	
	
	
	public UserRegistrationDto(){
		
	}



	public UserRegistrationDto(String firstName, String lastName, String email, String username, String password,
			Date regdate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.regdate = regdate;
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
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



	public Date getRegdate() {
		return regdate;
	}



	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
