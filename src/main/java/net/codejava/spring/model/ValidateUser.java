package net.codejava.spring.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;


public class ValidateUser {
	//@NotNull
	//private int id;
	@NotNull
	private String username;
	@NotNull
	private String password;


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

	//public String getEmail() {
	//	return email;
	//}

	//public void setEmail(String email) {
	//	this.email = email;
	//}

}
