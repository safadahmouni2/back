package com.thinktank.pts.qaservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.model.TestCaseLibraryHistory;
import com.thinktank.pts.qaservice.repository.TestCaseLibraryHistoryRepository;
import com.thinktank.pts.qaservice.service.TestCaseLibraryHistoryService;

@Service
public class TestCaseLibraryHistoryServiceImpl implements TestCaseLibraryHistoryService {

	@Autowired
	private TestCaseLibraryHistoryRepository testCaseLibraryHistoryRepository;

	@Override
	public void addTestCaseLibraryHistory(TestCaseLibraryHistory testCaseLibraryHistory) {
		if (testCaseLibraryHistory != null) {
			testCaseLibraryHistoryRepository.save(testCaseLibraryHistory);
		}

	}

	@Override
	public List<TestCaseLibraryHistory> getTestCaseLibraryHistoryByTestCaseLibraryId(long id) {
		return testCaseLibraryHistoryRepository.findByTestCaseLibraryId(id);
	}

	@Override
	public TestCaseLibraryHistory getTestCaseLibraryHistoryById(long id) {
		return testCaseLibraryHistoryRepository.findById(id);
	}

	@Override
	public void deleteAllTestCaseLibraryHistory(List<TestCaseLibraryHistory> testCaseLibraryHistorys) {
		 testCaseLibraryHistoryRepository.deleteAll(testCaseLibraryHistorys);	
	}

}
