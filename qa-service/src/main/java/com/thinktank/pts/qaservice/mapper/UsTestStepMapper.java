package com.thinktank.pts.qaservice.mapper;

import java.util.ArrayList;
import java.util.List;

import com.thinktank.pts.qaservice.dto.UsTestStepDTO;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestStep;
import com.thinktank.pts.qaservice.model.TestStepLibrary;
import com.thinktank.pts.qaservice.model.UsTestCase;
import com.thinktank.pts.qaservice.model.UsTestStep;

public class UsTestStepMapper {

	/**
	 * mapTestStepLibraryToTestStep : map from TestStepLibrary to TestStep
	 * 
	 * @param testStepLibraryList
	 *            : list of testStepLibrary
	 * @return
	 */
	public List<UsTestStep> mapTestStepLibraryToTestStep(List<TestStepLibrary> testStepLibraryList,
			UsTestCase testCase) {
		List<UsTestStep> testStepList = new ArrayList<UsTestStep>();
		for (TestStepLibrary testStepLibrary : testStepLibraryList) {
			UsTestStep testStep = new UsTestStep();
			testStep.setId(testStepLibrary.getId());
			testStep.setStepDescription(testStepLibrary.getStepDescription());
			testStep.setExpectedResult(testStepLibrary.getExpectedResult());
			testStep.setTestStepState(testStepLibrary.getTestStepState());
			testStep.setStepOrder(testStepLibrary.getStepOrder());
			testStep.setUsTestCase(testStep.getUsTestCase());
			testStep.setUsTestCase(testCase);
			testStep.setTestStepLibraryId(testStepLibrary.getId());
			testStepList.add(testStep);
		}
		return testStepList;
	}

	public UsTestStep mapToEntity(UsTestStepDTO dto) {

		UsTestStep result = null;
		if (dto != null) {
			result = new UsTestStep();
			result.setId(dto.getId());
			result.setExpectedResult(dto.getExpectedResult());
			result.setStepDescription(dto.getStepDescription());
			result.setTestStepState(dto.getTestStepState());
			result.setStepOrder(dto.getStepOrder());
			result.setTestStepLibraryId(dto.getTestStepLibraryId());
		}
		return result;
	}

	public TestStep mapToEntity(UsTestStep dto, Test parent) {

		TestStep result = null;
		if (dto != null) {
			result = new TestStep();
			result.setId(dto.getId());
			result.setExpectedResult(dto.getExpectedResult());
			result.setStepDescription(dto.getStepDescription());
			result.setTestStepState(dto.getTestStepState());
			result.setStepOrder(dto.getStepOrder());
			result.setTestStepLibraryId(dto.getTestStepLibraryId());
			result.setTest(parent);
		}
		return result;
	}

	public UsTestStepDTO mapToDTO(UsTestStep entity) {

		UsTestStepDTO result = null;
		if (entity != null) {
			result = new UsTestStepDTO();
			result.setId(entity.getId());
			result.setExpectedResult(entity.getExpectedResult());
			result.setStepDescription(entity.getStepDescription());
			result.setTestStepState(entity.getTestStepState());
			result.setStepOrder(entity.getStepOrder());
			result.setTestStepLibraryId(entity.getTestStepLibraryId());
			if (entity.getUsTestCase() != null) {
				result.setUsTestCaseId(entity.getUsTestCase().getUsTestCaseId());
			}
		}
		return result;
	}

	public void patch(UsTestStep from, UsTestStep to) {

		if (from != null && to != null) {
			to.setStepDescription(from.getStepDescription());
			to.setExpectedResult(from.getExpectedResult());
			to.setTestStepState(from.getTestStepState());
			to.setStepOrder(from.getStepOrder());
			to.setTestStepLibraryId(from.getTestStepLibraryId());
		}
	}
}
