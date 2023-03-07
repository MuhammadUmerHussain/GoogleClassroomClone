package com.ashar.MyClassroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ashar.MyClassroom.entity.Assignment;
public class AssignmentRowMapper implements RowMapper<Assignment>{
	
	@Override
	public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Assignment assignment = new Assignment();
		
		assignment.setA_id(rs.getInt("a_id"));
		assignment.setClass_id(rs.getInt("class_id"));
		assignment.setA_title(rs.getString("a_title"));
		assignment.setTotal_marks(rs.getInt("total_marks"));
        assignment.setDate_created(rs.getString("Date_created"));
        assignment.setDue_date(rs.getString("due_date"));
        assignment.setDescript(rs.getString("descript"));
		        
		return assignment;
	}

	
}

















//
//package com.ashar.MyClassroom.mapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import com.ashar.MyClassroom.entity.Assignment;
//import com.ashar.MyClassroom.entity.Post;
//
//public class AttachmentRowMapper implements RowMapper<Assignment>{
//	
//	@Override
//	public Assignment mapRow(ResultSet rs, int rowNum) throws SQLException {
//		
//		Assignment assignment = new Assignment();
//		
//		assignment.setA_id(rs.getInt("a_id"));
//		assignment.setClass_id(rs.getInt("class_id"));
//		assignment.setA_title(rs.getString("a_title"));
//		assignment.setTotal_marks(rs.getInt("total_marks"));
//        assignment.setDate_created(rs.getString("Date_created"));
//        assignment.setDue_date(rs.getString("due_date"));
//        assignment.setDescript(rs.getString("descript"));
//		        
//		return assignment;
//	}
//
//	
//}
