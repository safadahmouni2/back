package com.thinktank.pts.qaservice.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.TestEffortDTO;
import com.thinktank.pts.qaservice.mapper.TestEffortMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestEffort;
import com.thinktank.pts.qaservice.model.TestRun;
import com.thinktank.pts.qaservice.service.TestEffortService;
import com.thinktank.pts.qaservice.service.TestRunService;
import com.thinktank.pts.qaservice.service.TestService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Tag(name = "testEffort", description = "API for CRUD operations on testEfforts.")
@Slf4j
@Transactional
public class TestEffortController {

	@Autowired
	private TestEffortService testEffortService;

	@Autowired
	private TestService testService;

	@Autowired
	private TestRunService testRunService;

	private TestEffortMapper mapper = new TestEffortMapper();

	@PostMapping("/addTestEffort")
	@ResponseBody
	public ResponseEntity<ResponseMessage> addTestEffort(@RequestBody TestEffortDTO testEffortDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";

		try {
			TestEffort testEffort = mapper.mapToEntity(testEffortDTO);

			testEffort.setModifier(userCode);

			Test test = testService.getTestById(testEffortDTO.getTestId());
			testEffort.setTest(test);

			TestRun testRun = testRunService.getTestRunById(testEffortDTO.getTestRunId());
			testEffort.setTestRun(testRun);

			TestEffort result = testEffortService.addTestEffort(testEffort);
			if (result != null) {
				message = testEffort.getId().toString();
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
						new ResponseMessage(String.format("test run is %s  could not be edited", testRun.getState())));

			}

		} catch (Exception e) {
			log.error("Could not create the testEffort", e);
			message = String.format("Could not create the testEffort: %s!", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/finish-test-efforts/{id}")
	public ResponseEntity<ResponseMessage> finishTestEffort(@PathVariable(name = "id") Long id,
			@RequestBody TestEffortDTO testEffortDTO, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			TestEffort testEffort = mapper.mapToEntity(testEffortDTO);
			Test test = testService.getTestById(testEffortDTO.getTestId());
			testEffort.setTest(test);
			TestEffort t = testEffortService.finishTestEffort(id, testEffort, userCode);
			if (t != null) {
				message = String.format("Test effort: %s finished successfully", testEffortDTO.getId());
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
			} else {
				log.error("test effort: {} is could not be finished", id);
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body(new ResponseMessage("test effort: " + id + " is could not be finished"));
			}

		} catch (Exception e) {
			log.error("Could not finish the test effort", e);
			message = String.format("Could not finish the test effort: %s!", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/editTestEffort/{id}")
	public ResponseEntity<ResponseMessage> updateTestEffort(@PathVariable(name = "id") Long id,
			@RequestBody TestEffortDTO testEffortDTO, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			TestEffort testEffort = mapper.mapToEntity(testEffortDTO);
			Test test = testService.getTestById(testEffortDTO.getTestId());
			testEffort.setTest(test);
			TestEffort t = testEffortService.updateTestEffort(id, testEffort, userCode);
			if (t != null) {
				message = String.format("Test effort: %s updated successfully", testEffortDTO.getId());
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
			} else {
				log.error("test effort: {} is could not be updated", id);
				message = String.format("test effort: %s is could not be updated", id);
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body(new ResponseMessage(message));
			}

		} catch (Exception e) {
			log.error("Could not update the test effort", e);
			message = String.format("Could not update the test effort: %s!", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

		}
	}

	@PutMapping("/close-test-efforts/{id}")
	public ResponseEntity<ResponseMessage> closeTestEffort(@PathVariable(name = "id") Long id,
			@RequestBody TestEffortDTO testEffortDTO, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			TestEffort testEffort = mapper.mapToEntity(testEffortDTO);
			Test test = testService.getTestById(testEffortDTO.getTestId());
			testEffort.setTest(test);
			TestEffort t = testEffortService.closeTestEffort(id, testEffort, userCode);
			if (t != null) {
				message = String.format("Test effort: %s closed successfully", testEffortDTO.getId());
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
			} else {
				log.error("test effort: {} is could not be closed", id);
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body(new ResponseMessage("test effort: " + id + " is could not be closed"));
			}

		} catch (Exception e) {
			log.error("Could not close the test effort", e);
			message = "Could not close the test effort: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/testEffortByTest/{testId}")
	public List<TestEffortDTO> getTestEffortListByTestId(@PathVariable(name = "testId") Long testId) {
		List<TestEffortDTO> testEffortDtoList = new ArrayList<>();
		List<TestEffort> testEffortList = testEffortService.getTestEffortListByTestId(testId);
		for (TestEffort testEffort : testEffortList) {
			testEffortDtoList.add(mapper.mapToDTO(testEffort));
		}
		return testEffortDtoList;
	}

	@GetMapping("/totalTestEffortByTestRun/{testRunId}")
	public Time getTotalTestEffortByTestRunId(@PathVariable(name = "testRunId") Long testRunId) {
		Time totalTestEffort;
		totalTestEffort = testEffortService.getTotalTestEffortByTestRunId(testRunId);
		return totalTestEffort;
	}

	@GetMapping("/last-test-effort/{testRunId}")
	public TestEffortDTO getLastUnCompletedTestEffortByTestRunId(@PathVariable(name = "testRunId") Long testRunId) {
		TestEffort testEffort = testEffortService.getLastTestEffortByTestRunId(testRunId);
		return mapper.mapToDTO(testEffort);
	}

	@GetMapping("/totalTestEffortByTest/{testId}")
	public Time getTotalTestEffortByTestId(@PathVariable(name = "testId") Long testId) {
		Time totalTestEffort;
		totalTestEffort = testEffortService.getTotalTestEffortByTestId(testId);
		return totalTestEffort;
	}
}
