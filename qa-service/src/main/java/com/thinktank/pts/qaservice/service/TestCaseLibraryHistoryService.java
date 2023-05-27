package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.model.TestCaseLibraryHistory;

public interface TestCaseLibraryHistoryService {

	List<TestCaseLibraryHistory> getTestCaseLibraryHistoryByTestCaseLibraryId(long id);

	TestCaseLibraryHistory getTestCaseLibraryHistoryById(long id);

	void addTestCaseLibraryHistory(TestCaseLibraryHistory testCaseLibraryHistory);
	
	void deleteAllTestCaseLibraryHistory(List<TestCaseLibraryHistory> testCaseLibraryHistorys);

}
