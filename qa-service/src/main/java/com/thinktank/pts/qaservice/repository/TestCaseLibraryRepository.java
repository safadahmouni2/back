package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.enums.TestCaseState;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;

@RepositoryRestResource
@Repository
public interface TestCaseLibraryRepository
		extends JpaRepository<TestCaseLibrary, Long>, JpaSpecificationExecutor<TestCaseLibrary> {

	TestCaseLibrary findByTestCaseLibraryId(long testCaseLibraryId);

	List<TestCaseLibrary> findByState(TestCaseState state);

	List<TestCaseLibrary> findByFolderFolderId(long folderId);

	List<TestCaseLibrary> findByProductId(long productId);

	@Query("SELECT tc FROM TestCaseLibrary tc WHERE tc.productId=?1 AND tc.testCaseLibraryId=?2 AND tc.sourceSystemId.sourceSystemName=?3")
	TestCaseLibrary findTestCaseLibraryForProductByIdAndSourceSystemName(long productId, long testCaseLibraryId, String sourceSystemName);

	@Query("SELECT tc FROM TestCaseLibrary tc WHERE  tc.productId=?1 AND tc.externalId=?2 AND tc.sourceSystemId.sourceSystemName=?3")
	TestCaseLibrary findTestCaseLibraryForProductByExternalIdAndSourceSystemName(long productId, String externalId, String sourceSystemName);

}
