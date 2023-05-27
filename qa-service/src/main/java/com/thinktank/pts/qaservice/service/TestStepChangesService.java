package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.model.TestStepChanges;

public interface TestStepChangesService {

	void addTestStepChanges(TestStepChanges testStepChanges);

	TestStepChanges updateTestStepChanges(TestStepChanges testStepChanges);

	Boolean deleteTestStepChanges(Long id);

	TestStepChanges getTestStepChangesByTestStepLibraryIdAndTestCaseChangeRequestId(Long testStepLibraryId,
			int testCaseChangeRequestId);
	
	List<TestStepChanges> findByTestCaseLibraryId(Long id);
	
	void deleteAllTestStepChanges(List<TestStepChanges> testStepChanges);
	
}
