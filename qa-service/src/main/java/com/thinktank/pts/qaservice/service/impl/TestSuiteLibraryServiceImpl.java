package com.thinktank.pts.qaservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestSuiteLibrary;
import com.thinktank.pts.qaservice.repository.TestSuiteLibraryRepository;
import com.thinktank.pts.qaservice.service.TestSuiteLibraryService;

@Service
public class TestSuiteLibraryServiceImpl implements TestSuiteLibraryService {

	@Autowired
	private TestSuiteLibraryRepository testSuiteLibraryRepository;

	@Override
	public TestSuiteLibrary addTestSuiteLibrary(TestSuiteLibrary testSuiteLibrary) {
		TestSuiteLibrary result = null;
		if (testSuiteLibrary != null) {
			result = testSuiteLibraryRepository.save(testSuiteLibrary);
		}
		return result;
	}

	@Override
	public List<TestSuiteLibrary> getTestSuiteLibraryByProductId(Long productId) {
		return testSuiteLibraryRepository.findByProductId(productId);
	}

	@Override
	public TestSuiteLibrary getTestSuiteLibraryById(Long id) {
		return testSuiteLibraryRepository.findById(id).get();
	}

	@Override
	public List<TestSuiteLibrary> getByTestCaseLibraries(List<TestCaseLibrary> testCaseLibraries) {
		return testSuiteLibraryRepository.findByTestCaseLibrariesIn(testCaseLibraries);
	}
}
