package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.TestStepChangesDTO;
import com.thinktank.pts.qaservice.dto.TestStepDTO;
import com.thinktank.pts.qaservice.model.TestCaseChanges;
import com.thinktank.pts.qaservice.model.TestStep;
import com.thinktank.pts.qaservice.model.TestStepChanges;

public class TestStepChangesMapper {

	public TestStepChanges mapToEntity(TestStepChangesDTO dto) {

		TestStepChanges result = null;
		if (dto != null) {
			result = new TestStepChanges();
			result.setId(dto.getId());
			result.setTestCaseChangeRequestId(dto.getTestCaseChangeRequestId());
			result.setTestId(dto.getTestId());
			result.setTestCaseLibraryId(dto.getTestCaseLibraryId());
			result.setTestStepLibraryId(dto.getTestStepLibraryId());
			result.setStepDescription(dto.getStepDescription());
			result.setExpectedResult(dto.getExpectedResult());
			result.setStepOrder(dto.getStepOrder());
		}
		return result;
	}
	// mapTestStepsToEntity

	public TestStepChanges mapTestStepsToEntity(TestStep testStep, Long testCaseChangeRequestId,
			TestCaseChanges parent) {

		TestStepChanges result = null;
		if (testStep != null) {
			result = new TestStepChanges();
			result.setTestCaseChangeRequestId(testCaseChangeRequestId);
			result.setTestId(testStep.getTest().getTestId());
			result.setTestCaseLibraryId(testStep.getTestCaseLibrary().getTestCaseLibraryId());
			result.setTestStepLibraryId(testStep.getTestStepLibraryId());
			result.setStepDescription(testStep.getStepDescription());
			result.setExpectedResult(testStep.getExpectedResult());
			result.setStepOrder(testStep.getStepOrder());
			result.setTestCaseChanges(parent);
		}
		return result;
	}

	public TestStepChanges mapTestStepsDTOToEntity(TestStepDTO testStepDTO, Long testCaseChangeRequestId) {

		TestStepChanges result = null;
		if (testStepDTO != null) {
			result = new TestStepChanges();
			result.setTestCaseChangeRequestId(testCaseChangeRequestId);
			result.setTestId(testStepDTO.getTestId());
			result.setTestCaseLibraryId(testStepDTO.getTestCaseLibraryId());
			result.setTestStepLibraryId(testStepDTO.getId());
			result.setStepDescription(testStepDTO.getStepDescription());
			result.setExpectedResult(testStepDTO.getExpectedResult());
			result.setStepOrder(testStepDTO.getStepOrder());
		}
		return result;
	}

	public TestStepChangesDTO mapToDTO(TestStepChanges entity) {

		TestStepChangesDTO result = null;
		if (entity != null) {
			result = new TestStepChangesDTO();
			result.setId(entity.getId());
			result.setTestCaseChangeRequestId(entity.getTestCaseChangeRequestId());
			result.setTestId(entity.getTestId());
			result.setTestCaseLibraryId(entity.getTestCaseLibraryId());
			result.setTestStepLibraryId(entity.getTestStepLibraryId());
			result.setStepDescription(entity.getStepDescription());
			result.setExpectedResult(entity.getExpectedResult());
			result.setStepOrder(entity.getStepOrder());

		}
		return result;
	}

	public void patch(TestStepChanges from, TestStepChanges to) {

		if (from != null && to != null) {
			to.setTestCaseChangeRequestId(from.getTestCaseChangeRequestId());
			to.setTestId(from.getTestId());
			to.setTestCaseLibraryId(from.getTestCaseLibraryId());
			to.setTestStepLibraryId(from.getTestStepLibraryId());
			to.setStepDescription(from.getStepDescription());
			to.setExpectedResult(from.getExpectedResult());
			to.setStepOrder(from.getStepOrder());

		}
	}

}
