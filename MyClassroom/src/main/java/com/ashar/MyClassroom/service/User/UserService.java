package com.ashar.MyClassroom.service.User;

import java.sql.SQLException;
import java.util.Map;

public interface UserService {


	boolean RegisterUser(Map<String, String> obj) throws SQLException;

	boolean DeleteUser(Map<String, String> obj)throws SQLException;

	boolean ValidateUser(Map<String, String> obj)throws SQLException;


}
