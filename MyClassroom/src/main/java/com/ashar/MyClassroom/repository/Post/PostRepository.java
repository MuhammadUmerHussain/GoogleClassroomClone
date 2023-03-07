package com.ashar.MyClassroom.repository.Post;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ashar.MyClassroom.entity.Post;
import com.ashar.MyClassroom.mapper.PostRowMapper;

@Repository
public class PostRepository {

	@Autowired
	private DataSource dataSource;
	CallableStatement callableStatement;

	public boolean createPost(String username, String class_id, String title, String descript) throws SQLException {

		boolean result = false;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection()
				.prepareCall("{call CreatePost(?, ?, ?, ?, ?)}");
		callableStatement.setString(1, username);
		callableStatement.setString(2, class_id);
		callableStatement.setString(3, title);
		callableStatement.setString(4, descript);
		callableStatement.registerOutParameter(5, Types.BOOLEAN);

		callableStatement.executeUpdate();
		result = callableStatement.getBoolean(5);
		callableStatement.getConnection().close();

		return result;
	}

	public boolean editPost(String post_id, String username, String class_id, String title, String descript) throws SQLException {

		boolean result = false;
		System.out.println("EDIT post here!!");

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call EditPost(?, ?, ?, ?, ?, ?)}");
		callableStatement.setString(1, post_id);
		callableStatement.setString(2, username);
		callableStatement.setString(3, class_id);
		callableStatement.setString(4, title);
		callableStatement.setString(5, descript);
		callableStatement.registerOutParameter(6, Types.BOOLEAN);

		callableStatement.executeUpdate();
		result = callableStatement.getBoolean(6);
		callableStatement.getConnection().close();

		return result;
	}

	public boolean deletePost(String post_id, String teacherUsername) throws SQLException {

		boolean result = false;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call DeletePost(?, ?, ?)}");
		callableStatement.setString(1, post_id);
		callableStatement.setString(2, teacherUsername);
		callableStatement.registerOutParameter(3, Types.BOOLEAN);

		callableStatement.executeUpdate();
		result = callableStatement.getBoolean(3);
		callableStatement.getConnection().close();

		return result;
	}

	
	
	
	
	
	
	
	
	@SuppressWarnings("deprecation")
	public List<Post> viewAllPost(String classId) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		System.out.println("post");
		List<Post> posts = jdbcTemplate.query("call viewAllPost(?);", new Object[] {classId},new PostRowMapper());
		
		String Username = getUsername(classId);
		
		for(int i=0; i<posts.size(); i++) {
			posts.get(i).setUsername(Username);
		}
		return posts;
	}

	
	public String getUsername(String classId) throws SQLException {
		System.out.print("in the get username");
	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call GetTeacherUsernameFromClassId(?,?)}");
		callableStatement.setString(1, classId);
		callableStatement.registerOutParameter(2, Types.VARCHAR);


		callableStatement.executeUpdate();
		String result = callableStatement.getString(2);
		callableStatement.getConnection().close();

		return result;
	}

}
