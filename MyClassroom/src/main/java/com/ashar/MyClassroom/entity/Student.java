package com.ashar.MyClassroom.entity;

public class Student extends User {

	private String Department;
	private String DateJoined;

	public Student() {
		super();
	}

	public Student(String f_name, String l_name, String email, String password, String phone_no, String gender, int age) {
		super(f_name, l_name, email, password, phone_no, gender, age);
		// TODO Auto-generated constructor stub
	}

	public Student(String department, String dateJoined) {
		super();
		Department = department;
		DateJoined = dateJoined;
	}

	@Override
	public String toString() {
		return super.toString() + "Student [Department=" + Department + ", DateJoined=" + DateJoined + "]";
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getDateJoined() {
		return DateJoined;
	}

	public void setDateJoined(String dateJoined) {
		DateJoined = dateJoined;
	}
	

}
