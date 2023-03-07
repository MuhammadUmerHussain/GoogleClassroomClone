package com.ashar.MyClassroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ashar.MyClassroom.entity.Assignment;
import com.ashar.MyClassroom.entity.Grade;
import com.ashar.MyClassroom.entity.Post;

public class GradeRowMapper implements RowMapper<Grade>{
	
	@Override
	public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Grade grade = new Grade();
		
		   grade.setStd_username(rs.getString("std_username"));
           grade.setAssign_id(rs.getInt("assign_id"));
           grade.setMarks_obtained(rs.getFloat("marks_obtained"));
		        
		return grade;
	}

	
}
