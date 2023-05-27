package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.enums.TestCaseState;
import com.thinktank.pts.qaservice.model.UsTestCase;

@RepositoryRestResource
@Repository
public interface UsTestCaseRepository extends JpaRepository<UsTestCase, Long> {

	// List<TestCase> findByUserStoryUserStoryId(int userStoryId);

	List<UsTestCase> findByState(TestCaseState state);

	// List<TestCase> findByUserStoryState(UserStoryState state);

	UsTestCase findByUsTestCaseId(long id);

	List<UsTestCase> findByTestCaseLibraryId(long id);

	UsTestCase findBytestCaseLibraryIdAndUserStoryId(Long tcId, Long usId);

	List<UsTestCase> findByFolderFolderId(long folderId);

	List<UsTestCase> findByUserStoryId(long userStoryId);

	List<UsTestCase> findBySprintId(long sprintId);

	List<UsTestCase> findBySprintIdAndState(long sprintId, TestCaseState state);

	List<UsTestCase> findByUserStoryIdIn(List<Long> userStoryListId);

}
