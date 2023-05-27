package com.thinktank.pts.qaservice.service;

import java.sql.Time;
import java.util.List;

import com.thinktank.pts.qaservice.model.TestEffort;

public interface TestEffortService {

	TestEffort addTestEffort(TestEffort testEffort);

	TestEffort updateTestEffort(Long id, TestEffort testEffort, String modifier);

	TestEffort closeTestEffort(Long id, TestEffort testEffort, String modifier);

	TestEffort finishTestEffort(Long id, TestEffort testEffort, String modifier);

	List<TestEffort> getTestEffortListByTestId(Long testId);

	TestEffort getTestEffortById(Long id);

	TestEffort getLastTestEffortByTestRunId(Long testRunId);

	Time getTotalTestEffortByTestRunId(Long testRunId);

	Time getTotalTestEffortByTestId(Long testId);
}
