package com.thinktank.pts.qaservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.mapper.TestStepChangesMapper;
import com.thinktank.pts.qaservice.model.TestStepChanges;
import com.thinktank.pts.qaservice.repository.TestStepChangesRepository;
import com.thinktank.pts.qaservice.service.TestStepChangesService;

@Service
public class TestStepChangesServiceImpl implements TestStepChangesService {

	@Autowired
	private TestStepChangesRepository testStepChangesRepository;

	private TestStepChangesMapper mapper = new TestStepChangesMapper();

	@Override
	public void addTestStepChanges(TestStepChanges testStepChanges) {
		if (testStepChanges != null) {
			testStepChangesRepository.save(testStepChanges);
		}
	}

	@Override
	public TestStepChanges updateTestStepChanges(TestStepChanges testStepChanges) {
		TestStepChanges result = null;
		Optional<TestStepChanges> savedTestStepChanges = testStepChangesRepository.findById(testStepChanges.getId());
		if (savedTestStepChanges.isPresent()) {
			TestStepChanges testStepChangeToUpdate = savedTestStepChanges.get();
			mapper.patch(testStepChanges, testStepChangeToUpdate);
			testStepChangesRepository.save(testStepChangeToUpdate);
			result = testStepChangeToUpdate;
		}
		return result;
	}

	@Override
	public TestStepChanges getTestStepChangesByTestStepLibraryIdAndTestCaseChangeRequestId(Long testStepLibraryId,
			int testCaseChangeRequestId) {
		return testStepChangesRepository.findByTestStepLibraryIdAndTestCaseChangeRequestId(testStepLibraryId,
				testCaseChangeRequestId);
	}

	@Override
	public Boolean deleteTestStepChanges(Long id) {
		Boolean result = false;
		Optional<TestStepChanges> testStepChanges = testStepChangesRepository.findById(id);
		List<TestStepChanges> testSteps = new ArrayList<>();
		testSteps.add(testStepChanges.get());
		if (testStepChanges.isPresent()) {
			testStepChangesRepository.deleteInBatch(testSteps);
			result = true;
		}
		return result;
	}

	@Override
	public List<TestStepChanges> findByTestCaseLibraryId(Long id) {
		return testStepChangesRepository.findByTestCaseLibraryId(id);
	}

	@Override
	public void deleteAllTestStepChanges(List<TestStepChanges> testStepChanges) {
		testStepChangesRepository.deleteAll(testStepChanges);	
	}

}
