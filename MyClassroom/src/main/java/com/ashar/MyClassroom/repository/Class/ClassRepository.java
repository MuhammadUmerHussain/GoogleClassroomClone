package com.ashar.MyClassroom.repository.Class;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ashar.MyClassroom.entity.Assignment;
import com.ashar.MyClassroom.entity.Class;
import com.ashar.MyClassroom.entity.Post;
import com.ashar.MyClassroom.mapper.AssignmentRowMapper;
import com.ashar.MyClassroom.mapper.ClassRowMapper;
import com.ashar.MyClassroom.mapper.PostRowMapper;


@Repository
public class ClassRepository {

	@Autowired
	private DataSource dataSource;
	CallableStatement callableStatement;
	
	
	public boolean CreateClassroom(String TeacherUsername, String name, String title, String code, String unique_code,
			String descript) throws SQLException {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call CreateClassroom(?, ?, ?, ?, ?, ?, ?)}");
		
		callableStatement.setString(1, TeacherUsername);
		callableStatement.setString(2, name);
		callableStatement.setString(3, title);
		callableStatement.setString(4, code);
		callableStatement.setString(5, unique_code);
		callableStatement.setString(6, descript);
		callableStatement.registerOutParameter(7, Types.BOOLEAN);
		
		callableStatement.executeUpdate();

		boolean result = callableStatement.getBoolean(7);
		callableStatement.getConnection().close();

		return result;
	}


	public boolean EditClassroom(String class_id, String TeacherUsername, String name, String title, String code,
			String descript) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call EditClassroom(?, ?, ?, ?, ?, ?, ?)}");
		
		callableStatement.setString(1, class_id);
		callableStatement.setString(2, TeacherUsername);
		callableStatement.setString(3, name);
		callableStatement.setString(4, title);
		callableStatement.setString(5, code);
		callableStatement.setString(6, descript);
		callableStatement.registerOutParameter(7, Types.BOOLEAN);
		
		callableStatement.executeUpdate();

		boolean result = callableStatement.getBoolean(7);
		callableStatement.getConnection().close();

		return result;
	}


	public boolean DeleteClassroom(String class_id, String TeacherUsername) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call DeleteClassroom(?, ?, ?)}");
		
		callableStatement.setString(1, class_id);
		callableStatement.setString(2, TeacherUsername);
		callableStatement.registerOutParameter(3, Types.BOOLEAN);
		
		callableStatement.executeUpdate();

		boolean result = callableStatement.getBoolean(3);
		callableStatement.getConnection().close();

		return result;
	}


	public boolean JoinClassroom(String stdUsername, String unique_class_code) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call JoinClassroom(?, ?, ?)}");
		
		callableStatement.setString(1, stdUsername);
		callableStatement.setString(2, unique_class_code);
		callableStatement.registerOutParameter(3, Types.BOOLEAN);
		
		callableStatement.executeUpdate();

		boolean result = callableStatement.getBoolean(3);
		callableStatement.getConnection().close();

		return result;
	}


	public String GetUniqueCodeOfClass(String class_id) throws SQLException {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call GetUniqueCodeOfClass(?, ?)}");
		
		callableStatement.setString(1, class_id);
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		
		callableStatement.executeUpdate();

	     String result = callableStatement.getString(2);
		callableStatement.getConnection().close();

		return result;
	}

	
	@SuppressWarnings("deprecation")
	public List<Class> AllClassroomsOfTeacher(String username) {	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
	
		List<Class> Classrooms = jdbcTemplate.query("call AllClassroomsOfTeacher(?);", new Object[] {username},new ClassRowMapper());

		
		return Classrooms;
	}


	@SuppressWarnings("deprecation")
	public List<Class> AllClassroomsOfStudent(String username) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<Class> Classrooms = jdbcTemplate.query("call AllClassroomsOfStudent(?);", new Object[] {username},new ClassRowMapper());
		return Classrooms;
	}


	@SuppressWarnings("deprecation")
	public List<Class> GetClassData(String class_id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Class> ClassroomData = jdbcTemplate.query("call GetClassData(?);", new Object[] {class_id},new ClassRowMapper());
		return ClassroomData;
	}
	
}
