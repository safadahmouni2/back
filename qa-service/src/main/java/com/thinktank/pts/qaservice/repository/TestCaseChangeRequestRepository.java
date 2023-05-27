package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.enums.ChangeRequestState;
import com.thinktank.pts.qaservice.model.TestCaseChangeRequest;

@RepositoryRestResource
@Repository
public interface TestCaseChangeRequestRepository extends JpaRepository<TestCaseChangeRequest, Long> {

	TestCaseChangeRequest findByTestCaseIdAndTestIdAndProductIdAndState(long testCaseId, long testId, long productId,
			ChangeRequestState state);

	List<TestCaseChangeRequest> findByProductId(long productId);

	int countByProductId(Long productId);

	@Query(value = "SELECT COUNT(*) FROM test_case_change_request chR WHERE chR.product_id = :productId", nativeQuery = true)
	int getTotalTestCaseChangeRequestByProductId(@Param("productId") Long productId);

	List<TestCaseChangeRequest> findByProductIdAndStateIn(long productId, List<ChangeRequestState> asList);

	int countByProductIdAndStateIn(long productId, List<ChangeRequestState> asList);
	
	List<TestCaseChangeRequest> findByTestCaseId(Long testCaseId);
	

}
