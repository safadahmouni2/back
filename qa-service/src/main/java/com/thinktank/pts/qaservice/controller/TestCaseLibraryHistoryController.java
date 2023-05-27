package com.thinktank.pts.qaservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.dto.TestCaseLibraryHistoryDTO;
import com.thinktank.pts.qaservice.mapper.TestCaseLibraryHistoryMapper;
import com.thinktank.pts.qaservice.service.TestCaseLibraryHistoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "testCaseLibraryHistory", description = "API for CRUD operations on testCaseLibraryHistory.")
@RequestMapping("/testCaseLibraryHistory")
@Transactional
public class TestCaseLibraryHistoryController {

	@Autowired
	private TestCaseLibraryHistoryService testCaseLibraryHistoryService;

	private TestCaseLibraryHistoryMapper mapper = new TestCaseLibraryHistoryMapper();

	@GetMapping
	public List<TestCaseLibraryHistoryDTO> getTestCaseHistoryListByTestCaseId(
			@RequestParam(name = "testCaseId") Long testCaseId) {

		return testCaseLibraryHistoryService.getTestCaseLibraryHistoryByTestCaseLibraryId(testCaseId).stream()
				.map(mapper::mapToDTO).collect(Collectors.toList());
	}

}
