package com.ashar.MyClassroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ashar.MyClassroom.entity.Post;

public class PostRowMapper implements RowMapper<Post>{
	
	
	@Override
	public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Post post = new Post();
		
        post.setClass_id(rs.getInt("class_id"));
        post.setDate_created(rs.getString("datecreated"));
        post.setDescript(rs.getString("descript"));
        post.setTitle(rs.getString("title"));
        post.setPost_id(rs.getInt("post_id"));
		        
		return post;
	}


}
