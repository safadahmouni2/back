package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.TestEffortDTO;
import com.thinktank.pts.qaservice.model.TestEffort;

public class TestEffortMapper {

	public TestEffort mapToEntity(TestEffortDTO dto) {

		TestEffort result = null;

		if (dto != null) {
			result = new TestEffort();
			result.setId(dto.getId());
			result.setStartTime(dto.getStartTime());
			result.setEndTime(dto.getEndTime());
			result.setEffortByLine(dto.getEffortByLine());
			result.setDate(dto.getDate());
		}
		return result;
	}

	public TestEffortDTO mapToDTO(TestEffort entity) {

		TestEffortDTO result = null;

		if (entity != null) {
			result = new TestEffortDTO();
			result.setId(entity.getId());
			result.setStartTime(entity.getStartTime());
			result.setEndTime(entity.getEndTime());
			result.setEffortByLine(entity.getEffortByLine());
			result.setDate(entity.getDate());
			result.setTestId(entity.getTest().getTestId());
			result.setTestRunId(entity.getTestRun().getTestRunId());
		}
		return result;
	}

	public void patch(TestEffort from, TestEffort to) {

		if (from != null && to != null) {
			to.setStartTime(from.getStartTime());
			to.setEndTime(from.getEndTime());
			to.setDate(from.getDate());
			to.setEffortByLine(from.getEffortByLine());
			to.setTest(from.getTest());
		}
	}
}
