package com.ashar.MyClassroom.service.Grade;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ashar.MyClassroom.entity.Grade;

public interface GradeService {
	
	List<Grade> ViewGrade(Map<String, String> obj) throws SQLException;
	boolean AssignGrade(Map<String, String> obj) throws SQLException;

}
