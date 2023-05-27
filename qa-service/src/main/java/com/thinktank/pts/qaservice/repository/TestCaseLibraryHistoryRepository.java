package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.TestCaseLibraryHistory;

@RepositoryRestResource
@Repository
public interface TestCaseLibraryHistoryRepository extends JpaRepository<TestCaseLibraryHistory, Long> {

	List<TestCaseLibraryHistory> findByTestCaseLibraryId(long id);

	TestCaseLibraryHistory findById(long id);

	List<TestCaseLibraryHistory> findByFolderFolderId(long folderId);

}
