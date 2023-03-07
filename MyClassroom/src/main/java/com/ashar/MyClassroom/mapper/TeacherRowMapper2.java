package com.ashar.MyClassroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.ashar.MyClassroom.entity.Teacher;


public class TeacherRowMapper2 implements RowMapper<Teacher>{
	
	@Override
	public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Teacher Teacher = new Teacher();
		
		Teacher.setUsername(rs.getString("username"));
		Teacher.setF_name(rs.getString("f_name"));
		Teacher.setL_name(rs.getString("l_name"));
		Teacher.setEmail(rs.getString("email"));
		Teacher.setPhone_no(rs.getString("phone_no"));
		Teacher.setGender(rs.getString("gender"));
		Teacher.setAge(rs.getInt("age"));

		return Teacher;
	}
	
}
