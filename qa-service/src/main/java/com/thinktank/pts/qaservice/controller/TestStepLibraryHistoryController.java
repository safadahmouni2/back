package com.thinktank.pts.qaservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.TestStepLibraryHistoryDTO;
import com.thinktank.pts.qaservice.mapper.TestStepLibraryHistoryMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.TestCaseLibraryHistory;
import com.thinktank.pts.qaservice.model.TestStepLibraryHistory;
import com.thinktank.pts.qaservice.service.TestCaseLibraryHistoryService;
import com.thinktank.pts.qaservice.service.TestStepLibraryHistoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "testStepLibraryHistory", description = "API for CRUD operations on testStepLibraryHistory.")
@RequestMapping("/testStepLibraryHistory")
@Transactional
public class TestStepLibraryHistoryController {

	@Autowired
	private TestCaseLibraryHistoryService testCaseLibraryHistoryService;

	@Autowired
	private TestStepLibraryHistoryService testStepLibraryHistoryService;

	private TestStepLibraryHistoryMapper mapper = new TestStepLibraryHistoryMapper();

	@GetMapping
	@ResponseBody
	public List<TestStepLibraryHistoryDTO> getTestStepLibraryHistoryByTestCaseLibraryHistoryId(
			@PathVariable(name = "testCaseId") Long testCaseId) {
		return testStepLibraryHistoryService.getTestStepLibraryHistoryByTestCaseLibraryHistoryId(testCaseId).stream()
				.map(mapper::mapToDTO).collect(Collectors.toList());
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<ResponseMessage> addStepLibraryHistory(
			@RequestBody TestStepLibraryHistoryDTO testStepLibraryHistoryDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			TestStepLibraryHistory testStepLibraryHistory = mapper.mapToEntity(testStepLibraryHistoryDTO);
			TestCaseLibraryHistory testCaseLibraryHistory = testCaseLibraryHistoryService
					.getTestCaseLibraryHistoryById(testStepLibraryHistoryDTO.getTestCaseLibraryHistoryId());
			testStepLibraryHistory.setTestCaseLibraryHistory(testCaseLibraryHistory);

			testStepLibraryHistoryService.addTestStepLibraryHistory(testStepLibraryHistory);

			message = "Test step library history: " + testStepLibraryHistory.getId() + " created successfully";
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not create the test step library history : " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

}
