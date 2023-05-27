package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.UsTestStep;

@RepositoryRestResource
@Repository
public interface UsTestStepRepository extends JpaRepository<UsTestStep, Long> {

	List<UsTestStep> getUsTestStepListByUsTestCaseUsTestCaseId(Long usTestCaseId);

	UsTestStep getUsTestStepById(Long id);
	
	List<UsTestStep> getByUsTestCase(Long id);
	

}
