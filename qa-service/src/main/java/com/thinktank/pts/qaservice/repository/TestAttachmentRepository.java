package com.thinktank.pts.qaservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.TestAttachment;

/**
 * 
 * @author trabelsimn
 *
 */
@Repository
public interface TestAttachmentRepository extends JpaRepository<TestAttachment, Long> {

	List<TestAttachment> getDocumentTestByTestTestId(Long testId);

	Optional<TestAttachment> findByFileName(String fieldName);

}
