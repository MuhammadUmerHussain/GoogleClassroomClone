package com.ashar.MyClassroom.service.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ashar.MyClassroom.entity.Teacher;

public interface TeacherService {

	String GetTeacherUsernameFromClassId(Map<String, String> obj) throws SQLException;

	boolean RemoveStudentFromClass(Map<String, String> obj) throws SQLException;

	boolean IsTeacherOfaClass(Map<String, String> obj) throws SQLException;

	List<Teacher> GetTeacherData(Map<String, String> obj);


}
