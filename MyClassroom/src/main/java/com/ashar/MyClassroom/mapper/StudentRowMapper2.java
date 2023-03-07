package com.ashar.MyClassroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.ashar.MyClassroom.entity.Student;


public class StudentRowMapper2 implements RowMapper<Student>{
	
	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Student Student = new Student();
		
		Student.setUsername(rs.getString("username"));
		Student.setF_name(rs.getString("f_name"));
		Student.setL_name(rs.getString("l_name"));
		Student.setEmail(rs.getString("email"));
		Student.setPhone_no(rs.getString("phone_no"));
		Student.setGender(rs.getString("gender"));
		Student.setAge(rs.getInt("age"));

		return Student;
	}
	
}
