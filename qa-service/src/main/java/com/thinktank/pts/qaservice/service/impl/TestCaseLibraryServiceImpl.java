package com.thinktank.pts.qaservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.dto.DragAndDropTestCaseDTO;
import com.thinktank.pts.qaservice.enums.TestCaseState;
import com.thinktank.pts.qaservice.mapper.FolderMapper;
import com.thinktank.pts.qaservice.mapper.SourceSystemMapper;
import com.thinktank.pts.qaservice.mapper.TestCaseLibraryHistoryMapper;
import com.thinktank.pts.qaservice.mapper.TestCaseLibraryMapper;
import com.thinktank.pts.qaservice.mapper.TestStepLibraryMapper;
import com.thinktank.pts.qaservice.model.Folder;
import com.thinktank.pts.qaservice.model.SourceSystem;
import com.thinktank.pts.qaservice.model.TestCaseChangeRequest;
import com.thinktank.pts.qaservice.model.TestCaseChanges;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestCaseLibraryHistory;
import com.thinktank.pts.qaservice.model.TestStepLibrary;
import com.thinktank.pts.qaservice.model.UsTestCase;
import com.thinktank.pts.qaservice.model.UsTestStep;
import com.thinktank.pts.qaservice.repository.TestCaseLibraryRepository;
import com.thinktank.pts.qaservice.repository.specs.TestCaseLibrarySpecs;
import com.thinktank.pts.qaservice.service.FolderService;
import com.thinktank.pts.qaservice.service.SourceSystemService;
import com.thinktank.pts.qaservice.service.TestCaseChangeRequestService;
import com.thinktank.pts.qaservice.service.TestCaseChangesService;
import com.thinktank.pts.qaservice.service.TestCaseLibraryHistoryService;
import com.thinktank.pts.qaservice.service.TestCaseLibraryService;
import com.thinktank.pts.qaservice.service.TestStepLibraryHistoryService;
import com.thinktank.pts.qaservice.service.TestStepLibraryService;
import com.thinktank.pts.qaservice.service.TestSuiteLibraryService;
import com.thinktank.pts.qaservice.service.UsTestCaseService;
import com.thinktank.pts.qaservice.service.UsTestStepService;

@Service
public class TestCaseLibraryServiceImpl implements TestCaseLibraryService {

	@Autowired
	private TestCaseLibraryRepository testCaseLibraryRepository;

	@Autowired
	private TestStepLibraryService testStepLibraryService;

	@Autowired
	private FolderService folderService;

	@Autowired
	private TestCaseLibraryHistoryService testCaseLibraryHistoryService;

	@Autowired
	private TestCaseLibraryHistoryService testCaLibraryHistoryService;

	@Autowired
	TestStepLibraryHistoryService testStepLibraryHistoryService;

	@Autowired
	private TestCaseChangesService testCaseChangesService;

	@Autowired
	private TestCaseChangeRequestService testCaseChangeRequestService;

	@Autowired
	private UsTestCaseService usTestCaseService;

	@Autowired
	private UsTestStepService usTestStepService;

	@Autowired
	TestSuiteLibraryService testSuiteLibraryService;

	TestCaseLibraryMapper mapper = new TestCaseLibraryMapper();

	TestStepLibraryMapper testStepLibraryMapper = new TestStepLibraryMapper();

	FolderMapper folderMapper = new FolderMapper();

	SourceSystemMapper sourceSystemMapper = new SourceSystemMapper();

	private TestCaseLibraryHistoryMapper testCaseLibraryHistorymapper = new TestCaseLibraryHistoryMapper();

	@Autowired
	private SourceSystemService sourceSystemService;

	@Override
	public List<TestCaseLibrary> filterWithState(TestCaseState state) {
		return testCaseLibraryRepository.findByState(state);
	}

	@Override
	public List<TestCaseLibrary> getAllTestCaseLibrary() {
		return testCaseLibraryRepository.findAll();
	}

	@Override
	public TestCaseLibrary getTestCaseLibraryById(long id) {
		return testCaseLibraryRepository.findByTestCaseLibraryId(id);
	}

	@Override
	public void addTestCaseLibrary(TestCaseLibrary testCaseLibrary) {
		testCaseLibrary = saveSourceSystemOfTC(testCaseLibrary);
		if (testCaseLibrary != null) {
			testCaseLibraryRepository.save(testCaseLibrary);
		}

	}

	@Override
	public TestCaseLibrary updateTestCaseForImport(long id, TestCaseLibrary modifiedTestCaseLibrary) {
		TestCaseLibrary tcUpdated = null;

		TestCaseLibrary savedTestCaseInLibrary = getTestCaseLibraryById(id);
		modifiedTestCaseLibrary = saveSourceSystemOfTC(modifiedTestCaseLibrary);
		if (savedTestCaseInLibrary != null) {
			mapper.patch(modifiedTestCaseLibrary, savedTestCaseInLibrary);
			tcUpdated = testCaseLibraryRepository.save(savedTestCaseInLibrary);

			if (modifiedTestCaseLibrary.getTestStepsLibrary() != null) {

				List<TestStepLibrary> testSteps = new ArrayList<>();

				modifiedTestCaseLibrary.getTestStepsLibrary().forEach(testStep -> {
					testStep.setTestCaseLibrary(savedTestCaseInLibrary);
				});
				// remove all test step and replace with test step in excel file
				testSteps.addAll(modifiedTestCaseLibrary.getTestStepsLibrary());
				testStepLibraryService.deleteTestStepLibraryList(savedTestCaseInLibrary.getTestStepsLibrary());
				testStepLibraryService.addTestStepLibraryList(testSteps);

			}
		}
		return tcUpdated;
	}

	@Override
	public TestCaseLibrary updateTestCaseLibrary(long id, TestCaseLibrary modifiedTestCaseLibrary) {
		TestCaseLibrary result = null;
		TestCaseState newState = modifiedTestCaseLibrary.getState();
		TestCaseLibrary savedTestCaseInLibrary = getTestCaseLibraryById(id);
		if (savedTestCaseInLibrary != null) {
			TestCaseState savedState = savedTestCaseInLibrary.getState();
			boolean testCaseStatusChangedToApproved = (savedState != newState && newState == TestCaseState.APPROVED);
			boolean approvedTestCaseUpdated = (savedState == TestCaseState.APPROVED
					&& newState == TestCaseState.APPROVED);
			if (approvedTestCaseUpdated) {
				modifiedTestCaseLibrary.setState(TestCaseState.TO_BE_APPROVED);
			}
			mapper.patch(modifiedTestCaseLibrary, savedTestCaseInLibrary);
			result = testCaseLibraryRepository.save(savedTestCaseInLibrary);
			if (testCaseStatusChangedToApproved) {
				savedTestCaseInLibrary.setTestCaseVersion(savedTestCaseInLibrary.getTestCaseVersion() + 1);
				addTestCaseLibraryHistory(savedTestCaseInLibrary);
			}

		}
		return result;
	}

	// private methods
	private void addTestCaseLibraryHistory(TestCaseLibrary testCaseLibrary) {

		TestCaseLibraryHistory testCaseLibraryHistory = testCaseLibraryHistorymapper
				.mapTestCaseLibraryToTestCaseLibraryHistory(testCaseLibrary);
		if (testCaseLibrary.getFolder() != null) {
			Optional<Folder> optFolder = folderService.getFolder(testCaseLibrary.getFolder().getFolderId());
			if (optFolder.isPresent()) {
				testCaseLibraryHistory.setFolder(optFolder.get());
			}
		}
		testCaseLibraryHistoryService.addTestCaseLibraryHistory(testCaseLibraryHistory);
	}

	@Override
	public Boolean deleteTestCaseLibrary(long id) {
		Boolean result = false;
		TestCaseLibrary testCaseLibrary = getTestCaseLibraryById(id);
		List<TestStepLibrary> testStepsLibrary = testCaseLibrary.getTestStepsLibrary();
		testStepLibraryService.deleteTestStepLibraryList(testStepsLibrary);
		List<TestCaseLibrary> testCasesLibrary = new ArrayList<>();
		testCasesLibrary.add(testCaseLibrary);
		if (testCaseLibrary != null) {
			testCaseLibraryRepository.deleteInBatch(testCasesLibrary);
			result = true;
		}
		return result;
	}

	@Override
	public List<TestCaseLibrary> getTestCasesLibraryByFolderId(long folderId) {
		return testCaseLibraryRepository.findByFolderFolderId(folderId);
	}

	@Override
	public List<TestCaseLibrary> getTestCaseLibraryByIdProduct(long productId) {
		return testCaseLibraryRepository.findByProductId(productId);
	}

	@Override
	public TestCaseLibrary copyTestCaseLibrary(long testCaseLibraryId) {
		Optional<TestCaseLibrary> optTestCaseLibrary = testCaseLibraryRepository.findById(testCaseLibraryId);
		TestCaseLibrary copiedTestCaseLibrary = null;

		if (optTestCaseLibrary.isPresent()) {
			StringBuilder shortDescription = new StringBuilder("Copied: ");
			TestCaseLibrary testCaseLibrary = optTestCaseLibrary.get();
			shortDescription.append(testCaseLibrary.getShortDescription());
			copiedTestCaseLibrary = new TestCaseLibrary();
			copiedTestCaseLibrary.setShortDescription(shortDescription.toString());
			copiedTestCaseLibrary.setCategory(testCaseLibrary.getCategory());
			copiedTestCaseLibrary.setDescription(testCaseLibrary.getDescription());
			copiedTestCaseLibrary.setExecutionEstimationTime(testCaseLibrary.getExecutionEstimationTime());
			copiedTestCaseLibrary.setPreCondition(testCaseLibrary.getPreCondition());
			copiedTestCaseLibrary.setState(testCaseLibrary.getState());
			copiedTestCaseLibrary.setTestStepsLibrary(testCaseLibrary.getTestStepsLibrary());
			copiedTestCaseLibrary.setVersion(testCaseLibrary.getVersion());
		}

		return copiedTestCaseLibrary;
	}

	@Override
	public TestCaseLibrary cutTestCaseLibrary(long testCaseLibraryId) {
		Optional<TestCaseLibrary> optTestCaseLibrary = testCaseLibraryRepository.findById(testCaseLibraryId);
		TestCaseLibrary cuttedTestCaseLibrary = null;

		if (optTestCaseLibrary.isPresent()) {
			StringBuilder shortDescription = new StringBuilder("Cutted: ");
			TestCaseLibrary testCaseLibrary = optTestCaseLibrary.get();
			shortDescription.append(testCaseLibrary.getShortDescription());
			cuttedTestCaseLibrary = new TestCaseLibrary();
			cuttedTestCaseLibrary.setShortDescription(shortDescription.toString());
			cuttedTestCaseLibrary.setCategory(testCaseLibrary.getCategory());
			cuttedTestCaseLibrary.setDescription(testCaseLibrary.getDescription());
			cuttedTestCaseLibrary.setExecutionEstimationTime(testCaseLibrary.getExecutionEstimationTime());
			cuttedTestCaseLibrary.setPreCondition(testCaseLibrary.getPreCondition());
			cuttedTestCaseLibrary.setState(testCaseLibrary.getState());
			cuttedTestCaseLibrary.setTestStepsLibrary(testCaseLibrary.getTestStepsLibrary());
			cuttedTestCaseLibrary.setVersion(testCaseLibrary.getVersion());
			testCaseLibraryRepository.delete(testCaseLibrary);
		}

		return cuttedTestCaseLibrary;
	}

	@Override
	public List<TestCaseLibrary> getSearchedTestCaseLibrary(String shortDescription, String testCaseLibraryId,
			Long folderId, Long productId) {
		List<Folder> subFolders = new ArrayList<>();
		if (folderId != null) {
			subFolders = folderService.getSubFoldersByFolderId(folderId);
		}
		return testCaseLibraryRepository.findAll(TestCaseLibrarySpecs.searchSpecification(shortDescription,
				testCaseLibraryId, folderId, subFolders, productId));
	}

	@Override
	public void dragAndDropTestCase(long id, DragAndDropTestCaseDTO dragAndDropTestCaseDTO) {
		TestCaseLibrary testCaseLibrary = getTestCaseLibraryById(id);

		if (testCaseLibrary != null) {
			Folder folder = folderService.getFolderByFolderID(dragAndDropTestCaseDTO.getDropFolderId());
			if (folder != null) {
				testCaseLibrary.setFolder(folder);
			}
			testCaseLibrary.setProductId(dragAndDropTestCaseDTO.getProductId());
		}
		addTestCaseLibrary(testCaseLibrary);
	}

	@Override
	public void deleteTestCase(long id) {

		TestCaseLibrary testCaseLibrary = getTestCaseLibraryById(id);
		testCaseLibraryRepository.delete(testCaseLibrary);

		List<TestCaseChanges> testCaseChanges = testCaseChangesService.getTestCaseChangesByTestCaseLibrary(id);
		if (!testCaseChanges.isEmpty()) {
			testCaseChangesService.deleteAllTestCaseChanges(testCaseChanges);
		}

		List<TestCaseChangeRequest> testCaseChangeRequests = testCaseChangeRequestService
				.getTestCaseChangeRequestByTestCaseLibrary(id);
		if (!testCaseChangeRequests.isEmpty()) {
			testCaseChangeRequestService.deleteAllTestCaseChangeRequest(testCaseChangeRequests);
		}

		List<TestCaseLibraryHistory> testCaseLibraryHistories = testCaLibraryHistoryService
				.getTestCaseLibraryHistoryByTestCaseLibraryId(id);
		testCaLibraryHistoryService.deleteAllTestCaseLibraryHistory(testCaseLibraryHistories);

		List<UsTestCase> usTestCases = usTestCaseService.getUsTestCaseByTestCaseLibraryId(id);
		if (!usTestCases.isEmpty()) {
			usTestCases.forEach(usTestCase -> {
				List<UsTestStep> usTestSteps = usTestStepService
						.getUsTestStepsByTestCaseId(usTestCase.getUsTestCaseId());
				if (!usTestSteps.isEmpty()) {
					usTestSteps.forEach(usTestStep -> {
						usTestCase.setTestCaseLibraryId(null);
						usTestCaseService.addTestCase(usTestCase);
						usTestStep.setTestStepLibraryId(null);
						usTestStepService.addUsTestStep(usTestStep);
					});

				}
			});
		}
	}

	@Override
	public TestCaseLibrary findTestCaseLibraryForProductByIdAndSourceSystemName(long productId, long id,
			String sourceSystem) {
		return testCaseLibraryRepository.findTestCaseLibraryForProductByIdAndSourceSystemName(productId, id,
				sourceSystem);
	}

	@Override
	public TestCaseLibrary findTestCaseLibraryForProductByExternalIdAndSourceSystemName(long productId,
			String externalId, String sourceSystem) {
		return testCaseLibraryRepository.findTestCaseLibraryForProductByExternalIdAndSourceSystemName(productId,
				externalId, sourceSystem);
	}

	@Override
	public TestCaseLibrary saveSourceSystemOfTC(TestCaseLibrary testCaseLibrary) {
		SourceSystem sourceSystem = new SourceSystem();
		SourceSystem sourceSystemUpdated = new SourceSystem();
		if (testCaseLibrary.getSourceSystem() != null
				&& testCaseLibrary.getSourceSystem().getSourceSystemName() != null) {
			Optional<SourceSystem> sourceSystemOpt = sourceSystemService
					.getSourceSystemBySourceSystemName(testCaseLibrary.getSourceSystem().getSourceSystemName());
			if (sourceSystemOpt.isPresent()) {
				sourceSystemMapper.patch(testCaseLibrary.getSourceSystem(), sourceSystemOpt.get());
				sourceSystemUpdated = sourceSystemService.saveSourceSystem(sourceSystemOpt.get());
			} else {
				sourceSystem.setSourceSystemName(testCaseLibrary.getSourceSystem().getSourceSystemName());
				sourceSystemUpdated = sourceSystemService.saveSourceSystem(sourceSystem);
			}
		}
		testCaseLibrary.setSourceSystem(sourceSystemUpdated);
		return testCaseLibrary;
	}

	@Override
	public TestStepLibrary updateTestStepLibrary(TestStepLibrary testStepLibrary) {
		TestStepLibrary result = null;
		TestStepLibrary ts = testStepLibraryService.getTestStepLibraryById(testStepLibrary.getId());
		if (ts != null) {
			testStepLibraryMapper.patch(testStepLibrary, ts);
			updateTestCaseLibrary(ts.getTestCaseLibrary().getTestCaseLibraryId(), ts.getTestCaseLibrary());
			result = testStepLibraryService.save(ts);
		}
		return result;
	}
}
