package com.thinktank.pts.qaservice.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.thinktank.pts.qaservice.dto.TestDTO;
import com.thinktank.pts.qaservice.dto.TestRunDTO;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestRun;

public class TestRunMapper {

	TestMapper mapper = new TestMapper();

	public TestRun mapToEntity(TestRunDTO dto) {

		TestRun result = null;

		if (dto != null) {
			result = new TestRun();
			result.setTestRunId(dto.getTestRunId());
			result.setDescription(dto.getDescription());
			result.setShortDescription(dto.getShortDescription());
			result.setState(dto.getState());
			result.setInstalledId(dto.getInstalled_id());
			result.setEnvironmentId(dto.getEnvironmentId());
			result.setResponsible(dto.getResponsible());

			if (dto.getTests() != null) {
				result.setTests(mapTests(dto.getTests()));
			}
		}
		return result;
	}

	public TestRun mapToEntityWithEstimatedEffort(TestRunDTO dto, BigDecimal estimatedEffort) {

		TestRun result = null;

		if (dto != null) {
			result = new TestRun();
			result.setTestRunId(dto.getTestRunId());
			result.setDescription(dto.getDescription());
			result.setShortDescription(dto.getShortDescription());
			result.setState(dto.getState());
			result.setInstalledId(dto.getInstalled_id());
			result.setEnvironmentId(dto.getEnvironmentId());
			result.setResponsible(dto.getResponsible());
			result.setModified(dto.getModified());
			result.setModifier(dto.getModifier());
			result.setCreated(dto.getCreated());
			result.setCreator(dto.getCreator());

			if (dto.getTests() != null) {
				result.setTests(mapTests(dto.getTests()));
			}
			result.setEstimatedEffort(estimatedEffort);
		}
		return result;
	}

	public TestRunDTO mapToDTO(TestRun entity) {

		TestRunDTO result = null;

		if (entity != null) {
			result = new TestRunDTO();
			result.setTestRunId(entity.getTestRunId());
			result.setDescription(entity.getDescription());
			result.setShortDescription(entity.getShortDescription());
			result.setState(entity.getState());
			result.setInstalled_id(entity.getInstalledId());
			result.setEnvironmentId(entity.getEnvironmentId());
			result.setTests(mapTestsDTO(entity.getTests()));
			result.setEstimatedEffort(entity.getEstimatedEffort());
			result.setResponsible(entity.getResponsible());
		}
		return result;
	}

	public void patch(TestRun from, TestRun to) {

		if (from != null && to != null) {
			to.setTestRunId(from.getTestRunId());
			to.setDescription(from.getDescription());
			to.setShortDescription(from.getShortDescription());
			to.setState(from.getState());
		}
	}

	// private methods
	private List<TestDTO> mapTestsDTO(List<Test> tests) {

		List<TestDTO> result = null;

		if (tests != null && !tests.isEmpty()) {
			result = new ArrayList<>();
			result = tests.stream().map(test -> mapper.mapToDTO(test)).collect(Collectors.toList());
		}
		return result;
	}

	private List<Test> mapTests(List<TestDTO> testsDTO) {

		List<Test> result = null;

		if (testsDTO != null && !testsDTO.isEmpty()) {
			result = new ArrayList<>();
			result = testsDTO.stream().map(testDTO -> mapper.mapToEntity(testDTO)).collect(Collectors.toList());
		}
		return result;
	}

	public TestRunDTO mapToDTOWithStatistic(TestRun entity, Integer totalTestOk, Integer totalTestNotOk) {

		TestRunDTO result = null;

		if (entity != null) {
			result = new TestRunDTO();
			result.setTestRunId(entity.getTestRunId());
			result.setDescription(entity.getDescription());
			result.setShortDescription(entity.getShortDescription());
			result.setState(entity.getState());
			result.setInstalled_id(entity.getInstalledId());
			result.setEnvironmentId(entity.getEnvironmentId());
			result.setEstimatedEffort(entity.getEstimatedEffort());
			result.setTotalTests(entity.getTests().size());
			result.setTotalTestOk(totalTestOk);
			result.setTotalTestNotOk(totalTestNotOk);
			result.setResponsible(entity.getResponsible());
			result.setModified(entity.getModified());
			result.setModifier(entity.getModifier());
			result.setCreated(entity.getCreated());
			result.setCreator(entity.getCreator());
		}
		return result;
	}

}
