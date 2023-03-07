package com.ashar.MyClassroom.repository.Student;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ashar.MyClassroom.entity.Assignment;
import com.ashar.MyClassroom.entity.Student;
import com.ashar.MyClassroom.mapper.AssignmentRowMapper;
import com.ashar.MyClassroom.mapper.StudentRowMapper;
import com.ashar.MyClassroom.mapper.StudentRowMapper2;

@Repository
public class StudentRepository {

	@Autowired
	private DataSource dataSource;
	CallableStatement callableStatement;

	public boolean IsStudentOfaClass(String class_id, String stdUsername) throws SQLException {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection()
				.prepareCall("{call IsStudentOfaClass(?, ?, ?)}");
		callableStatement.setString(1, class_id);
		callableStatement.setString(2, stdUsername);
		callableStatement.registerOutParameter(3, Types.BOOLEAN);

		callableStatement.executeUpdate();

		boolean result = callableStatement.getBoolean(3);
		callableStatement.getConnection().close();

		return result;
	}

	@SuppressWarnings("deprecation")
	public List<Student> AllStudents(String class_id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Student> Students = jdbcTemplate.query("call AllStudents(?);", new Object[] { class_id },
				new StudentRowMapper());

		for (int i = 0; i < Students.size(); i++) {
			List<Student> s1 = GetStudentData(Students.get(i).getUsername());
			
		    Students.get(i).setF_name( s1.get(0).getF_name() );
		    Students.get(i).setL_name( s1.get(0).getL_name() );
		    Students.get(i).setEmail( s1.get(0).getEmail() );

		    Students.get(i).setPhone_no( s1.get(0).getPhone_no() );
		    Students.get(i).setGender( s1.get(0).getGender() );
		    Students.get(i).setAge( s1.get(0).getAge() );
		    
		}

		return Students;
	}

	@SuppressWarnings("deprecation")
	public List<Student> GetStudentData(String username) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Student> Students = jdbcTemplate.query("call GetStudentData(?);", new Object[] { username },
				new StudentRowMapper2());
		return Students;
	}
}
