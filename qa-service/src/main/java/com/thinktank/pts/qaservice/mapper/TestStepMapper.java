package com.thinktank.pts.qaservice.mapper;

import java.util.ArrayList;
import java.util.List;

import com.thinktank.pts.qaservice.dto.TestStepDTO;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestStep;
import com.thinktank.pts.qaservice.model.TestStepLibrary;

public class TestStepMapper {

	/**
	 * mapTestStepLibraryToTestStep : map from TestStepLibrary to TestStep
	 * 
	 * @param testStepLibraryList
	 *            : list of testStepLibrary
	 * @return
	 */
	public List<TestStep> mapTestStepLibraryToTestStep(List<TestStepLibrary> testStepLibraryList,
			TestCaseLibrary testCaseLibrary) {
		List<TestStep> testStepList = new ArrayList<TestStep>();
		for (TestStepLibrary testStepLibrary : testStepLibraryList) {
			TestStep testStep = new TestStep();
			testStep.setId(testStepLibrary.getId());
			testStep.setStepDescription(testStepLibrary.getStepDescription());
			testStep.setExpectedResult(testStepLibrary.getExpectedResult());
			testStep.setTestStepState(testStepLibrary.getTestStepState());
			testStep.setStepOrder(testStepLibrary.getStepOrder());
			testStep.setTestCaseLibrary(testStep.getTestCaseLibrary());
			testStep.setTestCaseLibrary(testCaseLibrary);
			testStep.setTestStepLibraryId(testStepLibrary.getId());
			testStepList.add(testStep);
		}
		return testStepList;
	}

	public TestStep mapToEntity(TestStepDTO testStepDTO) {

		TestStep result = null;
		if (testStepDTO != null) {
			result = new TestStep();
			result.setId(testStepDTO.getId());
			result.setExpectedResult(testStepDTO.getExpectedResult());
			result.setStepDescription(testStepDTO.getStepDescription());
			result.setTestStepState(testStepDTO.getTestStepState());
			result.setStepOrder(testStepDTO.getStepOrder());
			result.setTestStepLibraryId(testStepDTO.getTestStepLibraryId());
		}
		return result;
	}

	public TestStep mapToEntity(TestStepLibrary testStepLibrary, Test parent) {

		TestStep result = null;
		if (testStepLibrary != null) {
			result = new TestStep();
			// result.setId(testStepLibrary.getId());
			result.setExpectedResult(testStepLibrary.getExpectedResult());
			result.setStepDescription(testStepLibrary.getStepDescription());
			result.setTestStepState(testStepLibrary.getTestStepState());
			result.setStepOrder(testStepLibrary.getStepOrder());
			result.setTestStepLibraryId(testStepLibrary.getId());
			result.setTest(parent);
		}
		return result;
	}

	public TestStepDTO mapToDTO(TestStep entity) {

		TestStepDTO result = null;
		if (entity != null) {
			result = new TestStepDTO();
			result.setId(entity.getId());
			result.setExpectedResult(entity.getExpectedResult());
			result.setStepDescription(entity.getStepDescription());
			result.setTestStepState(entity.getTestStepState());
			result.setStepOrder(entity.getStepOrder());
			result.setTestStepLibraryId(entity.getTestStepLibraryId());
			if (entity.getTestCaseLibrary() != null) {
				result.setUsTestCaseId(entity.getTestCaseLibrary().getTestCaseLibraryId());
			}
			if (entity.getTest() != null) {
				result.setTestId(entity.getTest().getTestId());
			}
		}
		return result;
	}

	public void patch(TestStep from, TestStep to) {

		if (from != null && to != null) {
			to.setStepDescription(from.getStepDescription());
			to.setExpectedResult(from.getExpectedResult());
			to.setTestStepState(from.getTestStepState());
			to.setStepOrder(from.getStepOrder());
			to.setTestStepLibraryId(from.getTestStepLibraryId());
		}
	}
}
