package com.ashar.MyClassroom.entity;

public class User {
	
	private String username;
	private String f_name;
	private String l_name;
	private String email;
	private String password;

	private String phone_no;
	private String  gender;
	private int age;

	public User() {
		
	}
	
	public User(String username, String f_name, String l_name, String email, String phone_no, String  gender, int age) {
		super();
		this.username = username;
		this.f_name = f_name;
		this.l_name = l_name;
		this.email = email;
		this.phone_no = phone_no;
		this.gender = gender;
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", f_name=" + f_name + ", l_name=" + l_name + ", email=" + email
				+ ", phone_no=" + phone_no + ", gender=" + gender + ", age=" + age + "]";
	}

	@SuppressWarnings("unused")
	private String getPassword() {
		return password;
	}

	@SuppressWarnings("unused")
	private void setPassword(String password) {
		this.password = password;
	}
	
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String  getGender() {
		return gender;
	}
	public void setGender(String  gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
