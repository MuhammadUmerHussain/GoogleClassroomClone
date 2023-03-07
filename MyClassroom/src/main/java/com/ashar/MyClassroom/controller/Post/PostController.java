package com.ashar.MyClassroom.controller.Post;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashar.MyClassroom.entity.Post;
import com.ashar.MyClassroom.service.Post.PostService;


@RestController
@CrossOrigin(origins = "*")
public class PostController {

	@Autowired
	private PostService PostService;
	
	@PostMapping("/createPost")
	public String createPost (@RequestBody Map<String,String> obj ) throws SQLException {
	    	boolean result = PostService.CreatePost(obj);
            return Boolean.toString(result);
	}
	
	
	@PutMapping("/editPost")
	public String editPost (@RequestBody Map<String,String> obj ) throws SQLException {
		 
		 boolean result = PostService.editPost(obj);
		 return Boolean.toString(result);
	}

	
	@DeleteMapping("/deletePost")
	public String DeletePost (@RequestBody Map<String,String> obj ) throws SQLException {

		boolean result = PostService.deletePost(obj);
		return Boolean.toString(result);

	}
	

	@PostMapping("/viewAllPost")
	public List<Post> viewAllPost (@RequestBody Map<String,String> obj ) throws SQLException {
	
		String classId = obj.get("class_id");
		return PostService.viewAllPost(classId);

	}

}
