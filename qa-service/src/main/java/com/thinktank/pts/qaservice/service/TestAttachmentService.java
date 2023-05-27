package com.thinktank.pts.qaservice.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thinktank.pts.qaservice.model.TestAttachment;

/**
 * 
 * @author trabelsimn
 *
 */
@Service
public interface TestAttachmentService {

	public void store(MultipartFile file, Long testId) throws IOException;

	public Optional<TestAttachment> download(Long id);

	List<TestAttachment> getDocumentTestByTest(Long testId);

	public TestAttachment update(TestAttachment doc);

	void deleteDocumentTest(Long testDocId);

}
