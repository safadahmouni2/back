package com.thinktank.pts.qaservice.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.thinktank.pts.qaservice.dto.TestCaseLibraryDTO;
import com.thinktank.pts.qaservice.dto.TestStepLibraryDTO;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestStepLibrary;

public class TestCaseLibraryMapper {

	private TestStepLibraryMapper testStepLibraryMapper = new TestStepLibraryMapper();
	private FolderMapper folderMapper = new FolderMapper();

	private final SourceSystemMapper sourceSystemMapper = new SourceSystemMapper();

	public TestCaseLibrary mapToEntity(TestCaseLibraryDTO dto) {

		TestCaseLibrary result = null;

		if (dto != null) {
			result = new TestCaseLibrary();
			result.setTestCaseLibraryId(dto.getTestCaseLibraryId());
			result.setState(dto.getState());
			result.setCategory(dto.getCategory());
			result.setDescription(dto.getDescription());
			result.setPreCondition(dto.getPreCondition());
			result.setExecutionEstimationTime(dto.getExecutionEstimationTime());
			result.setShortDescription(dto.getShortDescription());
			result.setProductId(dto.getProductId());
			result.setTestCaseVersion(dto.getTestCaseVersion());
			if (dto.getTestStepsLibrary() != null) {
				result.setTestStepsLibrary(mapTestStepsLibrary(dto.getTestStepsLibrary(), result));
			}
			if (dto.getFolder() != null) {
				result.setFolder(folderMapper.mapToEntity(dto.getFolder()));
			}
			if (dto.getSourceSystem() != null) {
				result.setSourceSystem(sourceSystemMapper.mapToEntity(dto.getSourceSystem()));
			}
		}

		return result;
	}

	public TestCaseLibraryDTO mapToDTO(TestCaseLibrary entity) {

		TestCaseLibraryDTO result = null;
		if (entity != null) {
			result = new TestCaseLibraryDTO();
			result.setTestCaseLibraryId(entity.getTestCaseLibraryId());
			result.setState(entity.getState());
			result.setCategory(entity.getCategory());
			result.setDescription(entity.getDescription());
			result.setPreCondition(entity.getPreCondition());
			result.setExecutionEstimationTime(entity.getExecutionEstimationTime());
			result.setShortDescription(entity.getShortDescription());
			result.setTestCaseVersion(entity.getTestCaseVersion());
			result.setModified(entity.getModified());
			result.setModifier(entity.getModifier());
            result.setProductId(entity.getProductId());
            result.setCreated(entity.getCreated());
            result.setCreator(entity.getCreator());
			result.setTestStepsLibrary(mapTestStepsLibraryDTO(entity.getTestStepsLibrary()));
			if (entity.getFolder() != null) {
				result.setFolder(folderMapper.mapToDTO(entity.getFolder()));
			}
			if (entity.getSourceSystem() != null) {
				result.setSourceSystem(sourceSystemMapper.mapToDTO(entity.getSourceSystem()));
			}
		}
		return result;
	}

	public void patch(TestCaseLibrary from, TestCaseLibrary to) {

		if (from != null && to != null) {
			to.setState(from.getState());
			to.setCategory(from.getCategory());
			to.setDescription(from.getDescription());
			to.setPreCondition(from.getPreCondition());
			to.setExecutionEstimationTime(from.getExecutionEstimationTime());
			to.setShortDescription(from.getShortDescription());
			to.setTestCaseVersion(from.getTestCaseVersion());
			if(from.getExternalId()!=null){
				to.setExternalId(from.getExternalId());
			}
		}
	}

	// private methods
	private List<TestStepLibraryDTO> mapTestStepsLibraryDTO(List<TestStepLibrary> testStepsLibrary) {

		List<TestStepLibraryDTO> result = null;

		if (testStepsLibrary != null && !testStepsLibrary.isEmpty()) {
			result = new ArrayList<>();
			result = testStepsLibrary.stream().map(testStepLibrary -> testStepLibraryMapper.mapToDTO(testStepLibrary))
					.collect(Collectors.toList());
		}
		return result;
	}

	private List<TestStepLibrary> mapTestStepsLibrary(List<TestStepLibraryDTO> testStepsLibraryDTO,
			TestCaseLibrary parent) {

		List<TestStepLibrary> result = null;

		if (testStepsLibraryDTO != null && !testStepsLibraryDTO.isEmpty()) {
			result = new ArrayList<>();
			result = testStepsLibraryDTO.stream().map(
					testStepLibraryDTO -> testStepLibraryMapper.mapToEntityWithTestCase(testStepLibraryDTO, parent))
					.collect(Collectors.toList());
		}
		return result;
	}
}
