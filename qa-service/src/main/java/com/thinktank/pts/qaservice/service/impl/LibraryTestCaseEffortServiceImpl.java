package com.thinktank.pts.qaservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.mapper.LibraryTestCaseEffortMapper;
import com.thinktank.pts.qaservice.model.LibraryTestCaseEffort;
import com.thinktank.pts.qaservice.repository.LibraryTestCaseEffortRepository;
import com.thinktank.pts.qaservice.service.LibraryTestCaseEffortService;

@Service
public class LibraryTestCaseEffortServiceImpl implements LibraryTestCaseEffortService {

	@Autowired
	private LibraryTestCaseEffortRepository libraryTestCaseEffortRepository;

	LibraryTestCaseEffortMapper mapper = new LibraryTestCaseEffortMapper();

	@Override
	public LibraryTestCaseEffort addLibraryTestCaseEffort(LibraryTestCaseEffort testEffort) {
		if (testEffort != null) {
			return libraryTestCaseEffortRepository.save(testEffort);
		}
		return null;
	}

	@Override
	public LibraryTestCaseEffort updateLibraryTestCaseEffort(Long id, LibraryTestCaseEffort testEffort) {
		LibraryTestCaseEffort libraryTestCaseEffort = getLibraryTestCaseEffortById(id);
		if (libraryTestCaseEffort != null) {
			mapper.patch(testEffort, libraryTestCaseEffort);
		}
		return libraryTestCaseEffortRepository.save(libraryTestCaseEffort) ;
	}

	@Override
	public List<LibraryTestCaseEffort> getLibraryTestCaseEffortByLibraryTestCaseId(Long id) {
		return libraryTestCaseEffortRepository.getLibraryTestCaseEffortByTestCaseLibraryTestCaseLibraryId(id);
	}

	@Override
	public LibraryTestCaseEffort getLibraryTestCaseEffortById(Long id) {
		return libraryTestCaseEffortRepository.getLibraryTestCaseEffortById(id);
	}

	@Override
	public Long getTotalTestEffortByLibraryTestCaseEffortId(Long libraryTestCase) {
		return libraryTestCaseEffortRepository.getTotalTestEffortByTestLibraryTestCaseId(libraryTestCase);
	}

	@Override
	public LibraryTestCaseEffort getLastLibraryTestCaseEffortByLibraryTestCaseId(Long libraryTestCase) {
		if (libraryTestCaseEffortRepository
				.findFirstByTestCaseLibraryTestCaseLibraryIdOrderByCreatedDesc(libraryTestCase) != null) {
			return libraryTestCaseEffortRepository
					.findFirstByTestCaseLibraryTestCaseLibraryIdOrderByCreatedDesc(libraryTestCase);
		} else {
			return libraryTestCaseEffortRepository
					.findFirstByTestCaseLibraryTestCaseLibraryIdOrderByCreatedDesc(libraryTestCase);
		}
	}

}
