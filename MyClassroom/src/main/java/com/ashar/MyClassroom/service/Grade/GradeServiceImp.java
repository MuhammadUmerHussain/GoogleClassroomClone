package com.ashar.MyClassroom.service.Grade;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashar.MyClassroom.entity.Grade;
import com.ashar.MyClassroom.repository.Grade.GradeRepository;

@Service
public class GradeServiceImp implements GradeService {

	@Autowired
	private GradeRepository GradeRepo;

	
	@Override
	public List<Grade> ViewGrade(Map<String, String> obj) throws SQLException {
		return GradeRepo.ViewGrade(obj.get("assignment_id"), obj.get("stdUsername"));
	}


	@Override
	public boolean AssignGrade(Map<String, String> obj) throws SQLException {
		
		System.out.println(  obj.get("assignment_id")+ obj.get("std_username")+ obj.get("teacherUsername")
				+ obj.get("MarksObt") );
		
		return GradeRepo.ViewGrade(obj.get("assignment_id"), obj.get("std_username"), obj.get("teacherUsername")
				, obj.get("MarksObt"));
	}

}
