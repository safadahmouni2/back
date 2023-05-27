package com.thinktank.pts.qaservice.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thinktank.pts.qaservice.mapper.TestAttachmentMapper;
import com.thinktank.pts.qaservice.model.Test;
import com.thinktank.pts.qaservice.model.TestAttachment;
import com.thinktank.pts.qaservice.repository.TestAttachmentRepository;
import com.thinktank.pts.qaservice.repository.TestRepository;
import com.thinktank.pts.qaservice.service.TestAttachmentService;

/**
 * 
 * @author trabelsimn
 *
 */
@Service
public class TestAttachmentServiceImp implements TestAttachmentService {

	@Autowired
	private TestAttachmentRepository documentRepository;

	@Autowired
	private TestRepository testRepository;

	TestAttachmentMapper mapper = new TestAttachmentMapper();

	@Override
	public void store(MultipartFile doc, Long testId) throws IOException {
		String fileName = StringUtils.cleanPath(doc.getOriginalFilename());
		Optional<TestAttachment> documentTestCaseLibrary = documentRepository.findByFileName(fileName);
		if (documentTestCaseLibrary.isPresent()) {
			deleteDocumentTest(documentTestCaseLibrary.get().getId());
		}
		Test test = testRepository.findById(testId).get();
		TestAttachment document = new TestAttachment();
		document.setFileName(fileName);
		document.setData(doc.getBytes());
		document.setType(doc.getContentType());
		document.setUploadDate(LocalDateTime.now());
		document.setTest(test);

		documentRepository.save(document);
	}

	@Override
	public Optional<TestAttachment> download(Long fileId) {
		return documentRepository.findById(fileId);
	}

	@Override
	public List<TestAttachment> getDocumentTestByTest(Long testId) {
		return documentRepository.getDocumentTestByTestTestId(testId);
	}

	@Override
	public void deleteDocumentTest(Long testDocId) {
		documentRepository.deleteById(testDocId);
	}

	@Override
	public TestAttachment update(TestAttachment doc) {
		TestAttachment result = null;
		TestAttachment docDb = documentRepository.findById(doc.getId()).get();
		if (docDb != null) {
			mapper.patch(doc, docDb);
			result = documentRepository.save(docDb);
		}
		return result;
	}

}
