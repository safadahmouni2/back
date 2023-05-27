package com.thinktank.pts.qaservice.mapper;

import com.thinktank.pts.qaservice.dto.LibraryTestCaseEffortDTO;
import com.thinktank.pts.qaservice.model.LibraryTestCaseEffort;

public class LibraryTestCaseEffortMapper {
	public LibraryTestCaseEffort mapToEntity(LibraryTestCaseEffortDTO dto) {

		LibraryTestCaseEffort result = null;

		if (dto != null) {
			result = new LibraryTestCaseEffort();
			result.setId(dto.getId());
			result.setStartTime(dto.getStartTime());
			result.setEndTime(dto.getEndTime());
			result.setEffortByLine(dto.getEffortByLine());
			result.setDate(dto.getDate());
		}
		return result;
	}

	public LibraryTestCaseEffortDTO mapToDTO(LibraryTestCaseEffort entity) {

		LibraryTestCaseEffortDTO result = null;

		if (entity != null) {
			result = new LibraryTestCaseEffortDTO();
			result.setId(entity.getId());
			result.setStartTime(entity.getStartTime());
			result.setEndTime(entity.getEndTime());
			result.setEffortByLine(entity.getEffortByLine());
			result.setDate(entity.getDate());
			result.setTestCaseLibraryId(entity.getTestCaseLibrary().getTestCaseLibraryId());
		}
		return result;
	}

	public void patch(LibraryTestCaseEffort from, LibraryTestCaseEffort to) {

		if (from != null && to != null) {
			to.setStartTime(from.getStartTime());
			to.setEndTime(from.getEndTime());
			to.setDate(from.getDate());
			to.setEffortByLine(from.getEffortByLine());
			to.setTestCaseLibrary(from.getTestCaseLibrary());;
		}
	}
}
