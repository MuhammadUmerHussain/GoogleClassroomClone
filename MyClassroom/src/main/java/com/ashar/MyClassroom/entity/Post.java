package com.ashar.MyClassroom.entity;

public class Post {

	private int post_id ;   
	private String username;
	private int class_id ;
	private String title;
	private String descript;
	private String date_created;
	
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int post_id, String username, int class_id, String title, String descript) {
		super();
		this.post_id = post_id;
		this.username = username;
		this.class_id = class_id;
		this.title = title;
		this.descript = descript;
	}

	@Override
	public String toString() {
		return "Post [post_id=" + post_id + ", username=" + username + ", class_id=" + class_id + ", title=" + title
				+ ", descript=" + descript + "]";
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
	
	
}
