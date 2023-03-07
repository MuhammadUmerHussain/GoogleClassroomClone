package com.ashar.MyClassroom.service.Assignment;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ashar.MyClassroom.entity.Assignment;

public interface AssignmentService {

	boolean CreateAssignment(Map<String, String> obj) throws SQLException;
	boolean editAssignment(Map<String, String> obj) throws SQLException;
	boolean deleteAssignment(Map<String, String> obj) throws SQLException;
	List<Assignment> ViewAllAssignment(Map<String, String> obj) throws SQLException;

}
