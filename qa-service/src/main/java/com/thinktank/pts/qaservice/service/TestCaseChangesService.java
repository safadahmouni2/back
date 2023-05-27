package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.model.TestCaseChanges;

public interface TestCaseChangesService {

	void addTestCaseChanges(TestCaseChanges testCaseChanges);

	Boolean deleteTestCaseChanges(Long id);

	TestCaseChanges getTestCaseChangesByTestCaseChangeRequestId(Long testCaseChangeRequestId);

	TestCaseChanges updateTestCaseChanges(TestCaseChanges testCaseChanges);
	
	List<TestCaseChanges> getTestCaseChangesByTestCaseLibrary(Long id);
	
	void deleteAllTestCaseChanges(List<TestCaseChanges> testCaseChanges);

}
