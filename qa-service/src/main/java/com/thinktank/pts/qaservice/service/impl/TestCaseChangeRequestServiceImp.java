package com.thinktank.pts.qaservice.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.thinktank.pts.qaservice.enums.ChangeRequestGlobalActionEnum;
import com.thinktank.pts.qaservice.enums.ChangeRequestState;
import com.thinktank.pts.qaservice.enums.TestRunState;
import com.thinktank.pts.qaservice.mapper.TestCaseChangeRequestMapper;
import com.thinktank.pts.qaservice.model.TestCaseChangeRequest;
import com.thinktank.pts.qaservice.model.TestCaseChanges;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestRun;
import com.thinktank.pts.qaservice.model.TestStepChanges;
import com.thinktank.pts.qaservice.model.TestStepLibrary;
import com.thinktank.pts.qaservice.repository.TestCaseChangeRequestRepository;
import com.thinktank.pts.qaservice.service.TestCaseChangeRequestService;
import com.thinktank.pts.qaservice.service.TestCaseChangesService;
import com.thinktank.pts.qaservice.service.TestCaseLibraryService;
import com.thinktank.pts.qaservice.service.TestService;
import com.thinktank.pts.qaservice.service.TestStepLibraryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TestCaseChangeRequestServiceImp implements TestCaseChangeRequestService {

	private static final String LOG_UPDATE_TEST_CASE_CHANGE_REQUEST = "TC Change Request [id: {}] :: Update TestCaseChangeRequest to status {}";

	private static final String LOG_DELETE_TEST_CASE_CHANGES = "TC Change Request [id: {}] :: Delete TestCaseChanges";

	@Autowired
	private TestCaseChangeRequestRepository testCaseChangeRequestRepository;

	@Autowired
	private TestCaseLibraryService testCaseLibraryService;

	@Autowired
	private TestStepLibraryService testStepLibraryService;

	@Autowired
	private TestCaseChangesService testCaseChangesService;

	@Autowired
	private TestService testService;

	TestCaseChangeRequestMapper mapper = new TestCaseChangeRequestMapper();

	@Override
	public TestCaseChangeRequest addTestCaseChangeRequest(TestCaseChangeRequest testCaseChangeRequest) {
		TestRun testRun = testService.getTestById(testCaseChangeRequest.getTestId()).getTestRun();

		if (testRun.getState() == TestRunState.COMPLETED) {
			return null;
		}
		testCaseChangeRequest.setState(ChangeRequestState.created);
		TestCaseChangeRequest savedChangeRequest = getCreatedTestCaseChangeRequestByTestCaseIdAndTestIdAndProductId(
				testCaseChangeRequest.getTestCaseId(), testCaseChangeRequest.getTestId(),
				testCaseChangeRequest.getProductId());
		if (savedChangeRequest != null) {
			savedChangeRequest.setModifier(testCaseChangeRequest.getCreator());
			savedChangeRequest.setModified(testCaseChangeRequest.getCreated());
			return testCaseChangeRequestRepository.save(savedChangeRequest);
		} else {
			return testCaseChangeRequestRepository.save(testCaseChangeRequest);
		}
	}

	@Override
	public int getTotalTestCaseChangeRequestByProductId(long productId) {
		int totalTestCaseChangeRequest;
		totalTestCaseChangeRequest = testCaseChangeRequestRepository
				.getTotalTestCaseChangeRequestByProductId(productId);
		return totalTestCaseChangeRequest;
	}

	@Override
	public int countTestCaseChangeRequestByProductId(long productId) {
		return testCaseChangeRequestRepository.countByProductId(productId);
	}

	@Override
	public List<TestCaseChangeRequest> getTestCaseChangeRequestsByProductId(long productId) {
		return testCaseChangeRequestRepository.findByProductId(productId);
	}

	@Override
	public List<TestCaseChangeRequest> getTestCaseChangeRequestsByProductIdAndStatus(long productId) {
		return testCaseChangeRequestRepository.findByProductIdAndStateIn(productId,
				Arrays.asList(ChangeRequestState.processing, ChangeRequestState.created));
	}

	@Override
	public int countTestCaseChangeRequestByProductIdAndStatus(long productId) {
		return testCaseChangeRequestRepository.countByProductIdAndStateIn(productId,
				Arrays.asList(ChangeRequestState.processing, ChangeRequestState.created));
	}

	@Override
	public TestCaseChangeRequest updateTestCaseChangeRequestStatus(ChangeRequestState status, Long id) {
		TestCaseChangeRequest result = null;
		Optional<TestCaseChangeRequest> opt = testCaseChangeRequestRepository.findById(id);
		if (opt.isPresent()) {
			result = opt.get();
			result.setState(status);
			testCaseChangeRequestRepository.save(result);
		}
		return result;
	}

	@Override
	public TestCaseChangeRequest getCreatedTestCaseChangeRequestByTestCaseIdAndTestIdAndProductId(long testCaseId,
			long testId, long productId) {
		return testCaseChangeRequestRepository.findByTestCaseIdAndTestIdAndProductIdAndState(testCaseId, testId,
				productId, ChangeRequestState.created);
	}

	@Override
	public List<TestCaseChangeRequest> getTestCaseChangeRequestByTestCaseLibrary(Long id) {
		return testCaseChangeRequestRepository.findByTestCaseId(id);
	}

	@Override
	public void deleteAllTestCaseChangeRequest(List<TestCaseChangeRequest> testCaseChangeRequests) {
		testCaseChangeRequestRepository.deleteAll(testCaseChangeRequests);
	}

	@Override
	public TestCaseChangeRequest treatTestCaseChangeRequest(ChangeRequestGlobalActionEnum action,
			Long testCaseChangeRequestId) {

		Assert.notNull(testCaseChangeRequestId, "treat without testCaseChangeRequestId is not possible!");

		Optional<TestCaseChangeRequest> testCaseChangeRequestToUpdate = testCaseChangeRequestRepository
				.findById(testCaseChangeRequestId);

		if (testCaseChangeRequestToUpdate.isPresent()) {
			TestCaseChanges testCaseChanges = testCaseChangesService
					.getTestCaseChangesByTestCaseChangeRequestId(testCaseChangeRequestId);
			TestCaseLibrary testCaseLibrary = testCaseLibraryService
					.getTestCaseLibraryById(testCaseChangeRequestToUpdate.get().getTestCaseId());

			switch (action) {
			case DONE: {
				log.info(LOG_DELETE_TEST_CASE_CHANGES, testCaseChangeRequestId);
				testCaseChangesService.deleteTestCaseChanges(testCaseChanges.getId());

				log.info(LOG_UPDATE_TEST_CASE_CHANGE_REQUEST, testCaseChangeRequestId, ChangeRequestState.merged);
				updateTestCaseChangeRequestStatus(ChangeRequestState.merged, testCaseChangeRequestId);
				break;
			}
			case ACCEPT_ALL: {
				// update Test Case Library With Test Case Changes and update Library Test steps With Test Steps Changes
				patchTestCaseLibraryWithTestCaseChanges(testCaseChanges, testCaseLibrary);

				log.info(LOG_DELETE_TEST_CASE_CHANGES, testCaseChangeRequestId);
				testCaseChangesService.deleteTestCaseChanges(testCaseChanges.getId());

				log.info(LOG_UPDATE_TEST_CASE_CHANGE_REQUEST, testCaseChangeRequestId, ChangeRequestState.merged);
				updateTestCaseChangeRequestStatus(ChangeRequestState.merged, testCaseChangeRequestId);
				break;
			}
			case REJECT_ALL: {
				log.info(LOG_DELETE_TEST_CASE_CHANGES, testCaseChangeRequestId);
				testCaseChangesService.deleteTestCaseChanges(testCaseChanges.getId());

				log.info(LOG_UPDATE_TEST_CASE_CHANGE_REQUEST, testCaseChangeRequestId, ChangeRequestState.ignored);
				updateTestCaseChangeRequestStatus(ChangeRequestState.ignored, testCaseChangeRequestId);
				break;
			}
			}
		}
		return testCaseChangeRequestToUpdate.get();

	}

	private void patchTestCaseLibraryWithTestCaseChanges(TestCaseChanges testCaseChanges,
			TestCaseLibrary testCaseLibrary) {
		// update Test Case Library With Test Case Changes
		log.info("TC Change Request [id: {}] :: Update Test Case Library [id:{}] With Test Case Changes [id:{}]",
				testCaseChanges.getTestCaseChangeRequestId(), testCaseLibrary.getTestCaseLibraryId(),
				testCaseChanges.getId());
		patchTestCaseLibrary(testCaseChanges, testCaseLibrary);
		// update Library Test steps With Test Steps Changes
		log.info("TC Change Request [id: {}] :: Update Library Test steps With Test Steps Changes",
				testCaseChanges.getTestCaseChangeRequestId());
		patchTestStepLibraryCollection(testCaseChanges.getTestStepChanges(), testCaseLibrary.getTestStepsLibrary(),
				testCaseLibrary);

	}

	private void patchTestCaseLibrary(TestCaseChanges input, TestCaseLibrary entity) {
		entity.setShortDescription(input.getShortDescription());
		entity.setPreCondition(input.getPreCondition());
		entity.setCategory(input.getCategory());
		entity.setExecutionEstimationTime(input.getExecutionEstimationTime());
		testCaseLibraryService.updateTestCaseLibrary(entity.getTestCaseLibraryId(), entity);
	}

	private void patchTestStepLibraryCollection(Collection<TestStepChanges> inputs,
			Collection<TestStepLibrary> entities, TestCaseLibrary parent) {
		if (inputs != null) {
			Collection<TestStepLibrary> entitiesToRemove = new ArrayList<>();
			// remove or update
			for (TestStepLibrary entity : entities) {
				// get appropriate input by id
				Optional<TestStepChanges> optionalDto = getTestStepChangesWithId(inputs, entity.getId());
				if (optionalDto.isPresent()) {
					// entity sent ==> update it
					entity.setStepOrder(optionalDto.get().getStepOrder());
					entity.setStepDescription(optionalDto.get().getStepDescription());
					entity.setExpectedResult(optionalDto.get().getExpectedResult());
					log.info(
							"TC Change Request [id: {}] :: Update Test Case Library Step [id:{}/order:{}/testCaseLibraryId:{}]",
							optionalDto.get().getTestCaseChangeRequestId(), entity.getId(),
							optionalDto.get().getStepOrder(), optionalDto.get().getTestCaseLibraryId());
					testStepLibraryService.save(entity);
				} else {
					// entity not sent in update ==> remove
					entitiesToRemove.add(entity);
				}
			}

			if (!CollectionUtils.isEmpty(entitiesToRemove)) {

			}
			// remove all marked to be removed from entities list
			if (!CollectionUtils.isEmpty(entitiesToRemove)) {
				entitiesToRemove.forEach((entityToRemove) -> {
					log.info("TC Change Request [id: {}] :: Delete Test Case Library Step [id:{}/testCaseLibraryId:{}]",
							inputs.stream().findFirst().orElse(new TestStepChanges()).getTestCaseChangeRequestId(),
							entityToRemove.getId(), parent.getTestCaseLibraryId());
					testStepLibraryService.deleteTestStepLibrary(entityToRemove.getId());
				});

			}

			// add all new entities
			for (TestStepChanges input : inputs) {
				if (!isPersisted(input)
						|| !getTestStepLibraryWithId(entities, input.getTestStepLibraryId()).isPresent()) {
					log.info(
							"Change Request [id: {}] :: Add Test Case Library Step [setepDescription:{}/order:{}/testCaseLibraryId:{}]",
							input.getTestCaseChangeRequestId(), input.getStepDescription(), input.getStepOrder(),
							parent.getTestCaseLibraryId());
					TestStepLibrary newEntity = new TestStepLibrary();
					newEntity.setTestCaseLibrary(parent);
					newEntity.setStepOrder(input.getStepOrder());
					newEntity.setStepDescription(input.getStepDescription());
					newEntity.setExpectedResult(input.getExpectedResult());
					testStepLibraryService.addTestStepLibrary(newEntity);
				}
			}
		}
	}

	private boolean isPersisted(TestStepChanges input) {
		return input.getTestStepLibraryId() != null;
	}

	private Optional<TestStepChanges> getTestStepChangesWithId(Collection<TestStepChanges> inputs, Long id) {
		return inputs.stream().filter(input -> id.equals(input.getTestStepLibraryId())).findFirst();
	}

	private Optional<TestStepLibrary> getTestStepLibraryWithId(Collection<TestStepLibrary> inputs, Long id) {
		return inputs.stream().filter(input -> id.equals(input.getId())).findFirst();
	}

}
