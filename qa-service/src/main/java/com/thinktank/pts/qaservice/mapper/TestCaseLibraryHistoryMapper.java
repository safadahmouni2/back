package com.thinktank.pts.qaservice.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.thinktank.pts.qaservice.dto.TestCaseLibraryHistoryDTO;
import com.thinktank.pts.qaservice.dto.TestStepLibraryHistoryDTO;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestCaseLibraryHistory;
import com.thinktank.pts.qaservice.model.TestStepLibrary;
import com.thinktank.pts.qaservice.model.TestStepLibraryHistory;

public class TestCaseLibraryHistoryMapper {

	private TestStepLibraryHistoryMapper testStepLibraryHistoryMapper = new TestStepLibraryHistoryMapper();

	public TestCaseLibraryHistory mapTestCaseLibraryToTestCaseLibraryHistory(TestCaseLibrary tcl) {

		TestCaseLibraryHistory result = null;
		if (tcl != null) {
			result = new TestCaseLibraryHistory();
			result.setTestCaseLibraryId(tcl.getTestCaseLibraryId());
			result.setCreator(tcl.getModifier());
			result.setTestCaseVersion(tcl.getTestCaseVersion());
			result.setShortDescription(tcl.getShortDescription());
			result.setDescription(tcl.getDescription());
			result.setCategory(tcl.getCategory());
			result.setPreCondition(tcl.getPreCondition());
			result.setExecutionEstimationTime(tcl.getExecutionEstimationTime());
			result.setProductId(tcl.getProductId());
			if (tcl.getTestStepsLibrary() != null) {
				List<TestStepLibraryHistory> testStepList = new ArrayList<TestStepLibraryHistory>();
				for (TestStepLibrary testStepLibrary : tcl.getTestStepsLibrary()) {
					// TestStepLibraryHistory testStep = new TestStepLibraryHistory();
					TestStepLibraryHistory testStep = testStepLibraryHistoryMapper
							.mapTestStepLibraryToTestStepLibraryHistory(testStepLibrary);
					testStep.setTestCaseLibraryHistory(result);

					testStepList.add(testStep);
				}
				result.setTestStepsLibraryHistory(testStepList);
			}

		}

		return result;
	}

	public TestCaseLibraryHistory mapToEntity(TestCaseLibraryHistoryDTO dto) {

		TestCaseLibraryHistory result = null;
		if (dto != null) {
			result = new TestCaseLibraryHistory();
			result.setId(dto.getId());
			result.setTestCaseLibraryId(dto.getTestCaseLibraryId());
			result.setTestCaseVersion(dto.getTestCaseVersion());
			result.setShortDescription(dto.getShortDescription());
			result.setDescription(dto.getDescription());
			result.setCategory(dto.getCategory());
			result.setPreCondition(dto.getPreCondition());
			result.setExecutionEstimationTime(dto.getExecutionEstimationTime());
			result.setProductId(dto.getProductId());
			result.setFolder(dto.getFolder());
			result.setTestStepsLibraryHistory(mapTestStepsLibraryHistory(dto.getTestStepsLibraryHistory()));

		}
		return result;
	}

	public TestCaseLibraryHistoryDTO mapToDTO(TestCaseLibraryHistory entity) {

		TestCaseLibraryHistoryDTO result = null;
		if (entity != null) {
			result = new TestCaseLibraryHistoryDTO();
			result.setId(entity.getId());
			result.setDate(entity.getCreated());
			result.setCreator(entity.getModifier());
			result.setTestCaseLibraryId(entity.getTestCaseLibraryId());
			result.setTestCaseVersion(entity.getTestCaseVersion());
			result.setShortDescription(entity.getShortDescription());
			result.setDescription(entity.getDescription());
			result.setCategory(entity.getCategory());
			result.setPreCondition(entity.getPreCondition());
			result.setExecutionEstimationTime(entity.getExecutionEstimationTime());
			result.setProductId(entity.getProductId());
			result.setFolder(entity.getFolder());
			result.setTestStepsLibraryHistory(mapTestStepsLibraryHistoryDTO(entity.getTestStepsLibraryHistory()));
		}
		return result;
	}

	// private methods
	private List<TestStepLibraryHistoryDTO> mapTestStepsLibraryHistoryDTO(List<TestStepLibraryHistory> testSteps) {

		List<TestStepLibraryHistoryDTO> result = null;

		if (testSteps != null && !testSteps.isEmpty()) {
			result = new ArrayList<>();
			result = testSteps.stream().map(testStep -> testStepLibraryHistoryMapper.mapToDTO(testStep))
					.collect(Collectors.toList());
		}
		return result;
	}

	private List<TestStepLibraryHistory> mapTestStepsLibraryHistory(List<TestStepLibraryHistoryDTO> testStepsDTO) {

		List<TestStepLibraryHistory> result = null;

		if (testStepsDTO != null && !testStepsDTO.isEmpty()) {
			result = new ArrayList<>();
			result = testStepsDTO.stream().map(testStepDTO -> testStepLibraryHistoryMapper.mapToEntity(testStepDTO))
					.collect(Collectors.toList());
		}
		return result;
	}

}
