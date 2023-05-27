package com.thinktank.pts.qaservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.mapper.TestStepLibraryHistoryMapper;
import com.thinktank.pts.qaservice.model.TestStepLibraryHistory;
import com.thinktank.pts.qaservice.repository.TestStepLibraryHistoryRepository;
import com.thinktank.pts.qaservice.service.TestStepLibraryHistoryService;

@Service
public class TestStepLibraryHistoryServiceImpl implements TestStepLibraryHistoryService {
	@Autowired
	private TestStepLibraryHistoryRepository testStepLibraryHistoryRepository;

	TestStepLibraryHistoryMapper mapper = new TestStepLibraryHistoryMapper();

	@Override
	public List<TestStepLibraryHistory> getTestStepLibraryHistoryByTestCaseLibraryHistoryId(Long testCaseId) {
		return testStepLibraryHistoryRepository.getTestStepLibraryHistoryListByTestCaseLibraryHistoryId(testCaseId);
	}

	@Override
	public TestStepLibraryHistory getTestStepLibraryHistoryById(Long id) {
		return testStepLibraryHistoryRepository.getTestStepLibraryHistoryById(id);
	}

	@Override
	public Boolean addTestStepLibraryHistory(TestStepLibraryHistory testStep) {
		Boolean result = false;
		if (testStep != null) {
			testStepLibraryHistoryRepository.save(testStep);
			result = true;
		}
		return result;
	}

	@Override
	public Boolean addTestStepLibraryHistoryList(List<TestStepLibraryHistory> testSteps) {
		Boolean result = false;
		if (testSteps != null) {
			testStepLibraryHistoryRepository.saveAll(testSteps);
			result = true;
		}
		return result;

	}

	@Override
	public List<TestStepLibraryHistory> findByTestCaseLibraryHistoryTestCaseLibraryHistoryId(Long id) {
		return testStepLibraryHistoryRepository.getTestStepLibraryHistoryListByTestCaseLibraryHistoryId(id);
	}

	@Override
	public void deleteAllTestStepLibraryHistoryByLibraryTestCase(
			List<TestStepLibraryHistory> testStepLibraryHistories) {
		testStepLibraryHistoryRepository.deleteAll(testStepLibraryHistories);
	}

}
