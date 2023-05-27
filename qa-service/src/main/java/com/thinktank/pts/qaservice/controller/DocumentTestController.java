package com.thinktank.pts.qaservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thinktank.pts.qaservice.dto.DocumentTestDTO;
import com.thinktank.pts.qaservice.mapper.TestAttachmentMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.TestAttachment;
import com.thinktank.pts.qaservice.service.TestAttachmentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "documents", description = "API for CRUD operations on documentTest.")
@RequestMapping("/testDocuments")
@CrossOrigin("*")
@Transactional
public class DocumentTestController {
	@Autowired
	private TestAttachmentService documentTestService;

	private TestAttachmentMapper mapper = new TestAttachmentMapper();

	@PostMapping("/upload/{testId}")
	public ResponseEntity<ResponseMessage> uploadFile(@PathVariable(name = "testId") Long testId,
			@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			documentTestService.store(file, testId);

			message = "Uploaded the file successfully: " + file.getName();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getName() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/download/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") Long fileId) {
		TestAttachment file = documentTestService.download(fileId).get();
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + file.getFileName() + "\"")
				.body(new ByteArrayResource(file.getData()));

	}

	@GetMapping("/{testId}")
	public List<DocumentTestDTO> getFilesByTest(@PathVariable Long testId) {
		List<TestAttachment> documentTests = documentTestService.getDocumentTestByTest(testId);
		List<DocumentTestDTO> documentTestDTOs = new ArrayList<>();
		for (TestAttachment doc : documentTests) {
			documentTestDTOs.add(mapper.mapToDTO(doc));
		}
		return documentTestDTOs;
	}

	@DeleteMapping("/deleteTestDocument/{docId}")
	public ResponseEntity<ResponseMessage> deleteDoc(@PathVariable(name = "docId") Long docId) {
		String message = "";
		try {
			documentTestService.deleteDocumentTest(docId);
			message = "Document " + docId + " deleted successfully";
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not delete the doc: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

		}
	}

	@PutMapping("editTestDocument/{docId}")
	@ResponseBody
	public ResponseEntity<ResponseMessage> updateDocTestCaseLibrary(@PathVariable(name = "docId") int docId,
			@RequestBody DocumentTestDTO documentTestDTO) {
		String message = "";

		try {
			TestAttachment doc = mapper.mapToEntity(documentTestDTO);
			TestAttachment docSaved = documentTestService.update(doc);
			if (docSaved != null) {
				message = "DocumentTestCaseLibrary request: " + docSaved.getId() + " updated successfully";
			}
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
					.body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not update the documentTestCaseLibrary: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}
}
