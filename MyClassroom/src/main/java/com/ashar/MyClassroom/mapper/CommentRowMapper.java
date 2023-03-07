package com.ashar.MyClassroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ashar.MyClassroom.entity.Comment;
import com.ashar.MyClassroom.entity.Post;

public class CommentRowMapper implements RowMapper<Comment>{
	
	
	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Comment Comment = new Comment();
		
//		comment_id, post_id, comment_time, comment_text, comment_by_std_username, comment_by_teacher_username

		Comment.setComment_id(rs.getInt("comment_id"));
		Comment.setPost_id(rs.getInt("post_id"));
		Comment.setComment_text(rs.getString("comment_text"));
		Comment.setComment_time(rs.getString("comment_time"));
		
		Comment.setComment_by_std_username(rs.getString("comment_by_std_username"));
		Comment.setComment_by_teacher_username(rs.getString("comment_by_teacher_username"));

		        
		return Comment;
	}


}
