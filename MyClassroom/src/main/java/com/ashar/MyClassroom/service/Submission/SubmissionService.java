package com.ashar.MyClassroom.service.Submission;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

import com.ashar.MyClassroom.entity.Attachment;

public interface SubmissionService {



	List<String> UploadAndSubmitAssignment(String stdUsername, int assignmentId, MultipartFile file) throws IOException, SQLException;

	boolean SetDownloadLinkSubmission(String downloadURl, String id) throws SQLException;

	 List<Attachment> downloadFile(String stdUsername, int assignmentId);

	boolean isSubmissionAvaliable(String stdUsername, int assignmentId) throws SQLException;


}
