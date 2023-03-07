package com.ashar.MyClassroom.controller.Class;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashar.MyClassroom.entity.Class;
import com.ashar.MyClassroom.service.Class.ClassService;


@RestController
@CrossOrigin(origins = "*")
public class ClassController {

	@Autowired
	private ClassService ClassService;
	
	@PostMapping("/CreateClassroom")
	public boolean CreateClassroom (@RequestBody Map<String,String> obj ) throws SQLException {
            return  ClassService.CreateClassroom(obj);

	}
	
	@PutMapping("/EditClassroom")
	public boolean EditClassroom (@RequestBody Map<String,String> obj ) throws SQLException {
            return  ClassService.EditClassroom(obj);

	}
	
	@DeleteMapping("/DeleteClassroom")
	public boolean DeleteClassroom (@RequestBody Map<String,String> obj ) throws SQLException {
            return  ClassService.DeleteClassroom(obj);

	}
	
	@PostMapping("/JoinClassroom")
	public boolean JoinClassroom (@RequestBody Map<String,String> obj ) throws SQLException {
            return  ClassService.JoinClassroom(obj);
	}
	
	@PostMapping("/GetUniqueCodeOfClass")
	public String GetUniqueCodeOfClass (@RequestBody Map<String,String> obj ) throws SQLException {
            String result =  ClassService.GetUniqueCodeOfClass(obj);
            if(result.equals("-1")) return "false";
            else return result;
	}
	
	@PostMapping("/AllClassroomsOfTeacher")
	public List<Class> AllClassroomsOfTeacher (@RequestBody Map<String,String> obj ) throws SQLException {
            return  ClassService.AllClassroomsOfTeacher(obj);
	}

	
	@PostMapping("/AllClassroomsOfStudent")
	public List<Class> AllClassroomsOfStudent (@RequestBody Map<String,String> obj ) throws SQLException {
            return  ClassService.AllClassroomsOfStudent(obj);
	}
	
	@PostMapping("/GetClassData")
	public List<Class> GetClassData (@RequestBody Map<String,String> obj ) throws SQLException {
            return  ClassService.GetClassData(obj);
	}
}

