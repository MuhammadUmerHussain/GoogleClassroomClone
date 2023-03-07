package com.ashar.MyClassroom.controller.Grade;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashar.MyClassroom.entity.Assignment;
import com.ashar.MyClassroom.entity.Grade;
import com.ashar.MyClassroom.service.Grade.GradeService;


@RestController

@CrossOrigin(origins = "*")
public class GradeController {

	@Autowired
	private GradeService GradeService;
	
	@PostMapping("/ViewGrade")
	public List<Grade> ViewAllAssignment (@RequestBody Map<String,String> obj ) throws SQLException {
		return GradeService.ViewGrade(obj);
	}
	
	@PostMapping("/AssignGrade")
	public boolean AssignGrade (@RequestBody Map<String,String> obj ) throws SQLException {
            return  GradeService.AssignGrade(obj);
	}
	

}
