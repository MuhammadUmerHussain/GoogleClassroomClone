package com.ashar.MyClassroom.service.Comment;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashar.MyClassroom.entity.Assignment;
import com.ashar.MyClassroom.entity.Comment;
import com.ashar.MyClassroom.repository.Comment.CommentRepository;


@Service
public class CommentServiceImp implements CommentService {

	@Autowired
	private CommentRepository CommentRepo;

	@Override
	public boolean CommentOnPost(Map<String, String> obj) throws SQLException {
		
		return CommentRepo.CommentOnPost(obj.get("post_id"),obj.get("by_student"),obj.get("by_Teacher"),obj.get("comment_text"));
	}


	
	public List<Comment>  AllCommentsOnPost(Map<String, String> obj) throws SQLException {
		
		return CommentRepo.AllCommentsOnPost(obj.get("post_id"));
	}




}
