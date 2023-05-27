package com.thinktank.pts.qaservice.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.thinktank.pts.qaservice.dto.TestCaseInputDTO;
import com.thinktank.pts.qaservice.dto.UsTestCaseDTO;
import com.thinktank.pts.qaservice.dto.UsTestStepDTO;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.UsTestCase;
import com.thinktank.pts.qaservice.model.UsTestStep;

public class UsTestCaseMapper {
	private UsTestStepMapper testStepMapper = new UsTestStepMapper();
	private FolderMapper folderMapper = new FolderMapper();

	public UsTestCase mapToEntity(UsTestCaseDTO dto) {

		UsTestCase result = null;
		if (dto != null) {
			result = new UsTestCase();
			result.setUsTestCaseId(dto.getUsTestCaseId());
			result.setTestCaseVersion(dto.getTestCaseVersion());
			result.setState(dto.getState());
			result.setCategory(dto.getCategory());
			result.setDescription(dto.getDescription());
			result.setPreCondition(dto.getPreCondition());
			result.setExecutionEstimationTime(dto.getExecutionEstimationTime());
			result.setShortDescription(dto.getShortDescription());
			result.setUserStoryId(dto.getUserStoryId());
			result.setSprintId(dto.getSprintId());
			if (dto.getTestSteps() != null) {
				result.setTestSteps(mapTestSteps(dto.getTestSteps()));
			}

		}
		return result;
	}

	/**
	 * mapTestCaseLibraryToTestCase : Map from testCaseLibrary to TestCase
	 * 
	 * @param testCaseLibrary
	 * @param testCaseInputDTO
	 * @return
	 */
	public UsTestCase mapTestCaseLibraryToTestCase(TestCaseLibrary testCaseLibrary, TestCaseInputDTO testCaseInputDTO) {

		UsTestCase result = null;
		if (testCaseLibrary != null) {
			result = new UsTestCase();
			result.setUsTestCaseId(testCaseLibrary.getTestCaseLibraryId());
			result.setState(testCaseLibrary.getState());
			result.setCategory(testCaseLibrary.getCategory());
			result.setDescription(testCaseLibrary.getDescription());
			result.setPreCondition(testCaseLibrary.getPreCondition());
			result.setExecutionEstimationTime(testCaseLibrary.getExecutionEstimationTime());
			result.setShortDescription(testCaseLibrary.getShortDescription());
			result.setUserStoryId(testCaseInputDTO.getUserStoryId());
			result.setTestCaseLibraryId(testCaseLibrary.getTestCaseLibraryId());
			result.setSprintId(testCaseInputDTO.getSprintId());
			result.setFolder(testCaseLibrary.getFolder());
			result.setTestCaseVersion(testCaseLibrary.getTestCaseVersion());
			result.setTestSteps(
					testStepMapper.mapTestStepLibraryToTestStep(testCaseLibrary.getTestStepsLibrary(), result));
		}
		return result;
	}

	public UsTestCaseDTO mapToDTO(UsTestCase entity) {

		UsTestCaseDTO result = null;
		if (entity != null) {
			result = new UsTestCaseDTO();
			result.setUsTestCaseId(entity.getUsTestCaseId());
			result.setState(entity.getState());
			result.setCategory(entity.getCategory());
			result.setDescription(entity.getDescription());
			result.setPreCondition(entity.getPreCondition());
			result.setExecutionEstimationTime(entity.getExecutionEstimationTime());
			result.setShortDescription(entity.getShortDescription());
			result.setUserStoryId(entity.getUserStoryId());
			result.setModified(entity.getModified());
			result.setModifier(entity.getModifier());
			result.setCreated(entity.getCreated());
			result.setCreator(entity.getCreator());
			// map usTestCaseId
			result.setTestCaseLibraryId(entity.getTestCaseLibraryId());
			result.setTestCaseVersion(entity.getTestCaseVersion());
			result.setTestSteps(mapTestStepsDTO(entity.getTestSteps()));
			result.setSprintId(entity.getSprintId());
			if (entity.getFolder() != null) {
				result.setFolder(folderMapper.mapToDTO(entity.getFolder()));
			}
		}
		return result;
	}

	public void patch(UsTestCase from, UsTestCase to) {

		if (from != null && to != null) {
			to.setState(from.getState());
			to.setCategory(from.getCategory());
			to.setDescription(from.getDescription());
			to.setPreCondition(from.getPreCondition());
			to.setExecutionEstimationTime(from.getExecutionEstimationTime());
			to.setShortDescription(from.getShortDescription());
			to.setTestCaseVersion(from.getTestCaseVersion());
		}
	}

	// private methods
	private List<UsTestStepDTO> mapTestStepsDTO(List<UsTestStep> testSteps) {

		List<UsTestStepDTO> result = null;

		if (testSteps != null && !testSteps.isEmpty()) {
			result = new ArrayList<>();
			result = testSteps.stream().map(testStep -> testStepMapper.mapToDTO(testStep)).collect(Collectors.toList());
		}
		return result;
	}

	private List<UsTestStep> mapTestSteps(List<UsTestStepDTO> testStepsDTO) {

		List<UsTestStep> result = null;

		if (testStepsDTO != null && !testStepsDTO.isEmpty()) {
			result = new ArrayList<>();
			result = testStepsDTO.stream().map(testStepDTO -> testStepMapper.mapToEntity(testStepDTO))
					.collect(Collectors.toList());
		}
		return result;
	}

}
