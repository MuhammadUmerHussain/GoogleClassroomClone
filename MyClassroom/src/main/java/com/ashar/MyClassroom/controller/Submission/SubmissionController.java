package com.ashar.MyClassroom.controller.Submission;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.core.io.Resource;

import com.ashar.MyClassroom.entity.Attachment;
import com.ashar.MyClassroom.service.Submission.SubmissionService;



@RestController
@CrossOrigin(origins = "*")
public class SubmissionController {

	@Autowired
	private SubmissionService SubmissionService;

	@PostMapping("/UploadAndSubmitAssignment")
	public boolean UploadAndSubmitAssignment(@RequestParam(value = "stdUsername", required = false) String stdUsername,
			@RequestParam(value = "assignmentId", required = false) String assignmentId, @RequestPart("External_File") MultipartFile file)
			throws SQLException, IOException {
		
		 System.out.println(stdUsername + "  " + assignmentId );
		 
		 int assignmentIdd = Integer.parseInt(assignmentId);
		
		List<String> return_value = SubmissionService.UploadAndSubmitAssignment(stdUsername, assignmentIdd, file);
		String id = return_value.get(0);
		boolean result1 = Boolean.parseBoolean(return_value.get(1));

		String downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(id)
				.toUriString();

		boolean result2 = SubmissionService.SetDownloadLinkSubmission(downloadURl, id);

		System.out.println(downloadURl + "\n\n\n" );
		return result1 && result2;
	}
	
	
	 @GetMapping("/download/{stdUsername}/{assignmentId}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable("stdUsername") String stdUsername,
	    		@PathVariable("assignmentId") int assignmentId) throws Exception {
		 
		 System.out.println(stdUsername + "\n\n\n" + assignmentId );
					 
	        Attachment attachment = SubmissionService.downloadFile(stdUsername, assignmentId).get(0);   
	        return  ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(attachment.getFileType()))
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" + attachment.getFileName()
	                + "\"")
	                .body(new ByteArrayResource(attachment.getData()));
	        
	    }
	 
		@PostMapping("/isSubmissionAvaliable")
		public boolean isSubmissionAvaliable(@RequestBody Map<String,String> obj )
				throws SQLException, IOException {

			String stdUsername = obj.get("stdUsername");
			int assignmentId = Integer.parseInt(obj.get("assignmentId"));
			
			 System.out.println(stdUsername + " " + assignmentId );
			 return  SubmissionService.isSubmissionAvaliable(stdUsername, assignmentId);
		}
		
		
}
