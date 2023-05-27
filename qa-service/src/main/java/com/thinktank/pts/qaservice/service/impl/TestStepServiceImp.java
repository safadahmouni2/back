package com.thinktank.pts.qaservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.enums.TestRunState;
import com.thinktank.pts.qaservice.enums.TestStepState;
import com.thinktank.pts.qaservice.mapper.TestStepMapper;
import com.thinktank.pts.qaservice.model.TestRun;
import com.thinktank.pts.qaservice.model.TestStep;
import com.thinktank.pts.qaservice.repository.TestStepRepository;
import com.thinktank.pts.qaservice.service.TestStepService;

@Service
public class TestStepServiceImp implements TestStepService {

	@Autowired
	private TestStepRepository testStepRepository;

	TestStepMapper mapper = new TestStepMapper();

	@Override
	public List<TestStep> getTestStepsByTestCaseLibraryId(Long testCaseLibraryId) {
		return testStepRepository.getTestStepListByTestCaseLibraryTestCaseLibraryId(testCaseLibraryId);
	}

	@Override
	public TestStep getTestStepById(Long id) {
		return testStepRepository.getTestStepById(id);
	}

	@Override
	public Boolean addTestStep(TestStep testStep) {
		Boolean result = false;
		if (testStep != null) {
			TestRun testRun = testStep.getTest().getTestRun();
			if (testRun.getState() == TestRunState.COMPLETED) {
				return result;
			}
			testStepRepository.save(testStep);
			result = true;
		}
		return result;
	}

	@Override
	public TestStep updateTestStep(TestStep testStep) {
		TestStep result = null;
		TestStep ts = getTestStepById(testStep.getId());
		if (ts != null) {
			TestRun testRun = ts.getTest().getTestRun();
			if (testRun.getState() == TestRunState.COMPLETED) {
				return null;
			}
			mapper.patch(testStep, ts);
			result = testStepRepository.save(ts);
		}
		return result;
	}

	@Override
	public Boolean deleteTestStep(Long id) {
		Boolean result = false;
		TestStep testStep = getTestStepById(id);
		List<TestStep> testSteps = new ArrayList<>();
		testSteps.add(testStep);
		if (testStep != null) {
			TestRun testRun = testStep.getTest().getTestRun();
			if (testRun.getState() == TestRunState.COMPLETED) {
				return result;
			}
			testStepRepository.deleteInBatch(testSteps);
			result = true;
		}
		return result;
	}

	@Override
	public Boolean deleteTestStepList(List<TestStep> testSteps) {
		Boolean result = false;
		if (testSteps != null) {
			testStepRepository.deleteInBatch(testSteps);
			result = true;
		}
		return result;
	}

	@Override
	public Boolean addTestStepList(List<TestStep> testSteps) {
		Boolean result = false;
		if (testSteps != null) {
			testStepRepository.saveAll(testSteps);
			result = true;
		}
		return result;

	}

	@Override
	public TestStep updateTestStepState(Long id, TestStepState state) {
		TestStep ts = getTestStepById(id);
		if (ts != null) {
			TestRun testRun = ts.getTest().getTestRun();
			if (testRun.getState() == TestRunState.COMPLETED) {
				return null;
			}
			if (state == null) {
				ts.setTestStepState(null);
			} else {
				ts.setTestStepState(state);
			}
			return testStepRepository.save(ts);
		}
		return null;
	}

	@Override
	public List<TestStep> getTestStepByTestId(Long testId) {
		return testStepRepository.getTestStepListByTestTestId(testId);
	}

	@Override
	public List<TestStep> findByTestCaseLibraryId(Long id) {
		return testStepRepository.getTestStepListByTestCaseLibraryTestCaseLibraryId(id);
	}
}
