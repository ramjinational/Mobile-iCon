package com.iCon.iCon;

public class User {

	private String user_id;
	private String password;
	private String auth_number;

	public User(String user_id, String password, String auth_number) {
		this.user_id=user_id;
		this.password=password;
		this.auth_number=auth_number;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuth_number() {
		return auth_number;
	}

	public void setAuth_number(String auth_number) {
		this.auth_number = auth_number;
	}

	@Override
	public boolean equals(Object o) {
		/*User oldUser = (User) o;
		if (this.user_id != null && this.user_id.equals(oldUser.getUser_id()) && this.user_id != null
				&& this.user_id.equals(oldUser.getUser_id()) && this.user_id != null
				&& this.user_id.equals(oldUser.getUser_id())) {
			return true;
		} else {
			return false;
		}*/
		return super.equals(o);
	}

}
