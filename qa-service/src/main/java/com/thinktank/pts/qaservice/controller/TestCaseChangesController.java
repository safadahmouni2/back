package com.thinktank.pts.qaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.TestCaseChangesDTO;
import com.thinktank.pts.qaservice.mapper.TestCaseChangesMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestCaseChanges;
import com.thinktank.pts.qaservice.service.TestCaseChangesService;
import com.thinktank.pts.qaservice.service.TestService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Tag(name = "test-case-changes", description = "API for CRUD operations on Test Case Changes.")
@RequestMapping("/test-case-changes")
@Slf4j
@Transactional
public class TestCaseChangesController {

	@Autowired
	private TestCaseChangesService testCaseChangesService;

	@Autowired
	private TestService testService;

	private TestCaseChangesMapper mapper = new TestCaseChangesMapper();

	@PostMapping
	@ResponseBody
	public ResponseEntity<ResponseMessage> addTestCaseChanges(@RequestBody TestCaseChangesDTO testCaseChangesDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";

		try {
			Test test = testService.getTestById(testCaseChangesDTO.getTestId());

			TestCaseChanges testCaseChanges = mapper.mapTestToEntity(testCaseChangesDTO, test);
			testCaseChangesService.addTestCaseChanges(testCaseChanges);
			message = "test case changes: " + testCaseChanges.getId() + " created successfully";
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));

		} catch (Exception e) {
			log.error("Failed to add Test Case Changes", e);
			message = "Could not create the Test Case Changes: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@GetMapping
	public TestCaseChangesDTO getTestCaseChangesByTestCaseChangeRequestId(
			@RequestParam(name = "testCaseChangeRequestId") Long testCaseChangeRequestId) {
		return mapper
				.mapToDTO(testCaseChangesService.getTestCaseChangesByTestCaseChangeRequestId(testCaseChangeRequestId));

	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ResponseMessage> updateTestCaseChanges(@PathVariable(name = "id") int id,
			@RequestBody TestCaseChangesDTO testCaseChangesDTO, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";

		try {
			TestCaseChanges testCaseChanges = mapper.mapToEntity(testCaseChangesDTO);
			testCaseChanges.setModifier(userCode);
			TestCaseChanges updatedTestCaseChanges = testCaseChangesService.updateTestCaseChanges(testCaseChanges);
			if (updatedTestCaseChanges != null) {
				message = "Test Case Changes" + testCaseChanges.getId() + " updated successfully";
			}
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
					.body(new ResponseMessage(message));

		} catch (Exception e) {
			log.error("Failed to update Test Case Changes", e);
			message = "Could not update the test Case Changes: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

}
