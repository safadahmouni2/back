package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.DocumentTestCaseLibraryDTO;
import com.thinktank.pts.qaservice.model.LibraryTestCaseAttachment;

public class LibraryTestCaseAttachmentMapper {

	private TestCaseLibraryMapper mapperTestCaseLibrary = new TestCaseLibraryMapper();

	public LibraryTestCaseAttachment mapToEntity(DocumentTestCaseLibraryDTO dto) {
		LibraryTestCaseAttachment result = null;

		if (dto != null) {
			result = new LibraryTestCaseAttachment();
			result.setId(dto.getId());
			result.setFileName(dto.getFileName());
			result.setData(dto.getData());
			result.setShortDescription(dto.getShortDescription());
			result.setType(dto.getType());
			result.setUploadDate(dto.getUploadDate());
			result.setTestCaseLibrary(mapperTestCaseLibrary.mapToEntity(dto.getTestCaseLibrary()));
		}

		return result;
	}

	public DocumentTestCaseLibraryDTO mapToDTO(LibraryTestCaseAttachment entity) {
		DocumentTestCaseLibraryDTO result = null;

		if (entity != null) {
			result = new DocumentTestCaseLibraryDTO();
			result.setId(entity.getId());
			result.setFileName(entity.getFileName());
			result.setType(entity.getType());
			result.setUploadDate(entity.getUploadDate());
			result.setShortDescription(entity.getShortDescription());
			result.setData(entity.getData());
			result.setTestCaseLibrary(mapperTestCaseLibrary.mapToDTO(entity.getTestCaseLibrary()));
		}

		return result;
	}

	public void patch(LibraryTestCaseAttachment from, LibraryTestCaseAttachment to) {

		if (from != null && to != null) {
			to.setData(from.getData());
			to.setFileName(from.getFileName());
			to.setShortDescription(from.getShortDescription());
			to.setType(from.getType());
			to.setUploadDate(from.getUploadDate());
		}
	}
}
