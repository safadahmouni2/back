package com.thinktank.pts.qaservice.service;

import java.util.List;
import java.util.Optional;

import com.thinktank.pts.qaservice.dto.FolderDTO;
import com.thinktank.pts.qaservice.model.Folder;

/**
 * 
 * @author karabakaa
 *
 */
public interface FolderService {

	List<Folder> getFoldersByParentId(Long parentId);

	List<Folder> getSubFoldersByParentFolderRefId(Long parentFolderRefId);

	public List<Folder> getSubFoldersByFolderId(Long folderId);

	Folder getFolderByFolderID(Long folderId);

	Folder saveOrUpdatefolder(Folder folder);

	Folder renameFolder(Long folderId, String folderName);

	void deleteFolder(Long folderId);

	Optional<Folder> getFolder(long folderId);

	List<Folder> getAllFolders();

	List<Folder> getFolderByNameAndProductId(String name, long folderID);
	
	Folder dragAndDropFolder(FolderDTO folderDTO);

	Folder getFolderByParentIdAndFolderPath(Long parentId, String folderPath);

}
