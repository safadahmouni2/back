package com.thinktank.pts.qaservice.repository;

import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.TestEffort;

@RepositoryRestResource
@Repository
public interface TestEffortRepository extends JpaRepository<TestEffort, Long> {

	List<TestEffort> getTestEffortListByTestTestId(Long testId);

	TestEffort getTestEffortById(Long id);

	TestEffort findFirstByTestRunTestRunIdAndEndTimeIsNullOrderByCreatedDesc(Long testRunId);

	TestEffort findFirstByTestRunTestRunIdOrderByCreatedDesc(Long testRunId);

	@Query(value = "SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(t.effort_by_line))) FROM test_effort t WHERE t.test_run_id = :testRunId", nativeQuery = true)
	Time getTotalTestEffortByTestRunId(@Param("testRunId") Long testRunId);

	@Query(value = "SELECT SEC_TO_TIME(SUM(TIME_TO_SEC(t.effort_by_line))) FROM test_effort t WHERE t.test_id = :testId", nativeQuery = true)
	Time getTotalTestEffortByTestId(@Param("testId") Long testId);
}
