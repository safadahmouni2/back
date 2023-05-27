package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.ProblemDTO;
import com.thinktank.pts.qaservice.model.Problem;

public class ProblemMapper {

	public Problem mapToEntity(ProblemDTO dto) {

		Problem result = null;

		if (dto != null) {
			result = new Problem();
			result.setId(dto.getId());
			result.setUrgency(dto.getUrgency());
			result.setProductName(dto.getProductName());
			result.setShortDescription(dto.getShortDescription());
			result.setLongDescription(dto.getLongDescription());
			result.setProductId(dto.getProductId());
			result.setUserStoryId(dto.getUserStoryId());
			result.setTargetSprintId(dto.getTargetSprintId());
			result.setOriginSprintId(dto.getOriginSprintId());
			result.setProject(dto.getProject());
		}
		return result;
	}

	public ProblemDTO mapToDTO(Problem entity) {

		ProblemDTO result = null;

		if (entity != null) {
			result = new ProblemDTO();
			result.setId(entity.getId());
			result.setUrgency(entity.getUrgency());
			result.setProductName(entity.getProductName());
			result.setShortDescription(entity.getShortDescription());
			result.setLongDescription(entity.getLongDescription());
			result.setProductId(entity.getProductId());
			result.setUserStoryId(entity.getUserStoryId());
			result.setTargetSprintId(entity.getTargetSprintId());
			result.setOriginSprintId(entity.getOriginSprintId());
			result.setProject(entity.getProject());
		}
		return result;
	}

}
