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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.TestCaseChangeRequestDTO;
import com.thinktank.pts.qaservice.enums.ChangeRequestGlobalActionEnum;
import com.thinktank.pts.qaservice.mapper.TestCaseChangeRequestMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.TestCaseChangeRequest;
import com.thinktank.pts.qaservice.service.TestCaseChangeRequestService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Tag(name = "test-case-change-requests", description = "API for CRUD operations on Test Case Change Requests.")
@RequestMapping("/test-case-change-requests")
@Slf4j
@Transactional
public class TestCaseChangeRequestController {

	@Autowired
	private TestCaseChangeRequestService testCaseChangeRequestService;

	private TestCaseChangeRequestMapper mapper = new TestCaseChangeRequestMapper();

	@PostMapping
	@ResponseBody
	public ResponseEntity<ResponseMessage> addTestCaseChangeRequest(
			@RequestBody TestCaseChangeRequestDTO testCaseChangeRequestDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		try {
			TestCaseChangeRequest testCaseChangeRequest = mapper.mapToEntity(testCaseChangeRequestDTO);
			TestCaseChangeRequest result = testCaseChangeRequestService.addTestCaseChangeRequest(testCaseChangeRequest);
			if (result != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(
						" Test Case Changes: " + testCaseChangeRequest.getId() + " created successfully"));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body(new ResponseMessage("Could not create the Test Case Changes Request"));
			}
		} catch (Exception e) {
			log.error("Failed to create Test Case Changes", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseMessage("Could not create the Test Case Changes: " + e.getMessage() + "!"));
		}
	}

	@PostMapping("{action}/{testCaseChangeRequestId}")
	@ResponseBody
	public ResponseEntity<ResponseMessage> treatTestCaseChangeRequest(
			@PathVariable(name = "testCaseChangeRequestId") Long testCaseChangeRequestId,
			@PathVariable(name = "action") ChangeRequestGlobalActionEnum action,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());

		try {
			TestCaseChangeRequest testCaseChangeRequest = testCaseChangeRequestService
					.treatTestCaseChangeRequest(action, testCaseChangeRequestId);
			if (testCaseChangeRequest != null) {
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(
						"Test Case Change Request state: " + testCaseChangeRequest.getId() + " updated successfully"));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseMessage("Test Case Change Request not found."));
			}
		} catch (Exception e) {
			log.error("Failed to treat TC Change Request [id: {}]", testCaseChangeRequestId, e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
					"Could not update the Test Case Change Request state: " + e.getMessage() + "!"));
		}
	}

	@GetMapping()
	public List<TestCaseChangeRequestDTO> getTestCaseChangeRequestsByProductId(
			@RequestParam(name = "productId") long productId) {
		return testCaseChangeRequestService.getTestCaseChangeRequestsByProductIdAndStatus(productId).stream()
				.map(mapper::mapToDTO).collect(Collectors.toList());

	}

	@GetMapping(value = "/{productId}")
	public int getCountOfTestCaseChangeRequestByProductId(@PathVariable(name = "productId") Long productId) {
		return testCaseChangeRequestService.countTestCaseChangeRequestByProductIdAndStatus(productId);
	}

}
