package com.thinktank.pts.qaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.TestStepChangesDTO;
import com.thinktank.pts.qaservice.mapper.TestStepChangesMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.TestStepChanges;
import com.thinktank.pts.qaservice.service.TestStepChangesService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "test-step-changes", description = "API for CRUD operations on Test Step Changes.")
@RequestMapping("/test-step-changes")
@Transactional
public class TestStepChangesController {

	@Autowired
	private TestStepChangesService testStepChangesService;

	private TestStepChangesMapper mapper = new TestStepChangesMapper();

	@PostMapping()
	@ResponseBody
	public ResponseEntity<ResponseMessage> addTestStepChanges(@RequestBody TestStepChangesDTO testStepChangesDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";

		try {
			TestStepChanges testStepChanges = mapper.mapToEntity(testStepChangesDTO);
			testStepChangesService.addTestStepChanges(testStepChanges);

			message = "Test Step Chnages: " + testStepChanges.getId() + " created successfully";
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not create the Test Step Changes : " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<ResponseMessage> updateTestStepChanges(@PathVariable(name = "id") int id,
			@RequestBody TestStepChangesDTO testStepChangesDTO, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";

		try {
			TestStepChanges testStepChanges = mapper.mapToEntity(testStepChangesDTO);
			testStepChanges.setModifier(userCode);
			TestStepChanges updatedTestStepChanges = testStepChangesService.updateTestStepChanges(testStepChanges);
			if (updatedTestStepChanges != null) {
				message = "Test Step Changes" + testStepChanges.getId() + " updated successfully";
			}
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
					.body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not update the test Step Changes: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseMessage> deleteTestStepChange(@PathVariable(name = "id") Long id) {
		String message = "";
		try {
			if (testStepChangesService.deleteTestStepChanges(id)) {
				message = "Test Step Changes: " + id + " deleted successfully";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} else {
				message = "Test Step Changes: " + id + " can't be deleted";
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));

			}

		} catch (Exception e) {
			message = "Could not delete the case changes: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

}
