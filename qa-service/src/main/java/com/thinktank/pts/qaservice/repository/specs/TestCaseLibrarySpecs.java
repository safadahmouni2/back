package com.thinktank.pts.qaservice.repository.specs;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.thinktank.pts.qaservice.model.Folder;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;

/**
 * Specification class for filtering {TestCaseLibrary} entities.
 */

public class TestCaseLibrarySpecs {

	public static Specification<TestCaseLibrary> searchSpecification(String shortDescription, String testCaseLibraryId,
			Long folderId, List<Folder> subFolders, Long productId) {
		Specification<TestCaseLibrary> specification = Specification.where(null);
		if (folderId != null) {
			specification = specification.and(testCaseLibraryInFolder(folderId, subFolders));
		}
		if (productId != null) {
			specification = specification.and(productIdEqualsTo(productId));
		}
		if (shortDescription != null) {
			specification = specification.and(shortDescriptionContains(shortDescription));
		}
		if (testCaseLibraryId != null) {
			specification = specification.and(testCaseLibraryIdStartWith(testCaseLibraryId));
		}
		return specification;
	}

	public static Specification<TestCaseLibrary> shortDescriptionContains(String shortDescription) {
		String escapedSearchString = shortDescription.replace("\\\\", "\\\\\\\\").replace("%", "\\\\%").replace("_",
				"\\\\_");

		return (root, query, cb) -> cb.like(cb.lower(root.get("shortDescription")),
				"%" + escapedSearchString.toLowerCase() + "%", '\\');
	}

	public static Specification<TestCaseLibrary> testCaseLibraryIdStartWith(String testCaseLibraryId) {
		return (root, query, cb) -> cb.like(root.get("testCaseLibraryId").as(String.class), testCaseLibraryId + "%");
	}

	public static Specification<TestCaseLibrary> testCaseLibraryInFolder(Long folderId, List<Folder> subFolders) {
		return (root, query, cb) -> {
			Join<TestCaseLibrary, Folder> join = root.join("folder");
			Path<Long> folderIdPath = join.get("folderId");
			Path<Long> parentFolderRefIdPath = join.get("parentFolderRefId");

			Predicate predicate = cb.or(cb.equal(folderIdPath, folderId), cb.equal(parentFolderRefIdPath, folderId));

			CriteriaBuilder.In<Long> inClause = cb.in(join.get("parentFolderRefId"));
			inClause.value(folderId);

			for (Folder subFolder : subFolders) {
				inClause.value(subFolder.getFolderId());
			}

			predicate = cb.or(predicate, inClause);

			return predicate;
		};
	}

	public static Specification<TestCaseLibrary> productIdEqualsTo(Long productId) {
		return (root, query, cb) -> cb.equal(root.get("productId"), productId);
	}

}
