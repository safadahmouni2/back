package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.dto.TestDTO;
import com.thinktank.pts.qaservice.enums.TestState;
import com.thinktank.pts.qaservice.model.Test;

public interface TestService {

	List<Test> getTestsByTestRunId(Long testRunId);

	Test getTestById(Long id);

	Test addTest(Test test);

	boolean updateTestState(Long id, TestState state);

	boolean deleteTest(Long id);

	Integer countByTestRunIdAndState(Long testRunId, TestState state);

	List<Test> getTestByUserStoryId(Long userStoryId);

	List<Test> getTestsByUserStoryIdAndTestCaseLibraryId(Long userStoryId, Long testCaseLibraryId);

	boolean updateTest(TestDTO testDTO);
	
	List<Test> findByTestCaseLibraryId(Long id);

	Long countTestsByUserStoryIdAndTestCaseLibraryTestCaseLibraryId(Long userStoryId, Long tcId);
}
