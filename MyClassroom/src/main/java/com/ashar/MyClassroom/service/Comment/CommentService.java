package com.ashar.MyClassroom.service.Comment;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ashar.MyClassroom.entity.Assignment;
import com.ashar.MyClassroom.entity.Comment;

public interface CommentService {

	boolean CommentOnPost(Map<String, String> obj) throws SQLException;

	List<Comment> AllCommentsOnPost(Map<String, String> obj) throws SQLException;

}
