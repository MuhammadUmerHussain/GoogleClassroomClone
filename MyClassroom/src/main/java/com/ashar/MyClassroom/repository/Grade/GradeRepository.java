package com.ashar.MyClassroom.repository.Grade;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ashar.MyClassroom.entity.Assignment;
import com.ashar.MyClassroom.entity.Grade;
import com.ashar.MyClassroom.mapper.AssignmentRowMapper;
import com.ashar.MyClassroom.mapper.GradeRowMapper;


@Repository
public class GradeRepository {

	@Autowired
	private DataSource dataSource;
	CallableStatement callableStatement;
	
	
	@SuppressWarnings("deprecation")
	public List<Grade> ViewGrade(String assignment_id, String stdUsername) throws SQLException {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Grade> grade = jdbcTemplate.query("call ViewGrade(?, ?);", new Object[] {assignment_id,stdUsername},new GradeRowMapper());	
		return grade;
		
	}


	public boolean ViewGrade(String assignment_id, String stdUsername, String teacherUsername, String MarksObt) throws SQLException {
        
		boolean result = false;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call AssignGrade(?, ?, ?, ?, ?)}");
		
		callableStatement.setString(1, assignment_id);
		callableStatement.setString(2, stdUsername);
		callableStatement.setString(3, teacherUsername);
		callableStatement.setString(4, MarksObt);
		
		callableStatement.registerOutParameter(5, Types.BOOLEAN);

		callableStatement.executeUpdate();
		result = callableStatement.getBoolean(5);
		callableStatement.getConnection().close();

		return result;
	}
	

}
