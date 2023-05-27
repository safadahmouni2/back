package com.thinktank.pts.qaservice.service.impl;

import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.enums.TestRunState;
import com.thinktank.pts.qaservice.mapper.TestEffortMapper;
import com.thinktank.pts.qaservice.model.TestEffort;
import com.thinktank.pts.qaservice.model.TestRun;
import com.thinktank.pts.qaservice.repository.TestEffortRepository;
import com.thinktank.pts.qaservice.service.TestEffortService;

@Service
public class TestEffortServiceImpl implements TestEffortService {

	@Autowired
	private TestEffortRepository testEffortRepository;

	TestEffortMapper mapper = new TestEffortMapper();

	@Override
	public TestEffort addTestEffort(TestEffort testEffort) {
		if (testEffort != null) {
			TestRun testRun = testEffort.getTestRun();
				testRun.setResponsible(testEffort.getModifier());
				testRun.setState(TestRunState.IN_PROGRESS);
				testEffort.setTestRun(testRun);
				return testEffortRepository.save(testEffort);
		}
		return null;
	}

	@Override
	public List<TestEffort> getTestEffortListByTestId(Long testId) {
		return testEffortRepository.getTestEffortListByTestTestId(testId);
	}

	@Override
	public TestEffort getTestEffortById(Long id) {
		return testEffortRepository.getTestEffortById(id);
	}

	@Override
	public TestEffort updateTestEffort(Long id, TestEffort testEffort, String modifier) {
		TestEffort te = getTestEffortById(id);
		if (te == null) {
			return null;
		}

		TestRun testRun = te.getTestRun();
		TestRunState state = testRun.getState();

		if (state == TestRunState.CREATED || state == TestRunState.COMPLETED) {
			return null;
		}

		if (state == TestRunState.IN_PROGRESS && modifier.equals(testRun.getResponsible())) {
			testRun.setState(TestRunState.PAUSED);

		} else if (state == TestRunState.PAUSED) {
			testRun.setState(TestRunState.IN_PROGRESS);
			testRun.setResponsible(modifier);
		}

		testEffort.setTestRun(testRun);
		mapper.patch(testEffort, te);
		return testEffortRepository.save(te);
	}

	@Override
	public TestEffort closeTestEffort(Long id, TestEffort testEffort, String modifier) {
		TestEffort te = getTestEffortById(id);
		if (te == null) {
			return null;
		}

		TestRun testRun = te.getTestRun();
		if (!modifier.equals(testRun.getResponsible())) {
			return null;
		}

		testEffort.setTestRun(testRun);
		mapper.patch(testEffort, te);
		return testEffortRepository.save(te);
	}

	@Override
	public TestEffort finishTestEffort(Long id, TestEffort testEffort, String modifier) {
		TestEffort te = getTestEffortById(id);
		if (te == null) {
			return null;
		}

		TestRun testRun = te.getTestRun();
		TestRunState state = testRun.getState();

		if (state == TestRunState.IN_PROGRESS && modifier.equals(testRun.getResponsible())) {
			testRun.setState(TestRunState.COMPLETED);

		}
		if (state == TestRunState.PAUSED && modifier.equals(testRun.getResponsible())) {
			testRun.setState(TestRunState.COMPLETED);

		}

		testEffort.setTestRun(testRun);
		mapper.patch(testEffort, te);
		return testEffortRepository.save(te);
	}

	@Override
	public TestEffort getLastTestEffortByTestRunId(Long testRunId) {
		if (testEffortRepository.findFirstByTestRunTestRunIdAndEndTimeIsNullOrderByCreatedDesc(testRunId) != null) {
			return testEffortRepository.findFirstByTestRunTestRunIdAndEndTimeIsNullOrderByCreatedDesc(testRunId);
		} else {
			return testEffortRepository.findFirstByTestRunTestRunIdOrderByCreatedDesc(testRunId);
		}
	}

	@Override
	public Time getTotalTestEffortByTestRunId(Long testRunId) {
		Time totalTestEffort;
		totalTestEffort = testEffortRepository.getTotalTestEffortByTestRunId(testRunId);
		return totalTestEffort;
	}

	@Override
	public Time getTotalTestEffortByTestId(Long testId) {
		Time totalTestEffort;
		totalTestEffort = testEffortRepository.getTotalTestEffortByTestId(testId);
		return totalTestEffort;
	}

}
