package com.thinktank.pts.qaservice.service;

import java.util.List;
import java.util.Map;

import com.thinktank.pts.qaservice.dto.TestRunDTO;
import com.thinktank.pts.qaservice.model.TestRun;

public interface TestRunService {

	List<TestRun> getTestRunListByInstallId(Long installId);

	TestRun getTestRunById(Long id);

	TestRun addTestRun(TestRun testRun);

	TestRun updateTestRun(Long id, TestRun testRun);

	Boolean deleteTestRun(Long id);

	List<TestRun> getTestRunsByIdInstall(Long installId);

	long countByInstallId(Long installId);

	Map<Long, List<TestRunDTO>> getTestRunByEnvAndInstall(Long env_id);

	List<TestRun> getTestRunsByEnvId(Long envId);

	List<Long> getAllInstallsByEnvId(Long envId);

}
