package com.ashar.MyClassroom.service.Teacher;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashar.MyClassroom.entity.Teacher;
import com.ashar.MyClassroom.repository.Teacher.TeacherRepository;

@Service
public class TeacherServiceImp implements TeacherService {

	@Autowired
	private TeacherRepository TeacherRepo;

	@Override
	public String GetTeacherUsernameFromClassId(Map<String, String> obj) throws SQLException {
		return TeacherRepo.GetTeacherUsernameFromClassId(obj.get("class_id"));
	}

	
	@Override
	public boolean RemoveStudentFromClass(Map<String, String> obj) throws SQLException {
		 return TeacherRepo.RemoveStudentFromClass(obj.get("teacherUsername"), obj.get("class_id"), obj.get("stdUsername"));
	}


	@Override
	public boolean IsTeacherOfaClass(Map<String, String> obj) throws SQLException {
		 return TeacherRepo.IsTeacherOfaClass(obj.get("class_id"), obj.get("TeacherUsername"));
	}


	@Override
	public List<Teacher> GetTeacherData(Map<String, String> obj) {
		return TeacherRepo.GetTeacherData(obj.get("username"));
	}

}
