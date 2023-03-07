package com.ashar.MyClassroom.service.Class;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ashar.MyClassroom.entity.Assignment;
import com.ashar.MyClassroom.entity.Class;

public interface ClassService {

	boolean CreateClassroom(Map<String, String> obj) throws SQLException;

	boolean EditClassroom(Map<String, String> obj) throws SQLException;

	boolean DeleteClassroom(Map<String, String> obj) throws SQLException;

	boolean JoinClassroom(Map<String, String> obj) throws SQLException;

	String GetUniqueCodeOfClass(Map<String, String> obj) throws SQLException;

	List<Class> AllClassroomsOfTeacher(Map<String, String> obj) throws SQLException;

	List<Class> AllClassroomsOfStudent(Map<String, String> obj) throws SQLException;

	List<Class> GetClassData(Map<String, String> obj)throws SQLException;

}
