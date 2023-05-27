package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.TestStepLibraryHistory;

@RepositoryRestResource
@Repository
public interface TestStepLibraryHistoryRepository extends JpaRepository<TestStepLibraryHistory, Long> {

	List<TestStepLibraryHistory> getTestStepLibraryHistoryListByTestCaseLibraryHistoryId(Long testCaseLibraryHistoryId);

	TestStepLibraryHistory getTestStepLibraryHistoryById(Long id);
	
	

}
