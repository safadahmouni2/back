package com.thinktank.pts.qaservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.enums.TestCaseState;
import com.thinktank.pts.qaservice.mapper.UsTestCaseMapper;
import com.thinktank.pts.qaservice.mapper.UsTestStepMapper;
import com.thinktank.pts.qaservice.model.UsTestCase;
import com.thinktank.pts.qaservice.model.UsTestStep;
import com.thinktank.pts.qaservice.repository.TestCaseLibraryRepository;
import com.thinktank.pts.qaservice.repository.UsTestCaseRepository;
import com.thinktank.pts.qaservice.service.UsTestCaseService;
import com.thinktank.pts.qaservice.service.UsTestStepService;

@Service
public class UsTestCaseServiceImpl implements UsTestCaseService {

	@Autowired
	private UsTestCaseRepository usTestCaseRepository;

	@Autowired
	TestCaseLibraryRepository testCaseLibraryRepository;

	@Autowired
	private UsTestStepService testStepService;

	UsTestCaseMapper mapper = new UsTestCaseMapper();

	UsTestStepMapper mapperStep = new UsTestStepMapper();

	UsTestCaseMapper testCaseMapper = new UsTestCaseMapper();

	@Override
	public List<UsTestCase> getAllTestCase() {
		return usTestCaseRepository.findAll();
	}

	@Override
	public void addTestCase(UsTestCase testCase) {
		if (testCase != null) {
			usTestCaseRepository.save(testCase);
		}

	}

	@Override
	public UsTestCase updateTestCase(long id, UsTestCase testCase) {
		UsTestCase result = null;
		UsTestCase tc = getUsTestCaseById(id);
		if (tc != null) {
			mapper.patch(testCase, tc);
			result = usTestCaseRepository.save(tc);
		}
		return result;
	}

	@Override
	public Boolean deleteTestCase(long id) {
		Boolean result = false;
		UsTestCase testCase = getUsTestCaseById(id);
		List<UsTestStep> testSteps = testCase.getTestSteps();
		testStepService.deleteUsTestStepList(testSteps);
		List<UsTestCase> testCases = new ArrayList<>();
		testCases.add(testCase);
		if (testCase != null) {
			usTestCaseRepository.deleteInBatch(testCases);
			result = true;
		}
		return result;
	}

	@Override
	public UsTestCase getUsTestCaseById(long id) {
		return usTestCaseRepository.findByUsTestCaseId(id);
	}

	@Override
	public List<UsTestCase> getUsTestCaseByTestCaseLibraryId(long id) {
		return usTestCaseRepository.findByTestCaseLibraryId(id);
	}

	@Override
	public List<UsTestCase> filterWithState(TestCaseState state) {
		return usTestCaseRepository.findByState(state);
	}

	@Override
	public List<UsTestCase> filterWithSprintIdAndState(long sprintId, TestCaseState state) {
		return usTestCaseRepository.findBySprintIdAndState(sprintId, state);
	}

	@Override
	public List<UsTestCase> getTestCasesByFolderId(long folderId) {
		return usTestCaseRepository.findByFolderFolderId(folderId);
	}

	/**
	 * copyTestCase: copy test case to test case library
	 */
	@Override
	public UsTestCase copyTestCase(UsTestCase usTestCase) {

		UsTestCase savedUsTestCase = usTestCaseRepository
				.findBytestCaseLibraryIdAndUserStoryId(usTestCase.getTestCaseLibraryId(), usTestCase.getUserStoryId());
		if (savedUsTestCase == null) {
			usTestCaseRepository.save(usTestCase);
		}
		return usTestCase;
	}

	@Override
	public UsTestCase cutTestCase(long usTestCaseId) {
		Optional<UsTestCase> optTestCase = usTestCaseRepository.findById(usTestCaseId);
		UsTestCase cuttedTestCase = null;

		if (optTestCase.isPresent()) {
			StringBuilder shortDescription = new StringBuilder("Cutted: ");
			UsTestCase testCase = optTestCase.get();
			shortDescription.append(testCase.getShortDescription());
			cuttedTestCase = new UsTestCase();
			cuttedTestCase.setShortDescription(shortDescription.toString());
			cuttedTestCase.setCategory(testCase.getCategory());
			cuttedTestCase.setDescription(testCase.getDescription());
			cuttedTestCase.setExecutionEstimationTime(testCase.getExecutionEstimationTime());
			cuttedTestCase.setPreCondition(testCase.getPreCondition());
			cuttedTestCase.setState(testCase.getState());
			cuttedTestCase.setTestSteps(testCase.getTestSteps());
			cuttedTestCase.setVersion(testCase.getVersion());
			usTestCaseRepository.delete(testCase);
		}
		return cuttedTestCase;
	}

	@Override
	public List<UsTestCase> getTestCaseListByUserStoryId(long userStoryId) {
		List<UsTestCase> testCaseList = usTestCaseRepository.findByUserStoryId(userStoryId);
		return testCaseList;
	}

	@Override
	public List<UsTestCase> getTestCaseListBySprintId(long sprintId) {
		List<UsTestCase> testCaseList = usTestCaseRepository.findBySprintId(sprintId);
		return testCaseList;
	}

	@Override
	public UsTestCase getUsTestCaseByTestCaseLibraryIdAndUserStoryId(Long tcId, Long usId) {
		return usTestCaseRepository.findBytestCaseLibraryIdAndUserStoryId(tcId, usId);
	}

	@Override
	public Boolean deleteUsTestCaseByTestCaseLibraryIdAndUserStoryId(Long tcId, Long usId) {
		Boolean result = false;
		UsTestCase testCase = getUsTestCaseByTestCaseLibraryIdAndUserStoryId(tcId, usId);
		List<UsTestStep> testSteps = testCase.getTestSteps();
		testStepService.deleteUsTestStepList(testSteps);
		List<UsTestCase> testCases = new ArrayList<>();
		testCases.add(testCase);
		if (testCase != null) {
			usTestCaseRepository.deleteInBatch(testCases);
			result = true;
		}
		return result;
	}

	@Override
	public List<UsTestCase> getTestCaseByUserStoryId(List<Long> userStoryListId) {
		List<UsTestCase> testCaseList = usTestCaseRepository.findByUserStoryIdIn(userStoryListId);
		return testCaseList;
	}

}
