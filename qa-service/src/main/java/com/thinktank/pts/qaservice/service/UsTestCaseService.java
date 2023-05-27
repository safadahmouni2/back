package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.enums.TestCaseState;
import com.thinktank.pts.qaservice.model.UsTestCase;

public interface UsTestCaseService {

	List<UsTestCase> filterWithState(TestCaseState state);

	List<UsTestCase> filterWithSprintIdAndState(long sprintId, TestCaseState state);

	List<UsTestCase> getAllTestCase();

	List<UsTestCase> getUsTestCaseByTestCaseLibraryId(long id);

	UsTestCase getUsTestCaseById(long id);

	void addTestCase(UsTestCase t);

	UsTestCase updateTestCase(long id, UsTestCase testCase);

	Boolean deleteTestCase(long id);

	Boolean deleteUsTestCaseByTestCaseLibraryIdAndUserStoryId(Long tcId, Long usId);

	List<UsTestCase> getTestCasesByFolderId(long folderId);

	UsTestCase copyTestCase(UsTestCase testCase);

	UsTestCase cutTestCase(long usTestCaseId);

	List<UsTestCase> getTestCaseListByUserStoryId(long userStoryId);

	List<UsTestCase> getTestCaseListBySprintId(long sprintId);

	UsTestCase getUsTestCaseByTestCaseLibraryIdAndUserStoryId(Long tcId, Long usId);

	List<UsTestCase> getTestCaseByUserStoryId(List<Long> userStoryListId);
	
}
