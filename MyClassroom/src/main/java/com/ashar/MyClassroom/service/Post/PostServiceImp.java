package com.ashar.MyClassroom.service.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashar.MyClassroom.entity.Post;
import com.ashar.MyClassroom.repository.Post.PostRepository;

@Service
public class PostServiceImp implements PostService {

	@Autowired
	private PostRepository PostRepo;

	@Override
	public boolean CreatePost(Map<String, String> obj) throws SQLException {
		
		Boolean result = PostRepo.createPost(obj.get("teacherUsername"), obj.get("class_id")
				, obj.get("title"), obj.get("descript"));
		return result;
	}

	@Override
	public boolean editPost(Map<String, String> obj) throws SQLException {
//		System.out.println(obj.get("post_id")+ obj.get("teacherUsername")+ obj.get("class_id")
//				+ obj.get("title")+ obj.get("descript"));
		
	     return PostRepo.editPost(obj.get("post_id"), obj.get("teacherUsername"), obj.get("class_id")
				, obj.get("title"), obj.get("descript"));
	}


	@Override
	public boolean deletePost( Map<String, String> obj) throws SQLException {
		return PostRepo.deletePost(obj.get("post_id"), obj.get("teacherUsername") );
	}

	@Override
	public List<Post> viewAllPost(String classId) throws SQLException {
		return PostRepo.viewAllPost(classId);
	}

}
