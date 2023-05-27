package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.SourceSystemDTO;
import com.thinktank.pts.qaservice.model.SourceSystem;

public class SourceSystemMapper {

	public SourceSystem mapToEntity(SourceSystemDTO dto) {
		SourceSystem result = null;

		if (dto != null) {
			result = new SourceSystem();
			result.setSourceSystemName(dto.getSourceSystemName());

		}

		return result;
	}

	public void patch(SourceSystem from, SourceSystem to) {

		if (from != null && to != null) {
			to.setSourceSystemName(from.getSourceSystemName());
			to.setCreator(from.getCreator());
			to.setCreated(from.getCreated());
			to.setModifier(from.getModifier());
			to.setModified(from.getModified());

		}
	}
	public SourceSystemDTO mapToDTO(SourceSystem entity) {
		SourceSystemDTO result = null;
		if (entity != null) {
			result = new SourceSystemDTO();
			result.setSourceSystemName(entity.getSourceSystemName());
		}
		return result;
	}
}