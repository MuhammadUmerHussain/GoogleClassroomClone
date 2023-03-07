package com.ashar.MyClassroom.repository.Submission;

import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ashar.MyClassroom.entity.Attachment;
import com.ashar.MyClassroom.mapper.AttachmentRowMapper;

@Repository
public class SubmissionRepository {

	@Autowired
	private DataSource dataSource;
	CallableStatement callableStatement;

	public List<String> UploadAndSubmitAssignment(String FileName, String FileSize, String stdUsername, int assignmentId,
			MultipartFile file, String Id) throws SQLException, IOException {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection()
				.prepareCall("{call UploadAndSubmitAssignment(?, ?, ?, ?, ?, ?, ?, ?)}");
		
		callableStatement.setString(1, stdUsername);
		callableStatement.setInt(2, assignmentId);
		callableStatement.setString(3, FileName);
		callableStatement.setString(4, FileSize);
		callableStatement.setString(5, file.getContentType());
		callableStatement.setBytes(6, file.getBytes());
		
		callableStatement.registerOutParameter(7, Types.BOOLEAN);
		callableStatement.registerOutParameter(8, Types.VARCHAR);
		callableStatement.executeUpdate();

		boolean result = callableStatement.getBoolean(7);
		Id = callableStatement.getString(8);
		callableStatement.getConnection().close();


		List<String> list=new ArrayList<String>();
		 
		list.add(Id);
		list.add(String.valueOf(result));
		
		return list;
	}


	public boolean SetDownloadLinkSubmission(String downloadURl, String id) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection()
				.prepareCall("{call SetDownloadLinkSubmission(?, ?, ?)}");
		
		callableStatement.setString(1, downloadURl);
		callableStatement.setString(2, id);

		callableStatement.registerOutParameter(3, Types.BOOLEAN);
		callableStatement.executeUpdate();

		boolean result = callableStatement.getBoolean(3);
		callableStatement.getConnection().close();
		
		return result;
	}


	@SuppressWarnings("deprecation")
	public List<Attachment> downloadFile(String stdUsername, int assignmentId) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Attachment> Attachment = jdbcTemplate.query("call downloadFile(?, ?);", new Object[] {stdUsername,assignmentId},new AttachmentRowMapper());
		
		return Attachment;
	}


	public boolean isSubmissionAvaliable(String stdUsername, int assignmentId) throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		callableStatement = jdbcTemplate.getDataSource().getConnection()
				.prepareCall("{call isSubmissionAvaliable(?, ?, ?)}");
		
		callableStatement.setString(1, stdUsername);
		callableStatement.setInt(2, assignmentId);

		callableStatement.registerOutParameter(3, Types.BOOLEAN);
		callableStatement.executeUpdate();

		boolean result = callableStatement.getBoolean(3);
		callableStatement.getConnection().close();
		
		return result;
	}



}
