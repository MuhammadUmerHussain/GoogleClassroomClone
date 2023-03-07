package com.ashar.MyClassroom.service.Class;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashar.MyClassroom.entity.Class;
import com.ashar.MyClassroom.repository.Class.ClassRepository;


@Service
public class ClassServiceImp implements ClassService {

	@Autowired
	private ClassRepository ClassRepo;

	@Override
	public boolean CreateClassroom(Map<String, String> obj) throws SQLException {
		
		String unique_code = GeneratingRandomString();
		return ClassRepo.CreateClassroom(obj.get("TeacherUsername"), obj.get("name"), obj.get("title"),
				obj.get("code"),unique_code , obj.get("descript"));
	}
	
	@Override
	public boolean EditClassroom(Map<String, String> obj) throws SQLException {
		return ClassRepo.EditClassroom(obj.get("class_id"),obj.get("TeacherUsername"), obj.get("name"), obj.get("title"),
				obj.get("code"), obj.get("descript"));
	}

	@Override
	public boolean DeleteClassroom(Map<String, String> obj) throws SQLException {
		return ClassRepo.DeleteClassroom(obj.get("class_id"),obj.get("TeacherUsername"));
	}

	@Override
	public boolean JoinClassroom(Map<String, String> obj) throws SQLException {
		return ClassRepo.JoinClassroom(obj.get("stdUsername"),obj.get("unique_class_code"));
	}
	
	@Override
	public String GetUniqueCodeOfClass(Map<String, String> obj) throws SQLException {
	   	return ClassRepo.GetUniqueCodeOfClass(obj.get("class_id"));
	}
	
	
	@Override
	public List<Class> AllClassroomsOfTeacher(Map<String, String> obj) throws SQLException {
	   	return ClassRepo.AllClassroomsOfTeacher(obj.get("username"));
	}
	
	@Override
	public List<Class> AllClassroomsOfStudent(Map<String, String> obj) throws SQLException {
		return ClassRepo.AllClassroomsOfStudent(obj.get("username"));
	}

	@Override
	public List<Class> GetClassData(Map<String, String> obj) throws SQLException {
		System.out.println( obj.get("class_id") );
		return ClassRepo.GetClassData(obj.get("class_id"));
	}

	
	
	public String GeneratingRandomString() {
		 
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 8;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
          return generatedString;
	}


	




}
