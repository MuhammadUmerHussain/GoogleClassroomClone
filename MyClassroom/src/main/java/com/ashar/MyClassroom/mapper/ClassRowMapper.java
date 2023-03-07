package com.ashar.MyClassroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ashar.MyClassroom.entity.Class;

public class ClassRowMapper implements RowMapper<Class>{
	

//	class_id, username, class_name, course_title, Course_Code, Unique_Class_code, Date_created, descript
	
	@Override
	public Class mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Class classobj = new Class();
		
          classobj.setClass_id(rs.getInt("class_id"));
          classobj.setClass_name(rs.getString("class_name"));
          classobj.setCourse_title(rs.getString("course_title"));
          classobj.setDescription(rs.getString("descript"));
          classobj.setUnique_class_code(rs.getString("Unique_Class_code"));
          classobj.setCourse_Code(rs.getString("Course_Code"));
          classobj.setDate_created(rs.getString("Date_created"));

		        
		return classobj;
	}


}
