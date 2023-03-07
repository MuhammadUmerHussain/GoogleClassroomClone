package com.ashar.MyClassroom.entity;

public class Assignment {

	private int a_id;
	private String username;
	private int class_id;
	private String a_title;
	private int total_marks;
	private String Date_created;
	private String due_date;
	private String descript;
	private String External_File;

	public Assignment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Assignment(int a_id, String username, int class_id, String a_title, int total_marks, String date_created,
			String due_date, String descript) {
		super();
		this.a_id = a_id;
		this.username = username;
		this.class_id = class_id;
		this.a_title = a_title;
		this.total_marks = total_marks;
		Date_created = date_created;
		this.due_date = due_date;
		this.descript = descript;
	}

	@Override
	public String toString() {
		return "Assignment [a_id=" + a_id + ", username=" + username + ", class_id=" + class_id + ", a_title=" + a_title
				+ ", total_marks=" + total_marks + ", Date_created=" + Date_created + ", due_date=" + due_date
				+ ", descript=" + descript + "]";
	}

	public int getA_id() {
		return a_id;
	}

	public void setA_id(int a_id) {
		this.a_id = a_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getA_title() {
		return a_title;
	}

	public void setA_title(String a_title) {
		this.a_title = a_title;
	}

	public int getTotal_marks() {
		return total_marks;
	}

	public void setTotal_marks(int total_marks) {
		this.total_marks = total_marks;
	}

	public String getDate_created() {
		return Date_created;
	}

	public void setDate_created(String date_created) {
		Date_created = date_created;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getExternal_File() {
		return External_File;
	}

	public void setExternal_File(String external_File) {
		External_File = external_File;
	}

}
