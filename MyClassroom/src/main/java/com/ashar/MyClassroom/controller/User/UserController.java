package com.ashar.MyClassroom.controller.User;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashar.MyClassroom.service.User.UserService;


@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService UserService;
	
	
	@PostMapping("/RegisterUser")
	public boolean RegisterUser (@RequestBody Map<String,String> obj ) throws SQLException {
            return  UserService.RegisterUser(obj);
	}
	
	@DeleteMapping("/DeleteUser")
	public boolean DeleteUser (@RequestBody Map<String,String> obj ) throws SQLException {
            return  UserService.DeleteUser(obj);
	}
	
	@PostMapping("/ValidateUser")
	public boolean ValidateUser (@RequestBody Map<String,String> obj ) throws SQLException {
            return  UserService.ValidateUser(obj);
	}
	
//	@PostMapping("/ViewAllAssignment")
//	public List<Assignment> ViewAllAssignment (@RequestBody Map<String,String> obj ) throws SQLException {
//		return UserService.ViewAllAssignment(obj);
//	}
	

//	
//	
//	@PostMapping("/AllUsersOnPost")
//	public List<User> AllUsersOnPost (@RequestBody Map<String,String> obj ) throws SQLException {
//            return  UserService.AllUsersOnPost(obj);
//	}
//	


}
