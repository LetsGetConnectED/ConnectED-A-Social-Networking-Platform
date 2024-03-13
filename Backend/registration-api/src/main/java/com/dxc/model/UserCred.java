package com.dxc.model;

public class UserCred {
	public String username;
	public String password;

	public UserCred(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserCred [username=" + username + ", password=" + password + "]";
	}

}
