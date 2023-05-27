package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.enums.TestState;
import com.thinktank.pts.qaservice.model.Test;

@RepositoryRestResource
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

	List<Test> findByTestRunTestRunId(Long testRunId);

	Test getTestByTestId(Long id);

	Integer countByTestRunTestRunIdAndTestState(Long testRunId, TestState state);

	List<Test> getTestByUserStoryId(Long userStoryId);

	List<Test> getTestByUserStoryIdAndTestCaseLibraryTestCaseLibraryId(Long userStoryId, Long testCaseLibraryId);

	List<Test> findByTestCaseLibraryTestCaseLibraryId(Long id);

	Long countByUserStoryIdAndTestCaseLibraryTestCaseLibraryId(Long usId,Long tcId);

}
