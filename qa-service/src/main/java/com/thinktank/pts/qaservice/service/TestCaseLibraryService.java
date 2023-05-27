package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.dto.DragAndDropTestCaseDTO;
import com.thinktank.pts.qaservice.enums.TestCaseState;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestStepLibrary;

public interface TestCaseLibraryService {

	List<TestCaseLibrary> filterWithState(TestCaseState state);

	List<TestCaseLibrary> getAllTestCaseLibrary();

	TestCaseLibrary getTestCaseLibraryById(long id);

	List<TestCaseLibrary> getTestCaseLibraryByIdProduct(long productId);

	void addTestCaseLibrary(TestCaseLibrary t);

	TestCaseLibrary updateTestCaseLibrary(long id, TestCaseLibrary testCaseLibrary);

	Boolean deleteTestCaseLibrary(long id);

	List<TestCaseLibrary> getTestCasesLibraryByFolderId(long folderId);

	TestCaseLibrary copyTestCaseLibrary(long testCaseLibraryId);

	TestCaseLibrary cutTestCaseLibrary(long testCaseLibraryId);

	List<TestCaseLibrary> getSearchedTestCaseLibrary(String shortDescription, String testCaseLibraryId, Long folderId,
			Long productId);

	TestCaseLibrary updateTestCaseForImport(long id, TestCaseLibrary testCaseLibrary);
	
	void dragAndDropTestCase(long id ,DragAndDropTestCaseDTO dragAndDropTestCaseDTO);
	
	void deleteTestCase(long id);

	TestCaseLibrary findTestCaseLibraryForProductByIdAndSourceSystemName(long productId,long id, String sourceSystem);

	TestCaseLibrary saveSourceSystemOfTC(TestCaseLibrary testCaseLibrary);

	TestCaseLibrary findTestCaseLibraryForProductByExternalIdAndSourceSystemName(long productId,
			String externalId, String sourceSystem);

	TestStepLibrary updateTestStepLibrary(TestStepLibrary testStepLibrary);

}
