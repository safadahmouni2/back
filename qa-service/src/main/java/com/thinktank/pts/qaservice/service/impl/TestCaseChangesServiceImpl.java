package com.thinktank.pts.qaservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.enums.ChangeRequestState;
import com.thinktank.pts.qaservice.mapper.TestCaseChangesMapper;
import com.thinktank.pts.qaservice.model.TestCaseChanges;
import com.thinktank.pts.qaservice.repository.TestCaseChangesRepository;
import com.thinktank.pts.qaservice.service.TestCaseChangeRequestService;
import com.thinktank.pts.qaservice.service.TestCaseChangesService;

@Service
public class TestCaseChangesServiceImpl implements TestCaseChangesService {

	@Autowired
	private TestCaseChangesRepository testCaseChangesRepository;

	@Autowired
	private TestCaseChangeRequestService testCaseChangeRequestService;

	private TestCaseChangesMapper mapper = new TestCaseChangesMapper();

	@Override
	public void addTestCaseChanges(TestCaseChanges testCaseChanges) {
		if (testCaseChanges != null) {
			// changes it with optional and is present
			TestCaseChanges testCaseChange = testCaseChangesRepository
					.findByTestCaseChangeRequestId(testCaseChanges.getTestCaseChangeRequestId());
			if (testCaseChange == null) {
				testCaseChangesRepository.save(testCaseChanges);
				testCaseChangeRequestService.updateTestCaseChangeRequestStatus(ChangeRequestState.processing,
						testCaseChanges.getTestCaseChangeRequestId());
			}

		}

	}

	@Override
	public TestCaseChanges updateTestCaseChanges(TestCaseChanges testCaseChanges) {
		TestCaseChanges result = null;
		Optional<TestCaseChanges> savedTestCaseChange = testCaseChangesRepository.findById(testCaseChanges.getId());
		if (savedTestCaseChange.isPresent()) {
			TestCaseChanges testCaseChangeToUpdate = savedTestCaseChange.get();
			mapper.patch(testCaseChanges, testCaseChangeToUpdate);
			testCaseChangesRepository.save(testCaseChangeToUpdate);
			result = testCaseChangeToUpdate;
		}
		return result;
	}

	@Override
	public TestCaseChanges getTestCaseChangesByTestCaseChangeRequestId(Long testCaseChangeRequestId) {
		return testCaseChangesRepository.findByTestCaseChangeRequestId(testCaseChangeRequestId);
	}

	@Override
	public Boolean deleteTestCaseChanges(Long id) {
		Boolean result = false;
		Optional<TestCaseChanges> testCaseChanges = testCaseChangesRepository.findById(id);
		if (testCaseChanges.isPresent()) {
			testCaseChangesRepository.delete(testCaseChanges.get());
			result = true;
		}
		return result;
	}

	@Override
	public List<TestCaseChanges> getTestCaseChangesByTestCaseLibrary(Long id) {
		return testCaseChangesRepository.findByTestCaseLibraryId(id);
	}

	@Override
	public void deleteAllTestCaseChanges(List<TestCaseChanges> testCaseChanges) {
		testCaseChangesRepository.deleteAll(testCaseChanges);

	}

}
