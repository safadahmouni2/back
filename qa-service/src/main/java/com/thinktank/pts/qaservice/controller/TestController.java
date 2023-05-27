package com.thinktank.pts.qaservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.TestDTO;
import com.thinktank.pts.qaservice.enums.TestState;
import com.thinktank.pts.qaservice.mapper.TestMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.service.TestService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "test", description = "API for CRUD operations on test.")
@RequestMapping("/tests")
@Transactional
public class TestController {

	@Autowired
	private TestService testService;

	private TestMapper mapper = new TestMapper();

	@PutMapping("/editState/{id}")
	public ResponseEntity<ResponseMessage> updateTestState(@PathVariable(name = "id") Long id,
			@RequestBody TestDTO testDto, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			Test t = mapper.mapToEntity(testDto);
			TestState state = t.getTestState();
			boolean result = testService.updateTestState(id, state);
			if (result) {
				message = "Test State " + id + " updated successfully";
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
			}
			message = "Could not update the test State: " + id + "!";
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not update the test State: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@GetMapping(value = "/{testRunId}/{state}")
	public Integer countByTestRunIdAndState(@PathVariable(name = "testRunId") Long testRunId,
			@PathVariable(name = "state") TestState state) {
		return testService.countByTestRunIdAndState(testRunId, state);
	}

	@GetMapping(value = "/{userStoryId}")
	public List<TestDTO> getTestByUserStoryId(@PathVariable(name = "userStoryId") Long userStoryId) {
		List<TestDTO> testDTOList = new ArrayList<>();
		List<Test> testList = testService.getTestByUserStoryId(userStoryId);
		for (Test test : testList) {
			testDTOList.add(mapper.mapToDTO(test));
		}
		return testDTOList;
	}

	/**
	 * To Be implemented correctly to be a general method for search
	 * 
	 * @param testCaseLibraryId
	 * @param userStoryId
	 * @return
	 */
	@GetMapping()
	public List<TestDTO> findTests(@RequestParam(name = "userStoryId") Long userStoryId,
			@RequestParam(name = "testCaseLibraryId") Long testCaseLibraryId) {
		List<TestDTO> result = new ArrayList<>();

		List<Test> testList = testService.getTestsByUserStoryIdAndTestCaseLibraryId(userStoryId, testCaseLibraryId);

		if (!CollectionUtils.isEmpty(testList)) {
			result = testList.stream().map(test -> mapper.mapToDTO(test)).collect(Collectors.toList());
		}

		return result;
	}

	@PutMapping("/updateTest")
	@ResponseBody
	public ResponseEntity<ResponseMessage> updateTest(@RequestBody TestDTO testDTO) {
		String msg = "";
		try {
			if (testDTO.getTestId() != null) {
				boolean updated = testService.updateTest(testDTO);
				if (updated) {
					msg = "Test " + testDTO.getTestId() + " updated successfully";
					return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(msg));
				}
			}
			msg = "Test " + testDTO.getTestId() + " Could not be updated";
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage(msg));

		} catch (Exception e) {
			msg = "Could not update test : " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(msg));

		}
	}

	@GetMapping(value = "/testRun/{testRunId}")
	public List<TestDTO> getTestsByTestRun(@PathVariable(name = "testRunId") Long testRunId) {
		List<TestDTO> result = new ArrayList<>();

		List<Test> testList = testService.getTestsByTestRunId(testRunId);

		if (!CollectionUtils.isEmpty(testList)) {
			result = testList.stream().map(test -> mapper.mapToDTO(test)).collect(Collectors.toList());
		}

		return result;
	}

	@GetMapping(value = "/testResult/{userStoryId}/{tcId}")
	public List<TestDTO> getTestsByTestCaseIdAndUsId(@PathVariable(name = "userStoryId") Long userStoryId,@PathVariable(name = "tcId") Long tcId) {
		List<TestDTO> result = new ArrayList<>();

		List<Test> testList = testService.getTestsByUserStoryIdAndTestCaseLibraryId(userStoryId,tcId);
		if (!CollectionUtils.isEmpty(testList)) {
			result = testList.stream().map(test -> mapper.mapToDTO(test)).collect(Collectors.toList());
		}
		return result;
	}

}
