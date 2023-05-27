package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.TestStepLibrary;

@RepositoryRestResource
@Repository
public interface TestStepLibraryRepository extends JpaRepository<TestStepLibrary, Long> {

	List<TestStepLibrary> getTestStepLibraryListByTestCaseLibraryTestCaseLibraryId(Long testCaseLibraryId);

	TestStepLibrary getTestStepLibraryById(Long id);
}
