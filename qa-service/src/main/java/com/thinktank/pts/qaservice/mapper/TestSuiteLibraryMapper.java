package com.thinktank.pts.qaservice.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.thinktank.pts.qaservice.dto.TestCaseLibraryDTO;
import com.thinktank.pts.qaservice.dto.TestSuiteLibraryDTO;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestRun;
import com.thinktank.pts.qaservice.model.TestSuiteLibrary;

public class TestSuiteLibraryMapper {

	private TestCaseLibraryMapper testCaseLibraryMapper = new TestCaseLibraryMapper();

	public TestSuiteLibrary mapTestRunToTestSuiteLibrary(TestRun testRunDTO, Long productId,
			List<TestCaseLibrary> testCaseLibrary) {
		TestSuiteLibrary result = null;
		BigDecimal totalEstimatedEffort = BigDecimal.ZERO;
		if (testRunDTO != null) {
			result = new TestSuiteLibrary();
			result.setInstalledId(testRunDTO.getInstalledId());
			result.setDescription(testRunDTO.getDescription());
			result.setShortDescription(testRunDTO.getShortDescription());
			result.setCreationTestRun(testRunDTO.getTestRunId());
			result.setProductId(productId);
			result.addTestCaseLibrary(testCaseLibrary);
			for (TestCaseLibrary libraryTestCase : testCaseLibrary) {
				if (libraryTestCase.getExecutionEstimationTime() != null) {
					totalEstimatedEffort = totalEstimatedEffort.add(libraryTestCase.getExecutionEstimationTime());
				}
			}
			result.setEstimatedEffort(totalEstimatedEffort);
		}
		return result;
	}

	public TestSuiteLibraryDTO mapToDTO(TestSuiteLibrary entity) {
		TestSuiteLibraryDTO result = null;
		BigDecimal totalEstimatedEffort = BigDecimal.ZERO;
		if (entity != null) {
			result = new TestSuiteLibraryDTO();
			result.setTestSuiteLibraryId(entity.getTestSuiteLibraryId());
			result.setCreationTestRun(entity.getCreationTestRun());
			result.setShortDescription(entity.getShortDescription());
			result.setDescription(entity.getDescription());
			result.setInstalled_id(entity.getInstalledId());
			result.setState(entity.getState());
			result.setTestCaseLibraries(mapTestCasesLibraryDTO(entity.getTestCaseLibraries()));
			// calculate total estimatedEffort
			for (TestCaseLibrary libraryTestCase : entity.getTestCaseLibraries()) {
				if (libraryTestCase.getExecutionEstimationTime() != null) {
					totalEstimatedEffort = totalEstimatedEffort.add(libraryTestCase.getExecutionEstimationTime());
				}
			}
			result.setEstimatedEffort(totalEstimatedEffort);
		}
		return result;
	}

	private List<TestCaseLibraryDTO> mapTestCasesLibraryDTO(List<TestCaseLibrary> testCasesLibrary) {

		List<TestCaseLibraryDTO> result = null;

		if (CollectionUtils.isNotEmpty(testCasesLibrary)) {
			result = testCasesLibrary.stream().map(testCaseLibrary -> testCaseLibraryMapper.mapToDTO(testCaseLibrary))
					.collect(Collectors.toList());
		}
		return result;
	}

}
