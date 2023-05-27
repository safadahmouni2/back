package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.TestStepLibraryDTO;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestStepLibrary;

public class TestStepLibraryMapper {

	public TestStepLibrary mapToEntityWithTestCase(TestStepLibraryDTO dto, TestCaseLibrary parent) {

		TestStepLibrary result = null;
		if (dto != null) {
			result = new TestStepLibrary();
			result.setExpectedResult(dto.getExpectedResult());
			result.setStepDescription(dto.getStepDescription());
			result.setTestStepState(dto.getTestStepState());
			result.setStepOrder(dto.getStepOrder());
			result.setTestCaseLibrary(parent);
		}
		return result;
	}

	public TestStepLibrary mapToEntity(TestStepLibraryDTO dto) {

		TestStepLibrary result = null;
		if (dto != null) {
			result = new TestStepLibrary();
			result.setId(dto.getId());
			result.setExpectedResult(dto.getExpectedResult());
			result.setStepDescription(dto.getStepDescription());
			result.setTestStepState(dto.getTestStepState());
			result.setStepOrder(dto.getStepOrder());
		}
		return result;
	}

	public TestStepLibraryDTO mapToDTO(TestStepLibrary entity) {

		TestStepLibraryDTO result = null;
		if (entity != null) {
			result = new TestStepLibraryDTO();
			result.setId(entity.getId());
			result.setExpectedResult(entity.getExpectedResult());
			result.setStepDescription(entity.getStepDescription());
			result.setTestStepState(entity.getTestStepState());
			result.setStepOrder(entity.getStepOrder());
			if (entity.getTestCaseLibrary() != null) {
				result.setTestCaseLibraryId(entity.getTestCaseLibrary().getTestCaseLibraryId());
			}
		}
		return result;
	}

	public void patch(TestStepLibrary from, TestStepLibrary to) {

		if (from != null && to != null) {
			to.setStepDescription(from.getStepDescription());
			to.setExpectedResult(from.getExpectedResult());
			to.setTestStepState(from.getTestStepState());
			to.setStepOrder(from.getStepOrder());
		}
	}

}
