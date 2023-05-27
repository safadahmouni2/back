package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestSuiteLibrary;

public interface TestSuiteLibraryService {

	TestSuiteLibrary addTestSuiteLibrary(TestSuiteLibrary testSuiteLibrary);

	List<TestSuiteLibrary> getTestSuiteLibraryByProductId(Long productId);

	TestSuiteLibrary getTestSuiteLibraryById(Long id);
	
	List<TestSuiteLibrary> getByTestCaseLibraries(List<TestCaseLibrary> testCaseLibraries);

}
