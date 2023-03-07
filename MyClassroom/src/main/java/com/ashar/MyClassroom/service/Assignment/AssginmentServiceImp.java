package com.ashar.MyClassroom.service.Assignment;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashar.MyClassroom.entity.Assignment;
import com.ashar.MyClassroom.repository.Assignment.AssignmentRepository;


@Service
public class AssginmentServiceImp implements AssignmentService {

	@Autowired
	private AssignmentRepository AssignmentRepo;

	@Override
	public boolean CreateAssignment(Map<String, String> obj) throws SQLException {
		
		return AssignmentRepo.CreateAssignment(obj.get("teacherUsername"), obj.get("class_id"), obj.get("title"),
				obj.get("totalMarks"),obj.get("due_date"), obj.get("descript"));
	}

	@Override
	public boolean editAssignment(Map<String, String> obj) throws SQLException {
		System.out.println("service here");
		
		return AssignmentRepo.editAssignment(obj.get("assignment_id"), obj.get("teacherUsername"), obj.get("class_id")
				, obj.get("title"), obj.get("totalMarks"),obj.get("due_date"), obj.get("descript"));
	}

	@Override
	public boolean deleteAssignment(Map<String, String> obj) throws SQLException {
		return AssignmentRepo.deleteAssignment(obj.get("assignment_id"), obj.get("teacherUsername"));
	}

	@Override
	public List<Assignment> ViewAllAssignment(Map<String, String> obj) throws SQLException {
		return AssignmentRepo.ViewAllAssignment(obj.get("class_id"));
	}



}
