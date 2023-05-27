package com.thinktank.pts.qaservice.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.LibraryTestCaseEffortDTO;
import com.thinktank.pts.qaservice.mapper.LibraryTestCaseEffortMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.LibraryTestCaseEffort;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.service.LibraryTestCaseEffortService;
import com.thinktank.pts.qaservice.service.TestCaseLibraryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Tag(name = "libraryTestCaseEffort", description = "API for CRUD operations on testEfforts.")
@RequestMapping("/libraryTestCaseEfforts")
@Slf4j
@Transactional
public class LibraryTestCaseEffortController {

	@Autowired
	private LibraryTestCaseEffortService librartCaseEffortService;

	@Autowired
	private TestCaseLibraryService testCaseLibraryService;

	private LibraryTestCaseEffortMapper mapper = new LibraryTestCaseEffortMapper();

	@PostMapping("/addLibraryTestCaseEffort")
	@ResponseBody
	public ResponseEntity<ResponseMessage> addLibraryTestCaseEffort(
			@RequestBody LibraryTestCaseEffortDTO libraryTestCaseEffortDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";

		try {
			LibraryTestCaseEffort libraryTestCaseEffort = mapper.mapToEntity(libraryTestCaseEffortDTO);

			libraryTestCaseEffort.setModifier(userCode);

			TestCaseLibrary testCaseLibrary = testCaseLibraryService
					.getTestCaseLibraryById(libraryTestCaseEffortDTO.getTestCaseLibraryId());
			libraryTestCaseEffort.setTestCaseLibrary(testCaseLibrary);

			LibraryTestCaseEffort result = librartCaseEffortService.addLibraryTestCaseEffort(libraryTestCaseEffort);
			if (result != null) {
				message = libraryTestCaseEffort.getId().toString();
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
						new ResponseMessage("test run is " + testCaseLibrary.getState() + " could not be edited"));

			}

		} catch (Exception e) {
			log.error("Could not create the testEffort", e);
			message = "Could not create the testEffort: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/editLibraryTestCaseEffort/{id}")
	public ResponseEntity<ResponseMessage> updateTestEffort(@PathVariable(name = "id") Long id,
			@RequestBody LibraryTestCaseEffortDTO libraryTestCaseEffortDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			LibraryTestCaseEffort libraryTestCaseEffort = mapper.mapToEntity(libraryTestCaseEffortDTO);
			TestCaseLibrary testCaseLibrary = testCaseLibraryService
					.getTestCaseLibraryById(libraryTestCaseEffortDTO.getTestCaseLibraryId());
			libraryTestCaseEffort.setTestCaseLibrary(testCaseLibrary);
			LibraryTestCaseEffort libraryTestCaseEffortUpdate = librartCaseEffortService.updateLibraryTestCaseEffort(id,
					libraryTestCaseEffort);
			if (libraryTestCaseEffortUpdate != null) {
				message = String.format("Test effort: %s updated successfully", libraryTestCaseEffortDTO.getId());
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
			} else {
				log.error("Test effort: {} could not be updated", id);
				message = String.format("Test effort: %s could not be updated", id);
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseMessage(message));
			}

		} catch (Exception e) {
			log.error("Could not update the test effort", e);
			message = String.format("Could not update the test effort: %s", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

		}
	}

	@GetMapping("/{libraryTestCaseId}")
	public List<LibraryTestCaseEffortDTO> getLibraryTestCaseEffortListByLibraryTestCaseId(
			@PathVariable(name = "libraryTestCaseId") Long libraryTestCaseId) {
		List<LibraryTestCaseEffortDTO> libraryTestCaseEffortDtoList = new ArrayList<>();
		List<LibraryTestCaseEffort> libraryTestCaseEffortList = librartCaseEffortService
				.getLibraryTestCaseEffortByLibraryTestCaseId(libraryTestCaseId);
		for (LibraryTestCaseEffort testEffort : libraryTestCaseEffortList) {
			libraryTestCaseEffortDtoList.add(mapper.mapToDTO(testEffort));
		}
		return libraryTestCaseEffortDtoList;
	}

	@GetMapping("/last-library-test-case-effort/{libraryTestCaseId}")
	public LibraryTestCaseEffortDTO getLastUnCompletedTestEffortByTestRunId(
			@PathVariable(name = "libraryTestCaseId") Long libraryTestCaseId) {
		LibraryTestCaseEffort libraryTestCaseEffort = librartCaseEffortService
				.getLastLibraryTestCaseEffortByLibraryTestCaseId(libraryTestCaseId);
		return mapper.mapToDTO(libraryTestCaseEffort);
	}

	@GetMapping("/totalLibraryTestCaseByTestCaseLibraryId/{libraryTestCaseId}")
	public Long getTotalTestEffortByTestId(@PathVariable(name = "libraryTestCaseId") Long libraryTestCaseId) {
		return librartCaseEffortService.getTotalTestEffortByLibraryTestCaseEffortId(libraryTestCaseId);
	}
}
