package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.model.TestStepLibraryHistory;

public interface TestStepLibraryHistoryService {

	List<TestStepLibraryHistory> getTestStepLibraryHistoryByTestCaseLibraryHistoryId(Long id);

	TestStepLibraryHistory getTestStepLibraryHistoryById(Long id);

	Boolean addTestStepLibraryHistory(TestStepLibraryHistory testStepLibraryHistory);

	Boolean addTestStepLibraryHistoryList(List<TestStepLibraryHistory> testSteps);
	
	List<TestStepLibraryHistory> findByTestCaseLibraryHistoryTestCaseLibraryHistoryId(Long id);
		
	void deleteAllTestStepLibraryHistoryByLibraryTestCase(List<TestStepLibraryHistory> testStepLibraryHistories);

}
