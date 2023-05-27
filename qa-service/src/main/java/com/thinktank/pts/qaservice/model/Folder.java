package com.thinktank.pts.qaservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "FOLDER")
public class Folder extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "FOLDER_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long folderId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PARENT_ID")
	private Long parentId;

	@Column(name = "PARENT_FOLDER_REF_ID")
	private Long parentFolderRefId;

	@ManyToOne
	@JoinColumn(name = "PARENT_FOLDER_REF_ID", referencedColumnName = "FOLDER_ID", nullable = true, updatable = false, insertable = false)
	@JsonIgnore
	private Folder parentFolder;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getParentFolderRefId() {
		return parentFolderRefId;
	}

	public void setParentFolderRefId(Long parentFolderRefId) {
		this.parentFolderRefId = parentFolderRefId;
	}

	public String getFolderPath() {
		String result = "/" + this.getName();
		if (this.parentFolder != null) {
			result = parentFolder.getFolderPath() + result;
		}
		 return result;
	}
	public String getFolderPathIds() {
		String result = "/" + this.getFolderId();
		if (this.parentFolder != null) {
			result = parentFolder.getFolderPathIds() + result;
		}
		 return result;
	}
	public Folder getParentFolder() {
		return parentFolder;
	}

	public void setParentFolder(Folder parentFolder) {
		this.parentFolder = parentFolder;
	}
}
