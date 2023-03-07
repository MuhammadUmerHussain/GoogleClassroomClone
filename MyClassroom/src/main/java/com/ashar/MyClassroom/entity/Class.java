package com.ashar.MyClassroom.entity;

public class Class {
	
	private int class_id;
	private String class_name;
	private String course_title;
	private String Course_Code;
	private String Unique_class_code;
	private String description;
	private String Date_created;
	
    public Class() {
		super();
	}

	public Class(int class_id, String class_name, String course_title, String course_Code, String unique, String description) {
		super();
		this.class_id = class_id;
		this.class_name = class_name;
		this.course_title = course_title;
		this.Course_Code = course_Code;
		this.description = description;
		this.Unique_class_code = unique;
	}
	
	
	@Override
	public String toString() {
		return "Class [class_id=" + class_id + ", class_name=" + class_name + ", course_title=" + course_title
				+ ", Course_Code=" + Course_Code + ", description=" + description + "]";
	}


	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getCourse_title() {
		return course_title;
	}
	public void setCourse_title(String course_title) {
		this.course_title = course_title;
	}
	public String getCourse_Code() {
		return Course_Code;
	}
	public void setCourse_Code(String course_Code) {
		Course_Code = course_Code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnique_class_code() {
		return Unique_class_code;
	}

	public void setUnique_class_code(String unique_class_code) {
		Unique_class_code = unique_class_code;
	}

	public String getDate_created() {
		return Date_created;
	}

	public void setDate_created(String date_created) {
		Date_created = date_created;
	}
	

}
