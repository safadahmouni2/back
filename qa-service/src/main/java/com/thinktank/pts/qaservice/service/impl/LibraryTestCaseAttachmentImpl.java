package com.thinktank.pts.qaservice.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thinktank.pts.qaservice.mapper.LibraryTestCaseAttachmentMapper;
import com.thinktank.pts.qaservice.model.LibraryTestCaseAttachment;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.repository.LibraryTestCaseAttachmentRepository;
import com.thinktank.pts.qaservice.repository.TestCaseLibraryRepository;
import com.thinktank.pts.qaservice.service.LibraryTestCaseAttachmentService;

@Service
public class LibraryTestCaseAttachmentImpl implements LibraryTestCaseAttachmentService {

	@Autowired
	private LibraryTestCaseAttachmentRepository documentTestCaseLibraryRepository;

	@Autowired
	private TestCaseLibraryRepository testCaseLibraryRepository;

	LibraryTestCaseAttachmentMapper mapper = new LibraryTestCaseAttachmentMapper();

	@Override
	public void store(MultipartFile doc, Long testCaseLibraryId) throws IOException {
		String fileName = StringUtils.cleanPath(doc.getOriginalFilename());
		Optional<LibraryTestCaseAttachment> documentTestCaseLibrary = documentTestCaseLibraryRepository
				.findByFileName(fileName);
		if (documentTestCaseLibrary.isPresent()) {
			deleteDocumentTestCaseLibrary(documentTestCaseLibrary.get().getId());
		}
		LibraryTestCaseAttachment document = new LibraryTestCaseAttachment();
		TestCaseLibrary testCaseLibrary = testCaseLibraryRepository.findByTestCaseLibraryId(testCaseLibraryId);
		document.setFileName(fileName);
		document.setData(doc.getBytes());
		document.setType(doc.getContentType());
		document.setUploadDate(LocalDateTime.now());
		document.setTestCaseLibrary(testCaseLibrary);

		documentTestCaseLibraryRepository.save(document);
	}

	public Optional<LibraryTestCaseAttachment> download(Long fileId) {
		return documentTestCaseLibraryRepository.findById(fileId);
	}

	@Override
	public List<LibraryTestCaseAttachment> getDocumentTestCaseLibraryByTestCaseLibraryId(Long testCaseLibraryId) {
		return documentTestCaseLibraryRepository
				.getDocumentTestCaseLibraryByTestCaseLibraryTestCaseLibraryId(testCaseLibraryId);
	}

	@Override
	public LibraryTestCaseAttachment update(LibraryTestCaseAttachment document) {
		LibraryTestCaseAttachment result = null;
		LibraryTestCaseAttachment docDb = documentTestCaseLibraryRepository.findById(document.getId()).get();
		if (docDb != null) {
			mapper.patch(document, docDb);
			result = documentTestCaseLibraryRepository.save(docDb);
		}
		return result;
	}

	@Override
	public void deleteDocumentTestCaseLibrary(Long docTestCaseLibraryId) {
		documentTestCaseLibraryRepository.deleteById(docTestCaseLibraryId);
	}

	@Override
	public void deleteAllDocumentTestCaseLibrary(List<LibraryTestCaseAttachment> libraryTestCaseAttachments) {
		documentTestCaseLibraryRepository.deleteAll(libraryTestCaseAttachments);
	}

}
