package com.thinktank.pts.qaservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.TestStepDTO;
import com.thinktank.pts.qaservice.enums.TestStepState;
import com.thinktank.pts.qaservice.mapper.TestStepMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestStep;
import com.thinktank.pts.qaservice.service.TestCaseLibraryService;
import com.thinktank.pts.qaservice.service.TestService;
import com.thinktank.pts.qaservice.service.TestStepService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "testStep", description = "API for CRUD operations on testSteps.")
@Transactional
public class TestStepController {

	@Autowired
	private TestStepService testStepService;

	@Autowired
	private TestCaseLibraryService testCaseLibraryService;
	@Autowired
	private TestService testService;

	private TestStepMapper mapper = new TestStepMapper();

	@GetMapping("/testSteps/{testCaseLibraryId}")
	public List<TestStepDTO> getTestStepsByTestCaseLibraryId(
			@PathVariable(name = "testCaseLibraryId") Long testCaseLibraryId) {

		List<TestStepDTO> testStepDtoList = new ArrayList<>();
		List<TestStep> testStepList = testStepService.getTestStepsByTestCaseLibraryId(testCaseLibraryId);
		for (TestStep teststep : testStepList) {
			testStepDtoList.add(mapper.mapToDTO(teststep));
		}
		return testStepDtoList;
	}

	@GetMapping("/testStepsByTestId/{testId}")
	public List<TestStepDTO> getTestStepsByTestId(@PathVariable(name = "testId") Long testId) {

		List<TestStepDTO> testStepDtoList = new ArrayList<>();
		List<TestStep> testStepList = testStepService.getTestStepByTestId(testId);
		for (TestStep teststep : testStepList) {
			testStepDtoList.add(mapper.mapToDTO(teststep));
		}
		return testStepDtoList;
	}

	/**
	 * addTestStep: add test steps to test case library
	 * 
	 * @param testStepDTO
	 * @return message
	 */
	@PostMapping("/addTestStep")
	public ResponseEntity<ResponseMessage> addTestStep(@RequestBody TestStepDTO testStepDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			TestStep testStep = mapper.mapToEntity(testStepDTO);

			TestCaseLibrary testCaseLibrary = testCaseLibraryService
					.getTestCaseLibraryById(testStepDTO.getUsTestCaseId());
			testStep.setTestCaseLibrary(testCaseLibrary);

			Test test = testService.getTestById(testStepDTO.getTestId());
			testStep.setTest(test);

			Boolean result = testStepService.addTestStep(testStep);
			if (result) {
				message = testStep.getId().toString();
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body(new ResponseMessage("Could not create the Test Step"));
			}

		} catch (Exception e) {
			message = "Could not create the test step: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/editTestStep")
	public ResponseEntity<ResponseMessage> updateTestStep(@RequestBody TestStepDTO testStepDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			TestStep testStep = mapper.mapToEntity(testStepDTO);
			TestCaseLibrary testCaseLibrary = testCaseLibraryService
					.getTestCaseLibraryById(testStepDTO.getUsTestCaseId());
			testStep.setTestCaseLibrary(testCaseLibrary);
			TestStep t = testStepService.updateTestStep(testStep);
			if (t != null) {
				message = "Test Step:" + testStepDTO.getId() + " updated successfully";
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body(new ResponseMessage("Could not update the Test Step"));
			}


		} catch (Exception e) {
			message = "Could not update the test Step: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/editStepState/{id}")
	public ResponseEntity<ResponseMessage> updateTestState(@PathVariable(name = "id") Long id,
			@RequestBody TestStepDTO testStepDto, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			TestStep ts = mapper.mapToEntity(testStepDto);
			TestStepState state = ts.getTestStepState();
			TestStep result = testStepService.updateTestStepState(id, state);
			if (result != null) {
				message = "Test Step State " + id + " updated successfully";
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body(new ResponseMessage("Could not update the Test Step state"));
			}

		} catch (Exception e) {
			message = "Could not update the test Step State: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@DeleteMapping("/deleteTestStep/{id}")
	public ResponseEntity<ResponseMessage> deleteTestStep(@PathVariable(name = "id") Long id) {
		String message = "";
		try {
			if (testStepService.deleteTestStep(id)) {
				message = "Test step: " + id + " deleted successfully";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} else {
				message = "Test step: " + id + " can't be deleted";
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage(message));

			}

		} catch (Exception e) {
			message = "Could not delete the test step: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

}
