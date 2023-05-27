package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.enums.TestStepState;
import com.thinktank.pts.qaservice.model.TestStep;

public interface TestStepService {

	List<TestStep> getTestStepsByTestCaseLibraryId(Long id);

	TestStep getTestStepById(Long id);

	Boolean addTestStep(TestStep testStep);

	Boolean addTestStepList(List<TestStep> testSteps);

	TestStep updateTestStep(TestStep testStep);

	Boolean deleteTestStep(Long id);

	TestStep updateTestStepState(Long id, TestStepState state);

	Boolean deleteTestStepList(List<TestStep> testSteps);

	List<TestStep> getTestStepByTestId(Long testId);
	
	List<TestStep> findByTestCaseLibraryId(Long id);
}
