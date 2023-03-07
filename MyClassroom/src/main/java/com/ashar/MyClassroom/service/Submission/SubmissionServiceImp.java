package com.ashar.MyClassroom.service.Submission;

import java.io.IOException;
import java.sql.SQLException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ashar.MyClassroom.config.JasyptEncryptorConfig;
import com.ashar.MyClassroom.entity.Attachment;
import com.ashar.MyClassroom.repository.Submission.SubmissionRepository;

@Service
public class SubmissionServiceImp implements SubmissionService {

	@Autowired
	private SubmissionRepository SubmissionRepo;


	@Override
	public List<String> UploadAndSubmitAssignment(String stdUsername, int assignmentId, MultipartFile file) throws IOException, SQLException {
		 String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		 String fileSize = humanReadableByteCountSI(file.getSize());
		 
		 String Id= "";
		 List<String> return_value = SubmissionRepo.UploadAndSubmitAssignment(fileName,fileSize,stdUsername, assignmentId,file, Id);
	     System.out.println("ID: "+ return_value.get(0));
	     return return_value;
	}
	
	@Override
	public boolean SetDownloadLinkSubmission(String downloadURl, String id) throws SQLException {
	   
		return SubmissionRepo.SetDownloadLinkSubmission(downloadURl,id);
	}
	
	
	@Override
	public  List<Attachment> downloadFile(String stdUsername, int assignmentId) {
		return SubmissionRepo.downloadFile(stdUsername, assignmentId);
	}

	@Override
	public boolean isSubmissionAvaliable(String stdUsername, int assignmentId) throws SQLException {
		return SubmissionRepo.isSubmissionAvaliable(stdUsername, assignmentId);
	}
	
	
	public static String humanReadableByteCountSI(long bytes) {
	    if (-1000 < bytes && bytes < 1000) {
	        return bytes + " B";
	    }
	    CharacterIterator ci = new StringCharacterIterator("kMGTPE");
	    while (bytes <= -999_950 || bytes >= 999_950) {
	        bytes /= 1000;
	        ci.next();
	    }
	    return String.format("%.1f %cB", bytes / 1000.0, ci.current());
	}

}