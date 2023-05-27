package com.thinktank.pts.qaservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.enums.TestStepState;
import com.thinktank.pts.qaservice.mapper.TestStepLibraryMapper;
import com.thinktank.pts.qaservice.model.TestStepLibrary;
import com.thinktank.pts.qaservice.repository.TestStepLibraryRepository;
import com.thinktank.pts.qaservice.service.TestStepLibraryService;

@Service
public class TestStepLibraryServiceImp implements TestStepLibraryService {

	@Autowired
	private TestStepLibraryRepository testStepLibraryRepository;

	TestStepLibraryMapper mapper = new TestStepLibraryMapper();

	@Override
	public List<TestStepLibrary> getTestStepsLibraryByTestCaseLibraryId(long testCaseLibraryId) {
		return testStepLibraryRepository.getTestStepLibraryListByTestCaseLibraryTestCaseLibraryId(testCaseLibraryId);
	}

	@Override
	public TestStepLibrary getTestStepLibraryById(Long id) {
		return testStepLibraryRepository.getTestStepLibraryById(id);
	}

	@Override
	public Boolean addTestStepLibrary(TestStepLibrary testStepLibrary) {
		Boolean result = false;
		if (testStepLibrary != null) {
			testStepLibraryRepository.save(testStepLibrary);
			result = true;
		}
		return result;
	}

	@Override
	public Boolean addTestStepLibraryList(List<TestStepLibrary> testStepsLibrary) {
		Boolean result = false;
		if (testStepsLibrary != null) {
			testStepLibraryRepository.saveAll(testStepsLibrary);
			result = true;
		}
		return result;
	}

	@Override
	public TestStepLibrary save(TestStepLibrary entity) {
		return testStepLibraryRepository.save(entity);
	}

	@Override
	public Boolean deleteTestStepLibrary(Long id) {
		Boolean result = false;
		TestStepLibrary testStepLibrary = getTestStepLibraryById(id);
		List<TestStepLibrary> testStepsLibrary = new ArrayList<>();
		testStepsLibrary.add(testStepLibrary);
		if (testStepLibrary != null) {
			testStepLibraryRepository.deleteInBatch(testStepsLibrary);
			result = true;
		}
		return result;
	}

	@Override
	public Boolean updateTestStepLibraryState(Long id, TestStepState state) {
		Boolean result = false;
		TestStepLibrary ts = getTestStepLibraryById(id);
		if (ts != null) {
			if (state == null) {
				ts.setTestStepState(null);
			} else {
				ts.setTestStepState(state);
			}
			testStepLibraryRepository.save(ts);
			result = true;
		}
		return result;
	}

	@Override
	public Boolean deleteTestStepLibraryList(List<TestStepLibrary> testStepsLibrary) {
		Boolean result = false;
		if (testStepsLibrary != null) {
			testStepLibraryRepository.deleteInBatch(testStepsLibrary);
			result = true;
		}
		return result;
	}

}
