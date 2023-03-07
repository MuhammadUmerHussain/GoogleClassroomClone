package com.ashar.MyClassroom.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ashar.MyClassroom.entity.Attachment;

public class AttachmentRowMapper implements RowMapper<Attachment>{
	
	@Override
	public Attachment mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Attachment attachment = new Attachment();
		
		attachment.setFileName(rs.getString("FileName"));
		attachment.setData(rs.getBytes("External_File"));
		attachment.setFileType(rs.getString("FileContentType"));
		
		
//		submission_id, std_username, assign_id, FileName, FileSize, FileContentType, Data_submitted, DownloadUrl, External_File
		return attachment;
	}

	
}
