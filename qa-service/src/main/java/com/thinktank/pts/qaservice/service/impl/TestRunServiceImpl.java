package com.thinktank.pts.qaservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.dto.TestRunDTO;
import com.thinktank.pts.qaservice.enums.TestRunState;
import com.thinktank.pts.qaservice.mapper.TestRunMapper;
import com.thinktank.pts.qaservice.model.TestRun;
import com.thinktank.pts.qaservice.repository.TestRunRepository;
import com.thinktank.pts.qaservice.service.TestRunService;

@Service
public class TestRunServiceImpl implements TestRunService {

	@Autowired
	private TestRunRepository testRunRepository;

	TestRunMapper mapper = new TestRunMapper();

	@Override
	public List<TestRun> getTestRunListByInstallId(Long installId) {

		return null;
		// testRunRepository.findByInstallInstallId(installId);
	}

	@Override
	public TestRun getTestRunById(Long testRunId) {
		return testRunRepository.findByTestRunId(testRunId);
	}

	@Override
	public TestRun addTestRun(TestRun testRun) {
		TestRun result = null;
		if (testRun != null) {
			testRun.setState(TestRunState.CREATED);
			result = testRunRepository.save(testRun);
		}
		return result;
	}

	@Override
	public TestRun updateTestRun(Long testRunId, TestRun testRun) {
		TestRun result = null;
		TestRun tr = getTestRunById(testRunId);
		if (tr != null) {
			mapper.patch(testRun, tr);
			result = testRunRepository.save(tr);
		}
		return result;
	}

	@Override
	public Boolean deleteTestRun(Long id) {
		Boolean result = false;
		TestRun testRun = getTestRunById(id);
		List<TestRun> testRuns = new ArrayList<>();
		testRuns.add(testRun);
		if (testRun != null) {
			testRunRepository.deleteInBatch(testRuns);
			result = true;
		}
		return result;
	}

	@Override
	public List<TestRun> getTestRunsByIdInstall(Long installId) {
		return testRunRepository.findByInstalledId(installId);
	}

	@Override
	public long countByInstallId(Long installId) {
		return testRunRepository.countByInstalledId(installId);
	}

	@Override
	public Map<Long, List<TestRunDTO>> getTestRunByEnvAndInstall(Long envId) {
		List<TestRun> testRuns = testRunRepository.findByEnvironmentId(envId);
		List<TestRunDTO> testRunDTOList = new ArrayList<>();

		for (TestRun testRun : testRuns) {
			testRunDTOList.add(mapper.mapToDTO(testRun));
		}

		Map<Long, List<TestRunDTO>> TestRunsGroupByInstall = testRunDTOList.stream()
				.collect(Collectors.groupingBy(TestRunDTO::getInstalled_id));
		return TestRunsGroupByInstall;
	}

	@Override
	public List<TestRun> getTestRunsByEnvId(Long envId) {
		return testRunRepository.findByEnvironmentId(envId);
	}

	@Override
	public List<Long> getAllInstallsByEnvId(Long envId) {
		return testRunRepository.findDistinctInstalledIdByEnvironmentId(envId);
	}

}
