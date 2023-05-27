package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.TestCaseChangeRequestDTO;
import com.thinktank.pts.qaservice.model.TestCaseChangeRequest;

public class TestCaseChangeRequestMapper {

	public TestCaseChangeRequest mapToEntity(TestCaseChangeRequestDTO dto) {

		TestCaseChangeRequest result = null;

		if (dto != null) {
			result = new TestCaseChangeRequest();
			result.setId(dto.getId());
			result.setState(dto.getState());
			result.setTestId(dto.getTestId());
			result.setProductId(dto.getProductId());
			result.setSprintId(dto.getSprintId());
			result.setTestCaseId(dto.getTestCaseId());

		}
		return result;
	}

	public TestCaseChangeRequestDTO mapToDTO(TestCaseChangeRequest entity) {

		TestCaseChangeRequestDTO result = null;

		if (entity != null) {
			result = new TestCaseChangeRequestDTO();
			result.setId(entity.getId());
			result.setState(entity.getState());
			result.setTestId(entity.getTestId());
			result.setProductId(entity.getProductId());
			result.setSprintId(entity.getSprintId());
			result.setTestCaseId(entity.getTestCaseId());
			result.setModified(entity.getModified());
			result.setCreator(entity.getCreator());
			result.setCreated(entity.getCreated());
			result.setModifier(entity.getModifier());
			result.setModified(entity.getModified());
		}
		return result;
	}

	public void patch(TestCaseChangeRequest from, TestCaseChangeRequest to) {

		if (from != null && to != null) {
			to.setId(from.getId());
			to.setState(from.getState());
			to.setTestId(from.getTestId());
			to.setProductId(from.getProductId());
			to.setSprintId(from.getSprintId());
			to.setTestCaseId(from.getTestCaseId());
		}
	}

}
