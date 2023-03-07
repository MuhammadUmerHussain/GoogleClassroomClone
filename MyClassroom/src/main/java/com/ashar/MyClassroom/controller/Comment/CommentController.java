package com.ashar.MyClassroom.controller.Comment;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashar.MyClassroom.entity.Comment;
import com.ashar.MyClassroom.service.Comment.CommentService;


@RestController
@CrossOrigin(origins = "*")
public class CommentController {

	@Autowired
	private CommentService CommentService;
	
//	@PostMapping("/ViewAllAssignment")
//	public List<Assignment> ViewAllAssignment (@RequestBody Map<String,String> obj ) throws SQLException {
//		return CommentService.ViewAllAssignment(obj);
//	}
	
	@PostMapping("/CommentOnPost")
	public boolean CommentOnPost (@RequestBody Map<String,String> obj ) throws SQLException {
            return  CommentService.CommentOnPost(obj);
	}
	
	
	@PostMapping("/AllCommentsOnPost")
	public List<Comment> AllCommentsOnPost (@RequestBody Map<String,String> obj ) throws SQLException {
            return  CommentService.AllCommentsOnPost(obj);
	}
	


}
