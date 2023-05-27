package com.thinktank.pts.qaservice.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.TestDTO;
import com.thinktank.pts.qaservice.dto.TestRunDTO;
import com.thinktank.pts.qaservice.enums.TestState;
import com.thinktank.pts.qaservice.mapper.TestMapper;
import com.thinktank.pts.qaservice.mapper.TestRunMapper;
import com.thinktank.pts.qaservice.mapper.TestSuiteLibraryMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestRun;
import com.thinktank.pts.qaservice.model.TestStep;
import com.thinktank.pts.qaservice.model.TestSuiteLibrary;
import com.thinktank.pts.qaservice.model.UsTestCase;
import com.thinktank.pts.qaservice.service.TestCaseLibraryService;
import com.thinktank.pts.qaservice.service.TestRunService;
import com.thinktank.pts.qaservice.service.TestService;
import com.thinktank.pts.qaservice.service.TestSuiteLibraryService;
import com.thinktank.pts.qaservice.service.UsTestCaseService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Tag(name = "testruns", description = "API for CRUD operations on testruns.")
@Slf4j
@Transactional
public class TestRunController {

	@Autowired
	private TestSuiteLibraryService testSuiteLibraryService;

	@Autowired
	private TestRunService testRunService;

	@Autowired
	private TestCaseLibraryService testCaseLibraryService;

	@Autowired
	private TestService testService;

	@Autowired
	private UsTestCaseService usTestCaseService;

	private TestSuiteLibraryMapper testSuiteLibraryMapper = new TestSuiteLibraryMapper();
	private TestRunMapper mapper = new TestRunMapper();
	private TestMapper testMapper = new TestMapper();

	@GetMapping("/testRuns/{installId}")
	public List<TestRunDTO> getTestRunListByInstallId(@PathVariable(name = "installId") Long installId) {

		List<TestRunDTO> testRunDtoList = new ArrayList<>();
		List<TestRun> testRunList = testRunService.getTestRunListByInstallId(installId);
		for (TestRun testRun : testRunList) {
			testRunDtoList.add(mapper.mapToDTO(testRun));
		}
		return testRunDtoList;
	}

	@GetMapping("/testRunsByInstall/{installed_id}")
	public List<TestRunDTO> getTestRunListByIdInstall(@PathVariable(name = "installed_id") Long installed_id) {

		List<TestRunDTO> testRunDtoList = new ArrayList<>();
		List<TestRun> testRunList = testRunService.getTestRunsByIdInstall(installed_id);
		for (TestRun testRun : testRunList) {

			Integer totalTestOk = testService.countByTestRunIdAndState(testRun.getTestRunId(), TestState.OK);
			Integer totalTestNotOk = testService.countByTestRunIdAndState(testRun.getTestRunId(), TestState.NOT_OK);
			TestRunDTO testRunDto = mapper.mapToDTOWithStatistic(testRun, totalTestOk, totalTestNotOk);

			testRunDtoList.add(testRunDto);
		}
		return testRunDtoList;
	}

	@GetMapping("/test-runs/count-by-install/{installId}")
	public long getCountTesttRunsByIdInstall(@PathVariable(name = "installId") Long installId) {
		return testRunService.countByInstallId(installId);
	}

	@GetMapping("/testRun/{id}")
	public TestRunDTO getTestRunById(@PathVariable(name = "id") Long id) {

		TestRun testRun = testRunService.getTestRunById(id);
		Integer totalTestOk = testService.countByTestRunIdAndState(testRun.getTestRunId(), TestState.OK);
		Integer totalTestNotOk = testService.countByTestRunIdAndState(testRun.getTestRunId(), TestState.NOT_OK);
		TestRunDTO testRunDto = mapper.mapToDTOWithStatistic(testRun, totalTestOk, totalTestNotOk);

		return testRunDto;
	}

	@PostMapping("/addTestRun/{installed_id}/{productId}/{createTestSuite}")
	public ResponseEntity<ResponseMessage> addTestRun(@RequestBody TestRunDTO testRunDTO,
			@PathVariable(name = "installed_id") Long installed_id, @PathVariable(name = "productId") Long productId,
			@PathVariable(required = false) Boolean createTestSuite,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());

		StringBuilder message = new StringBuilder();
		BigDecimal estimatedEffort = BigDecimal.ZERO;
		List<TestCaseLibrary> testCaseLibraryList = new ArrayList<TestCaseLibrary>();
		List<UsTestCase> usTestCaseList = new ArrayList<UsTestCase>();
		try {
			Boolean testRunDtoTestsIsNotNull = false;
			List<TestDTO> testDtoList = new ArrayList<>();
			if (testRunDTO.getTests() != null) {
				testDtoList = testRunDTO.getTests();
				testRunDTO.setTests(null);
				testRunDtoTestsIsNotNull = true;
			}
			// calculate estimated Time
			if (testRunDtoTestsIsNotNull) {
				for (TestDTO testDto : testDtoList) {
					if (testDto.getTestCaseLibraryId() != null) {
						TestCaseLibrary testCaseLibrary = testCaseLibraryService
								.getTestCaseLibraryById(testDto.getTestCaseLibraryId());
						if (testCaseLibrary.getExecutionEstimationTime() != null) {
							estimatedEffort = estimatedEffort.add(testCaseLibrary.getExecutionEstimationTime());
						}

					}
				}
			}
			// TestRun testRun = mapper.mapToEntity(testRunDTO);
			TestRun testRun = mapper.mapToEntityWithEstimatedEffort(testRunDTO, estimatedEffort);

			TestRun tr = testRunService.addTestRun(testRun);

			if (testRunDtoTestsIsNotNull) {
				if (testRunDTO.getFlag().equals("libraryTC")) {
					for (TestDTO testDto : testDtoList) {
						TestCaseLibrary testCaseLibrary = testCaseLibraryService
								.getTestCaseLibraryById(testDto.getTestCaseLibraryId());
						if (!testCaseLibraryList.contains(testCaseLibrary)) {
							testCaseLibraryList.add(testCaseLibrary);
						}
						Test test = testMapper.mapToEntity(testCaseLibrary);
						test.setTestRun(tr);
						test.setTestCaseLibrary(testCaseLibrary);
						test.setUserStoryId(0L);
						test.setOriginSprintId(testRunDTO.getOriginSprintId());
						if (test.getTestSteps() != null) {
							List<TestStep> testStepList = test.getTestSteps();
							for (TestStep testStep : testStepList) {
								testStep.setTestCaseLibrary(testCaseLibrary);
							}
						}
						test = testService.addTest(test);
					}
				} else {
					for (TestDTO testDto : testDtoList) {
						UsTestCase usTestCase = usTestCaseService.getUsTestCaseByTestCaseLibraryIdAndUserStoryId(
								testDto.getTestCaseLibraryId(), testDto.getUserStoryId());
						usTestCaseList.add(usTestCase);
						Test test = testMapper.mapToEntity(usTestCase);
						// MG: always get sprint from select one
						test.setOriginSprintId(testRunDTO.getOriginSprintId());
						test.setTestRun(tr);
						TestCaseLibrary testCaseLibrary = testCaseLibraryService
								.getTestCaseLibraryById(testDto.getTestCaseLibraryId());
						if (!testCaseLibraryList.contains(testCaseLibrary)) {
							testCaseLibraryList.add(testCaseLibrary);
						}
						test.setTestCaseLibrary(testCaseLibrary);
						test.setUserStoryId(usTestCase.getUserStoryId());
						if (test.getTestSteps() != null) {
							List<TestStep> testStepList = test.getTestSteps();
							for (TestStep testStep : testStepList) {
								testStep.setTestCaseLibrary(testCaseLibrary);
								testStep.getTest().setTestCaseLibrary(testCaseLibrary);
							}
						}
						test = testService.addTest(test);
					}
				}
			}
			message.append("Test run: ");
			message.append(testRun.getTestRunId());
			if (createTestSuite != null && createTestSuite) {
				TestSuiteLibrary testSuiteLibrary = testSuiteLibraryMapper.mapTestRunToTestSuiteLibrary(testRun,
						productId, testCaseLibraryList);
				testSuiteLibraryService.addTestSuiteLibrary(testSuiteLibrary);
				message.append(" / Test suite: ");
				message.append(testSuiteLibrary.getTestSuiteLibraryId());
			}

			message.append(" created successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message.toString()));

		} catch (Exception e) {
			log.error("Fail to add TestRun", e);
			message.append("Could not create the test run: " + e + "!");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message.toString()));
		}
	}

	@GetMapping(value = "/testRuns/install/{envId}")
	public List<Long> getTestRunByEnvGroupByInstall(@PathVariable(name = "envId") Long envId) {
		return testRunService.getAllInstallsByEnvId(envId);
	}

	@GetMapping(value = "/testRuns/environment/{envId}")
	public List<TestRunDTO> getTestRunsByEnvId(@PathVariable(name = "envId") Long envId) {
		List<TestRun> testRuns = testRunService.getTestRunsByEnvId(envId);
		List<TestRunDTO> testRunDTOList = new ArrayList<>();
		for (TestRun testRun : testRuns) {
			testRunDTOList.add(mapper.mapToDTO(testRun));
		}
		return testRunDTOList;

	}

}
