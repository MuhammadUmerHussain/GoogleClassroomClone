package com.ashar.MyClassroom.controller.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashar.MyClassroom.service.Student.StudentService;
import com.ashar.MyClassroom.entity.Student;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {

	@Autowired
	private StudentService StudentService;
	
	
	@PostMapping("/AllStudents")
	public List<Student> AllStudents (@RequestBody Map<String,String> obj ) throws SQLException {
            return  StudentService.AllStudents(obj);
	}

	@PostMapping("/IsStudentOfaClass")
	public boolean IsStudentOfaClass (@RequestBody Map<String,String> obj ) throws SQLException {
            return  StudentService.IsStudentOfaClass(obj);
	}
	
	@PostMapping("/GetStudentData")
	public List<Student> GetStudentData (@RequestBody Map<String,String> obj ) throws SQLException {
            return  StudentService.GetStudentData(obj);
	}

}
