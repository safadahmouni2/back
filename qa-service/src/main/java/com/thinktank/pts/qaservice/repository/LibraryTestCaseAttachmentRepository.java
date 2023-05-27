package com.thinktank.pts.qaservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.LibraryTestCaseAttachment;

/**
 * 
 * @author trabelsimn
 *
 */
@Repository
public interface LibraryTestCaseAttachmentRepository extends JpaRepository<LibraryTestCaseAttachment, Long> {

	List<LibraryTestCaseAttachment> getDocumentTestCaseLibraryByTestCaseLibraryTestCaseLibraryId(
			Long testCaseLibraryId);

	Optional<LibraryTestCaseAttachment> findByFileName(String fieldName);

}
