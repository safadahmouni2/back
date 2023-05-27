package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.enums.TestStepState;
import com.thinktank.pts.qaservice.model.UsTestStep;

public interface UsTestStepService {

	List<UsTestStep> getUsTestStepsByTestCaseId(Long id);

	UsTestStep getUsTestStepById(Long id);

	Boolean addUsTestStep(UsTestStep testStep);

	Boolean addUsTestStepList(List<UsTestStep> testSteps);

	UsTestStep updateUsTestStep(UsTestStep testStep);

	Boolean deleteUsTestStep(Long id);

	Boolean updateUsTestStepState(Long id, TestStepState state);

	Boolean deleteUsTestStepList(List<UsTestStep> testSteps);
}
