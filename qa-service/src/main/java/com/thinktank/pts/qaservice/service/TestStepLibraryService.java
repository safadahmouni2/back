package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.enums.TestStepState;
import com.thinktank.pts.qaservice.model.TestStepLibrary;

public interface TestStepLibraryService {

	List<TestStepLibrary> getTestStepsLibraryByTestCaseLibraryId(long id);

	TestStepLibrary getTestStepLibraryById(Long id);

	Boolean addTestStepLibrary(TestStepLibrary testStepLibrary);

	Boolean addTestStepLibraryList(List<TestStepLibrary> testStepsLibrary);

	Boolean deleteTestStepLibrary(Long id);

	Boolean updateTestStepLibraryState(Long id, TestStepState state);

	Boolean deleteTestStepLibraryList(List<TestStepLibrary> testStepsLibrary);

	TestStepLibrary save(TestStepLibrary testStepLibrary);
}
