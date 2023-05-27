package com.thinktank.pts.qaservice.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.thinktank.pts.qaservice.dto.TestDTO;
import com.thinktank.pts.qaservice.dto.TestStepDTO;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestStep;
import com.thinktank.pts.qaservice.model.TestStepLibrary;
import com.thinktank.pts.qaservice.model.UsTestCase;
import com.thinktank.pts.qaservice.model.UsTestStep;

public class TestMapper {
	private TestStepMapper mapper = new TestStepMapper();
	private UsTestStepMapper mapperTestStep = new UsTestStepMapper();

	public Test mapToEntity(TestDTO dto) {

		Test result = null;

		if (dto != null) {
			result = new Test();
			result.setTestId(dto.getTestId());
			result.setTestState(dto.getTestState());
			result.setCategory(dto.getCategory());
			result.setDescription(dto.getDescription());
			result.setPreCondition(dto.getPreCondition());
			result.setExecutionEstimationTime(dto.getExecutionEstimationTime());
			result.setShortDescription(dto.getShortDescription());
			result.setUserStoryId(dto.getUserStoryId());
			result.setTestCaseVersion(dto.getTestCaseVersion());
			result.setOriginSprintId(dto.getOriginSprintId());
			if (dto.getTestSteps() != null) {
				result.setTestSteps(mapTestSteps(dto.getTestSteps()));
			}
		}
		return result;
	}

	public Test mapToEntity(TestCaseLibrary testCaseLibrary) {

		Test result = null;

		if (testCaseLibrary != null) {
			result = new Test();
			result.setCategory(testCaseLibrary.getCategory());
			result.setDescription(testCaseLibrary.getDescription());
			result.setPreCondition(testCaseLibrary.getPreCondition());
			result.setExecutionEstimationTime(testCaseLibrary.getExecutionEstimationTime());
			result.setShortDescription(testCaseLibrary.getShortDescription());
			result.setTestCaseVersion(testCaseLibrary.getTestCaseVersion());
			if (testCaseLibrary.getTestStepsLibrary() != null) {
				result.setTestSteps(mapTestStepsLibrary(testCaseLibrary.getTestStepsLibrary(), result));
			}
		}
		return result;
	}

	public Test mapToEntity(UsTestCase usTestCase) {
		Test result = null;
		if (usTestCase != null) {
			result = new Test();
			result.setCategory(usTestCase.getCategory());
			result.setDescription(usTestCase.getDescription());
			result.setPreCondition(usTestCase.getPreCondition());
			result.setExecutionEstimationTime(usTestCase.getExecutionEstimationTime());
			result.setShortDescription(usTestCase.getShortDescription());
			result.setUserStoryId(usTestCase.getUserStoryId());
			result.setTestCaseVersion(usTestCase.getTestCaseVersion());
			result.setOriginSprintId(usTestCase.getSprintId());
			if (usTestCase.getTestSteps() != null) {
				result.setTestSteps(mapTestSteps(usTestCase.getTestSteps(), result));
			}
		}
		return result;
	}

	public TestDTO mapToDTO(Test entity) {

		TestDTO result = null;

		if (entity != null) {
			result = new TestDTO();
			result.setTestId(entity.getTestId());
			result.setTestState(entity.getTestState());
			result.setCategory(entity.getCategory());
			result.setDescription(entity.getDescription());
			result.setPreCondition(entity.getPreCondition());
			result.setExecutionEstimationTime(entity.getExecutionEstimationTime());
			result.setShortDescription(entity.getShortDescription());
			result.setUserStoryId(entity.getUserStoryId());
			result.setShortDescription(entity.getShortDescription());
			result.setTestCaseVersion(entity.getTestCaseVersion());
			result.setCreated(entity.getCreated());
			result.setCreator(entity.getCreator());
			result.setModified(entity.getModified());
			result.setModifier(entity.getModifier());
			if(entity.getTestCaseLibrary()!= null) {
			result.setTestCaseLibraryId(entity.getTestCaseLibrary().getTestCaseLibraryId());
			}
			result.setTestSteps(mapTestStepsDTO(entity.getTestSteps()));
			result.setOriginSprintId(entity.getOriginSprintId());
			if (entity.getTestRun() != null) {
				result.setTestRunId(entity.getTestRun().getTestRunId());
				result.setEnvironmentId(entity.getTestRun().getEnvironmentId());
			}
		}
		return result;
	}

	// private methods
	private List<TestStepDTO> mapTestStepsDTO(List<TestStep> testSteps) {

		List<TestStepDTO> result = null;

		if (testSteps != null && !testSteps.isEmpty()) {
			result = new ArrayList<>();
			result = testSteps.stream().map(testStep -> mapper.mapToDTO(testStep)).collect(Collectors.toList());
		}
		return result;
	}

	private List<TestStep> mapTestSteps(List<TestStepDTO> testStepsDTO) {

		List<TestStep> result = null;

		if (testStepsDTO != null && !testStepsDTO.isEmpty()) {
			result = new ArrayList<>();
			result = testStepsDTO.stream().map(testStepDTO -> mapper.mapToEntity(testStepDTO))
					.collect(Collectors.toList());
		}
		return result;
	}

	private List<TestStep> mapTestSteps(List<UsTestStep> testSteps, Test parent) {
		List<TestStep> result = null;
		if (testSteps != null && !testSteps.isEmpty()) {
			result = new ArrayList<>();
			result = testSteps.stream().map(testStep -> mapperTestStep.mapToEntity(testStep, parent))
					.collect(Collectors.toList());
		}
		return result;
	}

	private List<TestStep> mapTestStepsLibrary(List<TestStepLibrary> testStepsLibraryList, Test parent) {

		List<TestStep> result = null;

		if (testStepsLibraryList != null && !testStepsLibraryList.isEmpty()) {
			result = new ArrayList<>();
			result = testStepsLibraryList.stream().map(testStepLibrary -> mapper.mapToEntity(testStepLibrary, parent))
					.collect(Collectors.toList());
		}
		return result;
	}

	public void patch(Test from, Test to) {
		if (from != null && to != null) {
			to.setCategory(from.getCategory());
			to.setDescription(from.getDescription());
			if (from.getExecutionEstimationTime() != null) {
				to.setExecutionEstimationTime(from.getExecutionEstimationTime());
			}
			to.setPreCondition(from.getPreCondition());
			to.setShortDescription(from.getShortDescription());
			to.setTestState(from.getTestState());
			to.setTestCaseLibrary(from.getTestCaseLibrary());
			to.setTestRun(from.getTestRun());
			to.setOriginSprintId(from.getOriginSprintId());
		}
	}
}
