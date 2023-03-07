package com.ashar.MyClassroom.repository.Comment;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ashar.MyClassroom.entity.Comment;
import com.ashar.MyClassroom.entity.Comment;
import com.ashar.MyClassroom.entity.Post;
import com.ashar.MyClassroom.mapper.CommentRowMapper;
import com.ashar.MyClassroom.mapper.PostRowMapper;


@Repository
public class CommentRepository {

	@Autowired
	private DataSource dataSource;
	CallableStatement callableStatement;
	


	public boolean CommentOnPost(String post_id, String by_student, String by_Teacher, String comment_text) throws SQLException {
	    
		boolean result = false;
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call CommentOnPost(?, ?, ?, ?, ?)}");
		
		callableStatement.setString(1, post_id);
		callableStatement.setString(2, by_student);
		callableStatement.setString(3, by_Teacher);
		callableStatement.setString(4, comment_text);
		callableStatement.registerOutParameter(5, Types.BOOLEAN);

		callableStatement.executeUpdate();
		result = callableStatement.getBoolean(5);
		callableStatement.getConnection().close();

		return result;
	}



	public List<Comment> AllCommentsOnPost(String post_id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println("AllCommentsOnPost");
		
		List<Comment> comments = jdbcTemplate.query("call AllCommentsOnPost(?);", new Object[] {post_id},new CommentRowMapper());
		
//		String Username = getUsername(class_id);
//		
//		for(int i=0; i<comments.size(); i++) {
//			comments.get(i).setUsername(Username);
//		}
		
		return comments;
	}
	
}
