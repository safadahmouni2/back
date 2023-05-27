package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestSuiteLibrary;

@RepositoryRestResource
@Repository
public interface TestSuiteLibraryRepository extends JpaRepository<TestSuiteLibrary, Long> {

	List<TestSuiteLibrary> findByProductId(Long productId);

	TestSuiteLibrary findByTestSuiteLibraryId(Long testSuiteLibraryId);

	List<TestSuiteLibrary> findByTestCaseLibrariesIn(List<TestCaseLibrary> testCaseLibraries);

}
