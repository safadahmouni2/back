package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.FolderDTO;
import com.thinktank.pts.qaservice.model.Folder;

/**
 * 
 * @author karabakaa
 *
 */
public class FolderMapper {

	public Folder mapToEntity(FolderDTO dto) {
		Folder result = null;

		if (dto != null) {
			result = new Folder();
			result.setFolderId(dto.getFolderId());
			result.setName(dto.getName());
			result.setParentId(dto.getParentId());
			result.setParentFolderRefId(dto.getParentFolderRefId());
		}

		return result;
	}

	public FolderDTO mapToDTO(Folder entity) {
		FolderDTO result = null;

		if (entity != null) {
			result = new FolderDTO();
			result.setFolderId(entity.getFolderId());
			result.setName(entity.getName());
			result.setParentId(entity.getParentId());
			result.setParentFolderRefId(entity.getParentFolderRefId());
			result.setFolderPath(entity.getFolderPath());
			result.setFolderPathIds(entity.getFolderPathIds());
		}

		return result;
	}
}
