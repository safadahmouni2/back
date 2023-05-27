package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.TestStep;

@RepositoryRestResource
@Repository
public interface TestStepRepository extends JpaRepository<TestStep, Long> {

	List<TestStep> getTestStepListByTestCaseLibraryTestCaseLibraryId(Long testCaseId);

	TestStep getTestStepById(Long id);

	List<TestStep> getTestStepListByTestTestId(Long testId);
}
