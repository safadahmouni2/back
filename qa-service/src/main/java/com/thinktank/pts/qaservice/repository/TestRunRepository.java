package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.TestRun;

@RepositoryRestResource
@Repository
public interface TestRunRepository extends JpaRepository<TestRun, Long> {

	TestRun findByTestRunId(Long testRunId);

	List<TestRun> findByInstalledId(Long installedId);

	List<TestRun> findByEnvironmentId(Long envId);

	@Query("SELECT DISTINCT installedId FROM TestRun t WHERE t.environmentId = :envId")
	List<Long> findDistinctInstalledIdByEnvironmentId(@Param("envId") Long envId);

	long countByInstalledId(Long installId);

}
