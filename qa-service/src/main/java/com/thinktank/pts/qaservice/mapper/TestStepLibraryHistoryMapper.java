package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.TestStepLibraryHistoryDTO;
import com.thinktank.pts.qaservice.model.TestStepLibrary;
import com.thinktank.pts.qaservice.model.TestStepLibraryHistory;

public class TestStepLibraryHistoryMapper {

	public TestStepLibraryHistory mapTestStepLibraryToTestStepLibraryHistory(TestStepLibrary tsl) {

		TestStepLibraryHistory result = null;
		if (tsl != null) {
			result = new TestStepLibraryHistory();
			result.setTestStepLibraryId(tsl.getId());
			result.setStepDescription(tsl.getStepDescription());
			result.setExpectedResult(tsl.getExpectedResult());
			result.setStepOrder(tsl.getStepOrder());
		}

		return result;
	}

	public TestStepLibraryHistory mapToEntity(TestStepLibraryHistoryDTO dto) {

		TestStepLibraryHistory result = null;
		if (dto != null) {
			result = new TestStepLibraryHistory();
			result.setId(dto.getId());
			result.setTestStepLibraryId(dto.getTestStepLibraryId());
			result.setStepDescription(dto.getStepDescription());
			result.setExpectedResult(dto.getExpectedResult());
			result.setStepOrder(dto.getStepOrder());

		}
		return result;
	}

	public TestStepLibraryHistoryDTO mapToDTO(TestStepLibraryHistory entity) {

		TestStepLibraryHistoryDTO result = null;
		if (entity != null) {
			result = new TestStepLibraryHistoryDTO();
			result.setId(entity.getId());
			result.setTestStepLibraryId(entity.getTestStepLibraryId());
			result.setStepDescription(entity.getStepDescription());
			result.setExpectedResult(entity.getExpectedResult());
			result.setStepOrder(entity.getStepOrder());
			if (entity.getTestCaseLibraryHistory() != null) {
				result.setTestCaseLibraryHistoryId(entity.getTestCaseLibraryHistory().getId());
			}

		}
		return result;
	}

}
