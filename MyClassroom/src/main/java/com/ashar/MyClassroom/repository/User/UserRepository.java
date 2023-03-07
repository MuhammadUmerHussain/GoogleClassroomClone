package com.ashar.MyClassroom.repository.User;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {

	@Autowired
	private DataSource dataSource;
	CallableStatement callableStatement;
	
	
	
	public boolean RegisterUser(String username, String first_name, String last_name, String user_password, String email,
			String phone_no, String gender, String age) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call RegisterUser(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		callableStatement.setString(1, username);
		callableStatement.setString(2, first_name);
		callableStatement.setString(3, last_name);
		callableStatement.setString(4, user_password);
		callableStatement.setString(5, email);
		callableStatement.setString(6, phone_no);
		callableStatement.setString(7, gender);
		callableStatement.setString(8, age);
		
		
		callableStatement.registerOutParameter(9, Types.BOOLEAN);
		callableStatement.executeUpdate();

		boolean result = callableStatement.getBoolean(9);
		callableStatement.getConnection().close();

		return result;
	}



	public boolean DeleteUser(String username) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call DeleteUser(?, ?)}");
		callableStatement.setString(1, username);
	
		
		callableStatement.registerOutParameter(2, Types.BOOLEAN);
		callableStatement.executeUpdate();

		boolean result = callableStatement.getBoolean(2);
		callableStatement.getConnection().close();
		return result;
	}



	public boolean ValidateUser(String username, String password) throws SQLException {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call ValidateUser(?, ?, ?)}");
		
		callableStatement.setString(1, username);
		callableStatement.setString(2, password);
		
		callableStatement.registerOutParameter(3, Types.BOOLEAN);
		callableStatement.executeUpdate();

		boolean result = callableStatement.getBoolean(3);
		callableStatement.getConnection().close();
		return result;
	}



	public String getThePasswordFromApi(String username) throws SQLException {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection().prepareCall("{call getThePasswordFromApi(?, ?)}");
		
		callableStatement.setString(1, username);
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		callableStatement.executeUpdate();

		String result = callableStatement.getString(2);
		callableStatement.getConnection().close();
		return result;
	}

}
