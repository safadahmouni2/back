package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.enums.ChangeRequestGlobalActionEnum;
import com.thinktank.pts.qaservice.enums.ChangeRequestState;
import com.thinktank.pts.qaservice.model.TestCaseChangeRequest;

public interface TestCaseChangeRequestService {

	TestCaseChangeRequest getCreatedTestCaseChangeRequestByTestCaseIdAndTestIdAndProductId(long testCaseId, long testId,
			long productId);

	List<TestCaseChangeRequest> getTestCaseChangeRequestsByProductIdAndStatus(long productId);

	TestCaseChangeRequest addTestCaseChangeRequest(TestCaseChangeRequest testCaseChangeRequest);

	int getTotalTestCaseChangeRequestByProductId(long productId);

	int countTestCaseChangeRequestByProductId(long productId);

	List<TestCaseChangeRequest> getTestCaseChangeRequestsByProductId(long productId);

	TestCaseChangeRequest updateTestCaseChangeRequestStatus(ChangeRequestState status, Long id);

	int countTestCaseChangeRequestByProductIdAndStatus(long productId);
	
	List<TestCaseChangeRequest> getTestCaseChangeRequestByTestCaseLibrary(Long id);
	
	void deleteAllTestCaseChangeRequest(List<TestCaseChangeRequest> testCaseChangeRequests);

	TestCaseChangeRequest treatTestCaseChangeRequest(ChangeRequestGlobalActionEnum action,
			Long testCaseChangeRequestId);

}
