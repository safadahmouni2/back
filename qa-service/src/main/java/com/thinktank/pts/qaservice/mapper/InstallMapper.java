package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.InstallDTO;
import com.thinktank.pts.qaservice.model.Install;

public class InstallMapper {

	public Install mapToEntity(InstallDTO dto) {

		Install result = null;

		if (dto != null) {
			result = new Install();
			result.setInstallId(dto.getInstallId());
			result.setDate(dto.getDate());
			result.setInstallVersion(dto.getInstallVersion());
			result.setRequestedRelease(dto.getRequestedRelease());
			result.setDescription(dto.getDescription());
			result.setUrgency(dto.getUrgency());
			result.setState(dto.getState());
			result.setEnvId(dto.getEnvId());
			result.setProductId(dto.getProductId());
			result.setTargetSprintId(dto.getTargetSprintId());
			result.setUserStoryId(dto.getUserStoryId());
			result.setDescription(dto.getDescription());
			result.setState(dto.getState());
			result.setPtsRef(dto.getPtsRef());
			result.setProject(dto.getProject());

		}
		return result;
	}

	public InstallDTO mapToDTO(Install entity) {

		InstallDTO result = null;

		if (entity != null) {
			result = new InstallDTO();
			result.setInstallId(entity.getInstallId());
			result.setDate(entity.getDate());
			result.setInstallVersion(entity.getInstallVersion());
			result.setRequestedRelease(entity.getRequestedRelease());
			result.setUrgency(entity.getUrgency());
			result.setEnvId(entity.getEnvId());
			result.setProductId(entity.getProductId());
			result.setTargetSprintId(entity.getTargetSprintId());
			result.setUserStoryId(entity.getUserStoryId());
			result.setDescription(entity.getDescription());
			result.setState(entity.getState());
			result.setPtsRef(entity.getPtsRef());
			result.setProject(entity.getProject());

		}
		return result;
	}

	public void patch(Install from, Install to) {

		if (from != null && to != null) {
			to.setDate(from.getDate());
			to.setInstallVersion(from.getInstallVersion());
			to.setRequestedRelease(from.getRequestedRelease());
			to.setUrgency(from.getUrgency());
			to.setEnvId(from.getEnvId());
			to.setProductId(from.getProductId());
			to.setDescription(from.getDescription());
			to.setState(from.getState());
			to.setTargetSprintId(from.getTargetSprintId());
			to.setUserStoryId(from.getUserStoryId());
			to.setPtsRef(from.getPtsRef());
			to.setProject(from.getProject());
		}
	}

}
