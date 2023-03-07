package com.ashar.MyClassroom.controller.Assginment;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashar.MyClassroom.entity.Assignment;
import com.ashar.MyClassroom.service.Assignment.AssignmentService;


@RestController
@CrossOrigin(origins = "*")
public class AssignmentController {

	@Autowired
	private AssignmentService AssignmentService;
	
	@PostMapping("/ViewAllAssignment")
	public List<Assignment> ViewAllAssignment (@RequestBody Map<String,String> obj ) throws SQLException {
		return AssignmentService.ViewAllAssignment(obj);
	}
	
	@PostMapping("/createAssignment")
	public boolean CreateAssignment (@RequestBody Map<String,String> obj ) throws SQLException {
            return  AssignmentService.CreateAssignment(obj);

	}
	@PutMapping("/editAssignment")
	public boolean editAssignment (@RequestBody Map<String,String> obj ) throws SQLException {
	    	return AssignmentService.editAssignment(obj);
	}
	
	@DeleteMapping("/deleteAssignment")
	public boolean deleteAssignment (@RequestBody Map<String,String> obj ) throws SQLException {
		return  AssignmentService.deleteAssignment(obj);
	}

}
