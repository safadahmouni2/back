package com.thinktank.pts.qaservice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.thinktank.pts.qaservice.dto.TestCaseChangesDTO;
import com.thinktank.pts.qaservice.dto.TestStepChangesDTO;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestCaseChanges;
import com.thinktank.pts.qaservice.model.TestStep;
import com.thinktank.pts.qaservice.model.TestStepChanges;

public class TestCaseChangesMapper {

	private TestStepChangesMapper testStepChangesMapper = new TestStepChangesMapper();

	public TestCaseChanges mapToEntity(TestCaseChangesDTO dto) {

		TestCaseChanges result = null;
		if (dto != null) {
			result = new TestCaseChanges();
			result.setId(dto.getId());
			result.setTestCaseChangeRequestId(dto.getTestCaseChangeRequestId());
			result.setTestId(dto.getTestId());
			result.setTestCaseLibraryId(dto.getTestCaseLibraryId());

			result.setShortDescription(dto.getShortDescription());
			result.setDescription(dto.getDescription());
			result.setCategory(dto.getCategory());
			result.setPreCondition(dto.getPreCondition());
			result.setExecutionEstimationTime(dto.getExecutionEstimationTime());
			result.setState(dto.getState());
			result.setTestStepChanges(mapTestStepsChanges(dto.getTestStepChanges()));

		}
		return result;
	}

	public TestCaseChanges mapTestToEntity(TestCaseChangesDTO dto, Test test) {

		TestCaseChanges result = null;
		if (dto != null) {
			result = new TestCaseChanges();
			result.setId(dto.getId());
			result.setTestCaseChangeRequestId(dto.getTestCaseChangeRequestId());
			result.setTestId(test.getTestId());
			result.setTestCaseLibraryId(dto.getTestCaseLibraryId());

			result.setShortDescription(test.getShortDescription());
			result.setDescription(test.getDescription());
			result.setCategory(test.getCategory());
			result.setPreCondition(test.getPreCondition());
			result.setExecutionEstimationTime(test.getExecutionEstimationTime());
			result.setState(dto.getState());
			result.setTestStepChanges(
					mapTestStepsToTestStepsChanges(test.getTestSteps(), dto.getTestCaseChangeRequestId(), result));

		}
		return result;
	}

	public TestCaseChangesDTO mapToDTO(TestCaseChanges entity) {

		TestCaseChangesDTO result = null;
		if (entity != null) {
			result = new TestCaseChangesDTO();
			result.setId(entity.getId());
			result.setTestCaseChangeRequestId(entity.getTestCaseChangeRequestId());
			result.setTestId(entity.getTestId());
			result.setTestCaseLibraryId(entity.getTestCaseLibraryId());

			result.setShortDescription(entity.getShortDescription());
			result.setDescription(entity.getDescription());
			result.setCategory(entity.getCategory());
			result.setPreCondition(entity.getPreCondition());
			result.setExecutionEstimationTime(entity.getExecutionEstimationTime());
			result.setState(entity.getState());
			result.setCreated(entity.getCreated());
			result.setCreator(entity.getCreator());
			result.setModified(entity.getModified());
			result.setModifier(entity.getModifier());
			result.setTestStepChanges(mapTestStepsChangesDTO(entity.getTestStepChanges()));
		}
		return result;
	}

	public void patch(TestCaseChanges from, TestCaseChanges to) {

		if (from != null && to != null) {
			to.setId(from.getId());
			to.setTestCaseChangeRequestId(from.getTestCaseChangeRequestId());
			to.setTestId(from.getTestId());
			to.setTestCaseLibraryId(from.getTestCaseLibraryId());

			to.setShortDescription(from.getShortDescription());
			to.setDescription(from.getDescription());
			to.setCategory(from.getCategory());
			to.setPreCondition(from.getPreCondition());
			to.setExecutionEstimationTime(from.getExecutionEstimationTime());
			to.setState(from.getState());
			// to.setTestStepChanges(from.getTestStepChanges());
		}
	}

	// private methods
	private List<TestStepChangesDTO> mapTestStepsChangesDTO(List<TestStepChanges> testSteps) {

		List<TestStepChangesDTO> result = null;

		if (testSteps != null && !testSteps.isEmpty()) {
			result = testSteps.stream().map(testStep -> testStepChangesMapper.mapToDTO(testStep))
					.collect(Collectors.toList());
		}
		return result;
	}

	private List<TestStepChanges> mapTestStepsChanges(List<TestStepChangesDTO> testStepsDTO) {
		List<TestStepChanges> result = null;
		if (testStepsDTO != null && !testStepsDTO.isEmpty()) {
			result = testStepsDTO.stream().map(testStepDTO -> testStepChangesMapper.mapToEntity(testStepDTO))
					.collect(Collectors.toList());
		}
		return result;
	}

	private List<TestStepChanges> mapTestStepsToTestStepsChanges(List<TestStep> testSteps, Long testCaseChangeRequestId,
			TestCaseChanges parent) {
		List<TestStepChanges> result = null;
		if (testSteps != null && !testSteps.isEmpty()) {
			result = testSteps.stream().map(
					testStep -> testStepChangesMapper.mapTestStepsToEntity(testStep, testCaseChangeRequestId, parent))
					.collect(Collectors.toList());
		}
		return result;
	}

}
