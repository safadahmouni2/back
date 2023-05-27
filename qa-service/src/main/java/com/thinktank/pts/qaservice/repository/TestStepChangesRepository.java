package com.thinktank.pts.qaservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.TestStepChanges;

@RepositoryRestResource
@Repository
public interface TestStepChangesRepository extends JpaRepository<TestStepChanges, Long> {

	@Override
	Optional<TestStepChanges> findById(Long id);

	TestStepChanges findByTestStepLibraryIdAndTestCaseChangeRequestId(Long testStepLibraryId,
			int testCaseChangeRequestId);

	List<TestStepChanges> findByTestCaseLibraryId(Long id);

}
