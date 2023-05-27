package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.TicketDTO;
import com.thinktank.pts.qaservice.model.Ticket;

public class TicketMapper {

	public Ticket mapToEntity(TicketDTO dto) {

		Ticket result = null;

		if (dto != null) {
			result = new Ticket();
			result.setId(dto.getId());
			result.setResponsible(dto.getResponsible());
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

	public TicketDTO mapToDTO(Ticket entity) {

		TicketDTO result = null;

		if (entity != null) {
			result = new TicketDTO();
			result.setId(entity.getId());
			result.setResponsible(entity.getResponsible());
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
