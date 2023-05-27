package com.thinktank.pts.qaservice.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thinktank.pts.qaservice.model.LibraryTestCaseAttachment;

/**
 * 
 * @author trabelsimn
 *
 */
@Service
public interface LibraryTestCaseAttachmentService {

	public void store(MultipartFile doc, Long testCaseLibraryId) throws IOException;

	public Optional<LibraryTestCaseAttachment> download(Long id);

	List<LibraryTestCaseAttachment> getDocumentTestCaseLibraryByTestCaseLibraryId(Long testCaseLibraryId);

	public LibraryTestCaseAttachment update(LibraryTestCaseAttachment doc);

	void deleteDocumentTestCaseLibrary(Long docTestCaseLibraryId);
	
	void deleteAllDocumentTestCaseLibrary(List <LibraryTestCaseAttachment> libraryTestCaseAttachments);
}
