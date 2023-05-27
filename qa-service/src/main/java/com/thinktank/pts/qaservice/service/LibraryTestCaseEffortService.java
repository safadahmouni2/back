package com.thinktank.pts.qaservice.service;

import java.util.List;

import com.thinktank.pts.qaservice.model.LibraryTestCaseEffort;

public interface LibraryTestCaseEffortService {
	
	LibraryTestCaseEffort addLibraryTestCaseEffort(LibraryTestCaseEffort testEffort);

	LibraryTestCaseEffort updateLibraryTestCaseEffort(Long id, LibraryTestCaseEffort testEffort);

	List<LibraryTestCaseEffort> getLibraryTestCaseEffortByLibraryTestCaseId(Long testId);
	
	LibraryTestCaseEffort getLibraryTestCaseEffortById(Long id);

	LibraryTestCaseEffort getLastLibraryTestCaseEffortByLibraryTestCaseId(Long testRunId);

	Long getTotalTestEffortByLibraryTestCaseEffortId(Long libraryTestCaseId);

}
