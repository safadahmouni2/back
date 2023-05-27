package com.thinktank.pts.qaservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.TestStepLibraryDTO;
import com.thinktank.pts.qaservice.enums.TestStepState;
import com.thinktank.pts.qaservice.mapper.TestStepLibraryMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestStepLibrary;
import com.thinktank.pts.qaservice.service.TestCaseLibraryService;
import com.thinktank.pts.qaservice.service.TestStepLibraryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "testStepsLibrary", description = "API for CRUD operations on testStepsLibrary.")
@RequestMapping("/testStepsLibrary")
@Transactional
public class TestStepLibraryController {

	@Autowired
	private TestStepLibraryService testStepLibraryService;

	@Autowired
	private TestCaseLibraryService testCaseLibraryService;

	private TestStepLibraryMapper mapper = new TestStepLibraryMapper();

	@GetMapping
	public List<TestStepLibraryDTO> getTestStepsLibraryByTestCaseLibraryId(
			@RequestParam(name = "testCaseLibraryId") int testCaseLibraryId) {
		return testStepLibraryService.getTestStepsLibraryByTestCaseLibraryId(testCaseLibraryId).stream()
				.map(mapper::mapToDTO).collect(Collectors.toList());

	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<ResponseMessage> addTestStepLibrary(@RequestBody TestStepLibraryDTO testStepLibraryDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String response = "";
		try {
			TestStepLibrary testStepLibrary = mapper.mapToEntity(testStepLibraryDTO);
			TestCaseLibrary testCaseLibrary = testCaseLibraryService
					.getTestCaseLibraryById(testStepLibraryDTO.getTestCaseLibraryId());
			testStepLibrary.setTestCaseLibrary(testCaseLibrary);
			testStepLibraryService.addTestStepLibrary(testStepLibrary);
			response = "" + testStepLibrary.getId();
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(response));

		} catch (Exception e) {
			response = "Could not create the test step Library: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(response));
		}
	}

	@PutMapping
	public ResponseEntity<ResponseMessage> updateTestStepLibrary(@RequestBody TestStepLibraryDTO testStepLibraryDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String response = "";
		try {
			TestStepLibrary testStepLibrary = mapper.mapToEntity(testStepLibraryDTO);
			TestCaseLibrary testCaseLibrary = testCaseLibraryService
					.getTestCaseLibraryById(testStepLibraryDTO.getTestCaseLibraryId());
			testStepLibrary.setTestCaseLibrary(testCaseLibrary);
			TestStepLibrary t = testCaseLibraryService.updateTestStepLibrary(testStepLibrary);
			if (t != null) {
				response = "Test Step: " + testStepLibraryDTO.getId() + " updated successfully";
			}
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
					.body(new ResponseMessage(response));
		} catch (Exception e) {
			response = "Could not update the test Step Library: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
					.body(new ResponseMessage(response));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseMessage> updateTestLibraryState(@PathVariable(name = "id") Long id,
			@RequestBody TestStepState state, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String response = "";
		try {
			Boolean result = testStepLibraryService.updateTestStepLibraryState(id, state);
			if (result) {
				response = "Test Step Library State " + id + " updated successfully";
			}
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
					.body(new ResponseMessage(response));

		} catch (Exception e) {
			response = "Could not update the test Step Library State: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
					.body(new ResponseMessage(response));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseMessage> deleteTestStepLibrary(@PathVariable(name = "id") Long id) {
		String response = "";
		try {
			if (testStepLibraryService.deleteTestStepLibrary(id)) {
				response = "Test step Library : " + id + " deleted successfully";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(response));
			} else {
				response = "Test step Library : " + id + " can't be deleted";
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(response));

			}

		} catch (Exception e) {
			response = "Could not delete the test step Library : " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(response));
		}
	}

}
