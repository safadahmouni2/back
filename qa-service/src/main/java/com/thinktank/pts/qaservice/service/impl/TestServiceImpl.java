package com.thinktank.pts.qaservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.dto.TestDTO;
import com.thinktank.pts.qaservice.enums.TestRunState;
import com.thinktank.pts.qaservice.enums.TestState;
import com.thinktank.pts.qaservice.mapper.TestMapper;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestRun;
import com.thinktank.pts.qaservice.repository.TestRepository;
import com.thinktank.pts.qaservice.service.TestCaseLibraryService;
import com.thinktank.pts.qaservice.service.TestRunService;
import com.thinktank.pts.qaservice.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	TestMapper mapper = new TestMapper();
	@Autowired
	private TestCaseLibraryService testCaseLibraryService;

	@Autowired
	private TestRunService testRunService;

	private TestMapper testMapper = new TestMapper();

	@Override
	public List<Test> getTestsByTestRunId(Long testRunId) {
		return testRepository.findByTestRunTestRunId(testRunId);
	}

	@Override
	public Test getTestById(Long id) {
		return testRepository.getTestByTestId(id);
	}

	@Override
	public Test addTest(Test test) {
		Test result = null;
		if (test != null) {
			result = testRepository.save(test);
		}
		return result;
	}

	@Override
	public boolean deleteTest(Long id) {
		boolean result = false;
		Test test = getTestById(id);
		List<Test> tests = new ArrayList<>();
		tests.add(test);
		if (test != null) {
			testRepository.deleteInBatch(tests);
			result = true;
		}
		return result;
	}

	@Override
	public boolean updateTestState(Long id, TestState state) {
		boolean result = false;
		Test test = getTestById(id);
		TestRun testRun = testRunService.getTestRunById(test.getTestRun().getTestRunId());
		if (testRun.getState() == TestRunState.COMPLETED) {
			return result;
		}
		if (test != null) {
			if (state == null) {
				test.setTestState(null);
			} else {
				test.setTestState(state);
			}
			testRepository.save(test);
			result = true;
		}
		return result;
	}

	@Override
	public Integer countByTestRunIdAndState(Long testRunId, TestState state) {
		return testRepository.countByTestRunTestRunIdAndTestState(testRunId, state);
	}

	@Override
	public List<Test> getTestByUserStoryId(Long userStoryId) {
		return testRepository.getTestByUserStoryId(userStoryId);
	}

	@Override
	public List<Test> getTestsByUserStoryIdAndTestCaseLibraryId(Long userStoryId, Long testCaseLibraryId) {
		return testRepository.getTestByUserStoryIdAndTestCaseLibraryTestCaseLibraryId(userStoryId, testCaseLibraryId);

	}

	@Override
	public boolean updateTest(TestDTO testDTO) {
		boolean updated = false;
		Test testEntity = new Test();
		Test testById = getTestById(testDTO.getTestId());
		TestRun testRun = testRunService.getTestRunById(testById.getTestRun().getTestRunId());
		if (testRun.getState() == TestRunState.COMPLETED) {
			return updated;
		}

		if (testDTO != null && testDTO.getTestId() != null && testById.getTestId().equals(testDTO.getTestId())) {
			testEntity = testMapper.mapToEntity(testDTO);
			if (testDTO.getTestCaseLibraryId() != null) {
				testDTO.setTestCaseLibraryId(testDTO.getTestCaseLibraryId());
				TestCaseLibrary testCaseLibrary = testCaseLibraryService
						.getTestCaseLibraryById(testDTO.getTestCaseLibraryId());
			} else {
				testDTO.setTestCaseLibraryId(testById.getTestCaseLibrary().getTestCaseLibraryId());
			}
			testEntity
					.setTestCaseLibrary(testCaseLibraryService.getTestCaseLibraryById(testDTO.getTestCaseLibraryId()));
			if (testById.getTestRun() != null && testById.getTestRun().getTestRunId() != null) {

				testEntity.setTestRun(testRun);
			}
		}
		testMapper.patch(testEntity, testById);
		Test t = testRepository.save(testById);
		if (t != null) {
			updated = true;
		}
		return updated;
	}

	@Override
	public List<Test> findByTestCaseLibraryId(Long id) {
		return testRepository.findByTestCaseLibraryTestCaseLibraryId(id);
	}
	@Override
	public Long countTestsByUserStoryIdAndTestCaseLibraryTestCaseLibraryId(Long usId,Long tcId){
		return testRepository.countByUserStoryIdAndTestCaseLibraryTestCaseLibraryId(usId, tcId);
	}
}
