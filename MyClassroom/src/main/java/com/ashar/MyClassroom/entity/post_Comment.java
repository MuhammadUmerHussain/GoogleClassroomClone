package com.ashar.MyClassroom.entity;

public class post_Comment {
	private int comment_id;

	private int post_id;
	private String teacher_username;
	private int class_id;

	private String comment_time;
	private String comment_text;
	private String comment_by_std_username;
	private String comment_by_teacher_username;
	
	public post_Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public post_Comment(int comment_id, int post_id, String teacher_username, int class_id, String comment_time,
			String comment_text, String comment_by_std_username, String comment_by_teacher_username) {
		super();
		this.comment_id = comment_id;
		this.post_id = post_id;
		this.teacher_username = teacher_username;
		this.class_id = class_id;
		this.comment_time = comment_time;
		this.comment_text = comment_text;
		this.comment_by_std_username = comment_by_std_username;
		this.comment_by_teacher_username = comment_by_teacher_username;
	}


	@Override
	public String toString() {
		return "post_Comment [comment_id=" + comment_id + ", post_id=" + post_id + ", teacher_username="
				+ teacher_username + ", class_id=" + class_id + ", comment_time=" + comment_time + ", comment_text="
				+ comment_text + ", comment_by_std_username=" + comment_by_std_username
				+ ", comment_by_teacher_username=" + comment_by_teacher_username + "]";
	}


	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getTeacher_username() {
		return teacher_username;
	}

	public void setTeacher_username(String teacher_username) {
		this.teacher_username = teacher_username;
	}

	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	public String getComment_by_std_username() {
		return comment_by_std_username;
	}

	public void setComment_by_std_username(String comment_by_std_username) {
		this.comment_by_std_username = comment_by_std_username;
	}

	public String getComment_by_teacher_username() {
		return comment_by_teacher_username;
	}

	public void setComment_by_teacher_username(String comment_by_teacher_username) {
		this.comment_by_teacher_username = comment_by_teacher_username;
	}

}
