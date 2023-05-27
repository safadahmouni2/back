package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.LibraryTestCaseEffort;

/**
 * 
 * @author trabelsimn
 *
 */
@Repository
public interface LibraryTestCaseEffortRepository extends JpaRepository<LibraryTestCaseEffort, Long> {

	List<LibraryTestCaseEffort> getLibraryTestCaseEffortByTestCaseLibraryTestCaseLibraryId(Long testCaseLibraryId);
	
	LibraryTestCaseEffort getLibraryTestCaseEffortById(Long id);
	
	LibraryTestCaseEffort findFirstByTestCaseLibraryTestCaseLibraryIdOrderByCreatedDesc(Long testCaseLibraryTestCaseLibraryId);

	@Query(value = "SELECT SUM(TIME_TO_SEC(t.effort_by_line)) FROM LIBRARY_TEST_CASE_EFFORT t WHERE t.library_test_case_id= :libraryTestCaseId", nativeQuery = true)
	Long getTotalTestEffortByTestLibraryTestCaseId(@Param("libraryTestCaseId") Long libraryTestCaseId);
	

}
