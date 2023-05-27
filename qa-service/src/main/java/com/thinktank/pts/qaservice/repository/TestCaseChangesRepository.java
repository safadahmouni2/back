package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.TestCaseChanges;

@RepositoryRestResource
@Repository
public interface TestCaseChangesRepository extends JpaRepository<TestCaseChanges, Long> {

	List<TestCaseChanges> findByTestCaseLibraryIdAndTestId(Long tcId, Long tId);

	TestCaseChanges findByTestCaseChangeRequestId(Long testCaseChangeRequestId);

	TestCaseChanges getById(Long id);
	
	List<TestCaseChanges> findByTestCaseLibraryId(Long id);

	boolean existsByTestCaseChangeRequestId(int testCaseChangeRequestId);

}
