package com.thinktank.pts.qaservice.dto;

/**
 * 
 * @author karabakaa
 *
 */
public class FolderDTO {

	private Long folderId;
	private String name;
	private Long parentId;
	private String folderPath;
	private Long parentFolderRefId;
	private String folderPathIds;

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentFolderRefId() {
		return parentFolderRefId;
	}

	public void setParentFolderRefId(Long parentFolderRefId) {
		this.parentFolderRefId = parentFolderRefId;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public String getFolderPathIds() {
		return folderPathIds;
	}

	public void setFolderPathIds(String folderPathIds) {
		this.folderPathIds = folderPathIds;
	}

}
