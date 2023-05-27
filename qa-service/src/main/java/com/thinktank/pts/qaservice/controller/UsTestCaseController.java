package com.thinktank.pts.qaservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.thinktank.pts.qaservice.dto.TestCaseInputDTO;
import com.thinktank.pts.qaservice.dto.UsTestCaseDTO;
import com.thinktank.pts.qaservice.enums.TestCaseState;
import com.thinktank.pts.qaservice.mapper.UsTestCaseMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.Folder;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.UsTestCase;
import com.thinktank.pts.qaservice.service.FolderService;
import com.thinktank.pts.qaservice.service.TestCaseLibraryService;
import com.thinktank.pts.qaservice.service.TestService;
import com.thinktank.pts.qaservice.service.UsTestCaseService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "usTestCase", description = "API for CRUD operations on usTestCases.")
@Transactional
public class UsTestCaseController {

	@Autowired
	private UsTestCaseService usTestCaseService;

	@Autowired
	private TestCaseLibraryService testCaseLibraryService;

	@Autowired
	private FolderService folderService;

	@Autowired
	private TestService testService;

	private UsTestCaseMapper mapper = new UsTestCaseMapper();

	@GetMapping("/usTestCasesByUserStoryId/{userStoryId}")
	public List<UsTestCaseDTO> getTestCaseListByUserStoryId(@PathVariable(name = "userStoryId") long userStoryId) {
		List<UsTestCaseDTO> testCaseDtoList = new ArrayList<>();
		List<UsTestCase> testCaseList = usTestCaseService.getTestCaseListByUserStoryId(userStoryId);

		for (UsTestCase testcase : testCaseList) {
			Long totalTest = 0l;
			if (testcase.getTestCaseLibraryId() != null) {
				totalTest = testService.countTestsByUserStoryIdAndTestCaseLibraryTestCaseLibraryId(
					userStoryId, testcase.getTestCaseLibraryId());
			}
				UsTestCaseDTO usTestCaseDTO=mapper.mapToDTO(testcase);
			usTestCaseDTO.setTotalTestResult(Math.toIntExact(totalTest));
			testCaseDtoList.add(usTestCaseDTO);
		}
		return testCaseDtoList;
	}

	@GetMapping("/usTestCasesBySprintId/{sprintId}")
	public List<UsTestCaseDTO> getTestCaseListBySprintId(@PathVariable(name = "sprintId") long sprintId) {
		List<UsTestCaseDTO> testCaseDtoList = new ArrayList<>();
		List<UsTestCase> testCaseList = usTestCaseService.getTestCaseListBySprintId(sprintId);
		for (UsTestCase testcase : testCaseList) {
			testCaseDtoList.add(mapper.mapToDTO(testcase));
		}
		return testCaseDtoList;
	}

	@GetMapping("/filterByUsTestCaseState/{sprintId}/{state}")
	public int filterWithState(@PathVariable(name = "sprintId") long sprintId,
			@PathVariable(name = "state") TestCaseState state) {
		List<UsTestCaseDTO> testCaseDtoList = new ArrayList<>();
		List<UsTestCase> testCaseList = usTestCaseService.filterWithSprintIdAndState(sprintId, state);
		for (UsTestCase testcase : testCaseList) {
			testCaseDtoList.add(mapper.mapToDTO(testcase));
		}
		return testCaseDtoList.size();
	}

	@GetMapping("/filterUsTestCaseWithProductIdAndState/{productId}/{state}")
	public int filterTestCaseWithProductIdAndState(@PathVariable(name = "productId") long sprintId,
			@PathVariable(name = "state") TestCaseState state) {
		List<UsTestCaseDTO> testCaseDtoList = new ArrayList<>();
		List<UsTestCase> testCaseList = usTestCaseService.filterWithSprintIdAndState(sprintId, state);
		for (UsTestCase testcase : testCaseList) {
			testCaseDtoList.add(mapper.mapToDTO(testcase));
		}
		return testCaseDtoList.size();
	}

	@GetMapping("/AllUsTestCases")
	public List<UsTestCaseDTO> getAllTestCases() {

		List<UsTestCaseDTO> testCaseDtoList = new ArrayList<>();
		List<UsTestCase> testCaseList = usTestCaseService.getAllTestCase();
		for (UsTestCase testcase : testCaseList) {
			testCaseDtoList.add(mapper.mapToDTO(testcase));
		}
		return testCaseDtoList;
	}

	@GetMapping("/usTestCase/{id}")
	public UsTestCaseDTO getTestCaseById(@PathVariable(name = "id") long id) {

		UsTestCaseDTO testCaseDto = new UsTestCaseDTO();
		UsTestCase testCase = usTestCaseService.getUsTestCaseById(id);
		testCaseDto = mapper.mapToDTO(testCase);
		return testCaseDto;
	}

	@GetMapping("/usTestCase/{testCaseLibraryId}/{userStoryId}")
	public UsTestCaseDTO getUsTestCaseByTestCaseLibraryIdAndUserStoryId(
			@PathVariable(name = "testCaseLibraryId") Long testCaseLibraryId,
			@PathVariable(name = "userStoryId") Long userStoryId) {

		UsTestCaseDTO testCaseDto = new UsTestCaseDTO();
		UsTestCase testCase = usTestCaseService.getUsTestCaseByTestCaseLibraryIdAndUserStoryId(testCaseLibraryId,
				userStoryId);
		testCaseDto = mapper.mapToDTO(testCase);
		return testCaseDto;
	}

	@PostMapping("/addUsTestCase")
	public ResponseEntity<Long> addTestCase(@RequestBody UsTestCaseDTO testCaseDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		Long idTestCase = null;
		try {
			UsTestCase testCase = mapper.mapToEntity(testCaseDTO);
			if (testCaseDTO.getFolder() != null) {
				Optional<Folder> optFolder = folderService.getFolder(testCaseDTO.getFolder().getFolderId());
				if (optFolder.isPresent()) {
					testCase.setFolder(optFolder.get());
				}
			}
			testCase.setState(TestCaseState.CREATED);
			usTestCaseService.addTestCase(testCase);

			idTestCase = (long) testCase.getUsTestCaseId();
			return ResponseEntity.status(HttpStatus.CREATED).body(idTestCase);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(idTestCase);
		}
	}

	@PutMapping("/editUsTestCase/{id}")
	public ResponseEntity<ResponseMessage> updateTestCase(@PathVariable(name = "id") long id,
			@RequestBody UsTestCaseDTO testCaseDTO, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			UsTestCase testCase = mapper.mapToEntity(testCaseDTO);
			UsTestCase t = usTestCaseService.updateTestCase(id, testCase);
			if (t != null) {
				message = String.format("Test case: %s  updated successfully", id);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = String.format("Could not update the test case: %s!", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@DeleteMapping("/deleteUsTestCase/{id}")
	public ResponseEntity<ResponseMessage> deleteTestCase(@PathVariable(name = "id") long id) {
		String message = "";
		try {
			if (usTestCaseService.deleteTestCase(id)) {
				message = "Test case: " + id + " deleted successfully";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} else {
				message = "Test case: " + id + " can't be deleted";
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));

			}

		} catch (Exception e) {
			message = "Could not delete the test case: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	/**
	 * deleteUsTestCaseByTestCaseLibraryIdAndUserStoryId: Delete the usTestCase by the testCaseLibraryId and userStoryId
	 * 
	 * @param testCaseLibraryId
	 *            : testCaseLibraryId property
	 * @param userStoryId
	 *            : userStoryId Property
	 * @return ResponseEntity<ResponseMessage>
	 */
	@DeleteMapping("/deleteUsTestCaseByIdTcAndIdUs/{testCaseLibraryId}/{userStoryId}")
	public ResponseEntity<ResponseMessage> deleteUsTestCaseByTestCaseLibraryIdAndUserStoryId(
			@PathVariable(name = "testCaseLibraryId") Long testCaseLibraryId,
			@PathVariable(name = "userStoryId") Long userStoryId) {
		String message = "";
		try {
			if (usTestCaseService.deleteUsTestCaseByTestCaseLibraryIdAndUserStoryId(testCaseLibraryId, userStoryId)) {
				message = "Test case with TestCAseId and UserStoryId : " + testCaseLibraryId + "," + userStoryId
						+ " deleted successfully";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} else {
				message = "Test case with TestCAseId and UserStoryId : " + testCaseLibraryId + "," + userStoryId
						+ " can't be deleted";
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));

			}

		} catch (Exception e) {
			message = "Could not delete the test case: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@PostMapping("/copyUsTestCase")
	public ResponseEntity<ResponseMessage> copyTestCase(@RequestBody TestCaseInputDTO testCaseInputDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";

		TestCaseLibrary testCaseLibrary = testCaseLibraryService
				.getTestCaseLibraryById(testCaseInputDTO.getTestCaseLibraryId());
		UsTestCase copiedTestCase = mapper.mapTestCaseLibraryToTestCase(testCaseLibrary, testCaseInputDTO);
		usTestCaseService.copyTestCase(copiedTestCase);

		message = "Test case successfully attached";
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
	}

	@PostMapping("/usTestCases/{testCaseLibraryId}/cut")
	public ResponseEntity<UsTestCaseDTO> cutTestCase(@PathVariable(name = "testCaseId") long testCaseId) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(mapper.mapToDTO(usTestCaseService.cutTestCase(testCaseId)));
	}

	@GetMapping("/testCasesByUserStoryList/{userStoryListId}")
	public List<UsTestCaseDTO> getTestCasesByUserStoryList(@PathVariable List<Long> userStoryListId) {
		List<UsTestCase> testCaseList = new ArrayList<>();
		List<UsTestCaseDTO> testCaseListDto = new ArrayList<>();
		testCaseList.addAll(usTestCaseService.getTestCaseByUserStoryId(userStoryListId));
		for (UsTestCase testCaseListL : testCaseList) {
			testCaseListDto.add(mapper.mapToDTO(testCaseListL));
		}
		return testCaseListDto;
	}

}