package com.ashar.MyClassroom.service.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ashar.MyClassroom.entity.Student;

public interface StudentService {

	List<Student> AllStudents(Map<String, String> obj)  throws SQLException;

	boolean IsStudentOfaClass(Map<String, String> obj) throws SQLException;

	List<Student> GetStudentData(Map<String, String> obj)  throws SQLException;


}
