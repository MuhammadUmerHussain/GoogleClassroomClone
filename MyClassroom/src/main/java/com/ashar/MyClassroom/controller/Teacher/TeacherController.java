package com.ashar.MyClassroom.controller.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashar.MyClassroom.entity.Student;
import com.ashar.MyClassroom.entity.Teacher;
import com.ashar.MyClassroom.service.Teacher.TeacherService;


@RestController
@CrossOrigin(origins = "*")
public class TeacherController {

	@Autowired
	private TeacherService TeacherService;
	
	
	@PostMapping("/GetTeacherUsernameFromClassId")
	public String GetTeacherUsernameFromClassId (@RequestBody Map<String,String> obj ) throws SQLException {
            String result =  TeacherService.GetTeacherUsernameFromClassId(obj);
            if(result == null) return "false";
            return result;
	}
	
	@PostMapping("/IsTeacherOfaClass")
	public boolean IsTeacherOfaClass (@RequestBody Map<String,String> obj ) throws SQLException {
            return  TeacherService.IsTeacherOfaClass(obj);
	}
	
	@DeleteMapping("/RemoveStudentFromClass")
	public boolean RemoveStudentFromClass (@RequestBody Map<String,String> obj ) throws SQLException {
            return  TeacherService.RemoveStudentFromClass(obj);
	}
	
	
	@PostMapping("/GetTeacherData")
	public List<Teacher> GetTeacherData (@RequestBody Map<String,String> obj ) throws SQLException {
            return  TeacherService.GetTeacherData(obj);
	}
	

}
