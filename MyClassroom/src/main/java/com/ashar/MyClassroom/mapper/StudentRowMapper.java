package com.ashar.MyClassroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.ashar.MyClassroom.entity.Student;


public class StudentRowMapper implements RowMapper<Student>{
	
	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Student Student = new Student();
		
		Student.setUsername(rs.getString("username"));
//		Student.setClass_id(rs.getInt("class_id"));
		Student.setDepartment(rs.getString("Department"));
		Student.setDateJoined(rs.getString("Date_joined"));

		return Student;
	}

//	username, class_id, Department, Date_joined
}
