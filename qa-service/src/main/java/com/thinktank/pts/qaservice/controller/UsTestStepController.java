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
import com.thinktank.pts.qaservice.dto.UsTestStepDTO;
import com.thinktank.pts.qaservice.enums.TestStepState;
import com.thinktank.pts.qaservice.mapper.UsTestStepMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.UsTestCase;
import com.thinktank.pts.qaservice.model.UsTestStep;
import com.thinktank.pts.qaservice.service.UsTestCaseService;
import com.thinktank.pts.qaservice.service.UsTestStepService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "usTestStep", description = "API for CRUD operations on UsTestSteps.")
@Transactional
public class UsTestStepController {

	@Autowired
	private UsTestStepService testStepService;

	@Autowired
	private UsTestCaseService usTestCaseService;

	private UsTestStepMapper mapper = new UsTestStepMapper();

	@GetMapping("/usTestSteps/{testCaseId}")
	public List<UsTestStepDTO> getUsTestStepsByUsTestCaseId(@PathVariable(name = "testCaseId") Long testCaseId) {

		List<UsTestStepDTO> testStepDtoList = new ArrayList<>();
		List<UsTestStep> testStepList = testStepService.getUsTestStepsByTestCaseId(testCaseId);
		for (UsTestStep teststep : testStepList) {
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
	@PostMapping("/addUsTestStep")
	public ResponseEntity<ResponseMessage> addUsTestStep(@RequestBody UsTestStepDTO testStepDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			UsTestStep testStep = mapper.mapToEntity(testStepDTO);

			UsTestCase testCase = usTestCaseService.getUsTestCaseById(testStepDTO.getUsTestCaseId());
			testStep.setUsTestCase(testCase);

			testStepService.addUsTestStep(testStep);

			message = String.format("Test step: %s created successfully", testStep.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not create the test step: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/editUsTestStep")
	public ResponseEntity<ResponseMessage> updateUsTestStep(@RequestBody UsTestStepDTO testStepDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			UsTestStep testStep = mapper.mapToEntity(testStepDTO);
			UsTestCase testCase = usTestCaseService.getUsTestCaseById(testStepDTO.getUsTestCaseId());
			testStep.setUsTestCase(testCase);
			UsTestStep t = testStepService.updateUsTestStep(testStep);
			if (t != null) {
				message = String.format("Test step: %s updated successfully", testStep.getId());
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not update the test Step: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/editUsStepState/{id}")
	public ResponseEntity<ResponseMessage> updateUsTestState(@PathVariable(name = "id") Long id,
			@RequestBody UsTestStepDTO testStepDto, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			UsTestStep ts = mapper.mapToEntity(testStepDto);
			TestStepState state = ts.getTestStepState();
			Boolean result = testStepService.updateUsTestStepState(id, state);
			if (result) {
				message = "Test Step State " + id + " updated successfully";
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not update the test Step State: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@DeleteMapping("/deleteUsTestStep/{id}")
	public ResponseEntity<ResponseMessage> deleteTestStep(@PathVariable(name = "id") Long id) {
		String message = "";
		try {
			if (testStepService.deleteUsTestStep(id)) {
				message = String.format("Test step: %s deleted successfully", id);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} else {
				message = String.format("Test step: %s can't be deleted", id);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));

			}

		} catch (Exception e) {
			message = "Could not delete the test step: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

}
