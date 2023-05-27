package com.thinktank.pts.qaservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.enums.TestStepState;
import com.thinktank.pts.qaservice.mapper.UsTestStepMapper;
import com.thinktank.pts.qaservice.model.UsTestStep;
import com.thinktank.pts.qaservice.repository.UsTestStepRepository;
import com.thinktank.pts.qaservice.service.UsTestStepService;

@Service
public class UsTestStepServiceImp implements UsTestStepService {

	@Autowired
	private UsTestStepRepository testStepRepository;

	UsTestStepMapper mapper = new UsTestStepMapper();

	@Override
	public List<UsTestStep> getUsTestStepsByTestCaseId(Long usTestCaseId) {
		return testStepRepository.getUsTestStepListByUsTestCaseUsTestCaseId(usTestCaseId);
	}

	@Override
	public UsTestStep getUsTestStepById(Long id) {
		return testStepRepository.getUsTestStepById(id);
	}

	@Override
	public Boolean addUsTestStep(UsTestStep testStep) {
		Boolean result = false;
		if (testStep != null) {
			testStepRepository.save(testStep);
			result = true;
		}
		return result;
	}

	@Override
	public UsTestStep updateUsTestStep(UsTestStep testStep) {
		UsTestStep result = null;
		UsTestStep ts = getUsTestStepById(testStep.getId());
		if (ts != null) {
			mapper.patch(testStep, ts);
			result = testStepRepository.save(ts);
		}
		return result;
	}

	@Override
	public Boolean deleteUsTestStep(Long id) {
		Boolean result = false;
		UsTestStep testStep = getUsTestStepById(id);
		List<UsTestStep> testSteps = new ArrayList<>();
		testSteps.add(testStep);
		if (testStep != null) {
			testStepRepository.deleteInBatch(testSteps);
			result = true;
		}
		return result;
	}

	@Override
	public Boolean deleteUsTestStepList(List<UsTestStep> testSteps) {
		Boolean result = false;
		if (testSteps != null) {
			testStepRepository.deleteInBatch(testSteps);
			result = true;
		}
		return result;
	}

	@Override
	public Boolean addUsTestStepList(List<UsTestStep> testSteps) {
		Boolean result = false;
		if (testSteps != null) {
			testStepRepository.saveAll(testSteps);
			result = true;
		}
		return result;

	}

	@Override
	public Boolean updateUsTestStepState(Long id, TestStepState state) {
		Boolean result = false;
		UsTestStep ts = getUsTestStepById(id);
		if (ts != null) {
			if (state == null) {
				ts.setTestStepState(null);
			} else {
				ts.setTestStepState(state);
			}
			testStepRepository.save(ts);
			result = true;
		}
		return result;
	}

}
