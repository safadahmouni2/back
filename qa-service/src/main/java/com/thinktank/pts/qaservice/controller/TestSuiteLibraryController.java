package com.thinktank.pts.qaservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.dto.TestCaseLibraryDTO;
import com.thinktank.pts.qaservice.dto.TestSuiteLibraryDTO;
import com.thinktank.pts.qaservice.mapper.TestSuiteLibraryMapper;
import com.thinktank.pts.qaservice.model.TestSuiteLibrary;
import com.thinktank.pts.qaservice.service.TestSuiteLibraryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "testSuiteLibrary", description = "API for CRUD operations on testSuiteLibrary.")
@Transactional
public class TestSuiteLibraryController {

	private TestSuiteLibraryMapper mapper = new TestSuiteLibraryMapper();

	@Autowired
	private TestSuiteLibraryService TestSuiteLibraryService;

	@GetMapping("/getTestSuiteLibraryByProductId/{productId}")
	public List<TestSuiteLibraryDTO> getTestCaseListBySprintId(@PathVariable(name = "productId") Long productId) {
		List<TestSuiteLibraryDTO> result = new ArrayList<>();

		List<TestSuiteLibrary> testSuiteLibraryList = TestSuiteLibraryService.getTestSuiteLibraryByProductId(productId);

		for (TestSuiteLibrary testSuiteLibrary : testSuiteLibraryList) {

			result.add(mapper.mapToDTO(testSuiteLibrary));

		}

		return result;
	}

	@GetMapping("/testCaseLibraryByTestSuiteLibraryId/{TestSuiteLibraryId}")
	public List<TestCaseLibraryDTO> testCaseLibraryByTestSuiteLibraryId(
			@PathVariable(name = "TestSuiteLibraryId") long TestSuiteLibraryId) {

		TestSuiteLibrary testSuiteLibrary = TestSuiteLibraryService.getTestSuiteLibraryById(TestSuiteLibraryId);

		TestSuiteLibraryDTO testSuiteLibraryDTO = mapper.mapToDTO(testSuiteLibrary);

		return testSuiteLibraryDTO.getTestCaseLibraries();
	}

}
