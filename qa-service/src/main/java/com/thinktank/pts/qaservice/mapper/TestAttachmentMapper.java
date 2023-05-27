package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.DocumentTestDTO;
import com.thinktank.pts.qaservice.model.TestAttachment;

public class TestAttachmentMapper {

	private TestMapper testMapper = new TestMapper();

	public TestAttachment mapToEntity(DocumentTestDTO dto) {
		TestAttachment result = null;

		if (dto != null) {
			result = new TestAttachment();
			result.setId(dto.getId());
			result.setFileName(dto.getFileName());
			result.setData(dto.getData());
			result.setShortDescription(dto.getShortDescription());
			result.setType(dto.getType());
			result.setTest(testMapper.mapToEntity(dto.getTest()));
			result.setUploadDate(dto.getUploadDate());
		}

		return result;
	}

	public DocumentTestDTO mapToDTO(TestAttachment entity) {
		DocumentTestDTO result = null;

		if (entity != null) {
			result = new DocumentTestDTO();
			result.setId(entity.getId());
			result.setFileName(entity.getFileName());
			result.setType(entity.getType());
			result.setUploadDate(entity.getUploadDate());
			result.setShortDescription(entity.getShortDescription());
			result.setData(entity.getData());
			result.setTest(testMapper.mapToDTO(entity.getTest()));
		}

		return result;
	}

	public void patch(TestAttachment from, TestAttachment to) {

		if (from != null && to != null) {
			to.setData(from.getData());
			to.setFileName(from.getFileName());
			to.setShortDescription(from.getShortDescription());
			to.setType(from.getType());
			to.setUploadDate(from.getUploadDate());
		}
	}
}
