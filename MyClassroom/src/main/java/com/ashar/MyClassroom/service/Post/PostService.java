package com.ashar.MyClassroom.service.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ashar.MyClassroom.entity.Post;

public interface PostService {


	boolean CreatePost(Map<String, String> obj) throws SQLException;

	boolean editPost(Map<String, String> obj) throws SQLException;

	boolean deletePost(Map<String, String> obj) throws SQLException;

	List<Post> viewAllPost(String classId) throws SQLException; 


}
