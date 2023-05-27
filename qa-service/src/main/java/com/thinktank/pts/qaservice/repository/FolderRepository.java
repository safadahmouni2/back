package com.thinktank.pts.qaservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.Folder;

/**
 * 
 * @author karabakaa
 *
 */
@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {

	List<Folder> findByParentId(Long productId);

	List<Folder> findByParentFolderRefId(Long parentFolderRefId);

	Folder findByFolderId(Long folderId);

	@Query("SELECT f FROM Folder f WHERE f.name=?1 and f.parentId=?2")
	List<Folder> findByNameAndProductId(String name, Long productId);


}
