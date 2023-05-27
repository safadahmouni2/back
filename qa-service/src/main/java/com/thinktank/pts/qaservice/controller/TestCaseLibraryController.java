package com.thinktank.pts.qaservice.controller;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.DragAndDropTestCaseDTO;
import com.thinktank.pts.qaservice.dto.TestCaseLibraryDTO;
import com.thinktank.pts.qaservice.enums.TestCaseState;
import com.thinktank.pts.qaservice.exception.PtsImportException;
import com.thinktank.pts.qaservice.export.ExcelGeneratorService;
import com.thinktank.pts.qaservice.mapper.TestCaseLibraryMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.Folder;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.service.FolderService;
import com.thinktank.pts.qaservice.service.TestCaseLibraryService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Tag(name = "testCaseLibrary", description = "API for CRUD operations on testCasesLibrary.")
@Slf4j
@Transactional
public class TestCaseLibraryController {

	private final TestCaseLibraryService testCaseLibraryService;

	private final FolderService folderService;

	private final ExcelGeneratorService excelGeneratorService;

	private final TestCaseLibraryMapper mapper = new TestCaseLibraryMapper();

	public TestCaseLibraryController(TestCaseLibraryService testCaseLibraryService,
									 ExcelGeneratorService excelGeneratorService, FolderService folderService) {
		this.testCaseLibraryService = testCaseLibraryService;
		this.excelGeneratorService = excelGeneratorService;
		this.folderService = folderService;
	}

	@GetMapping("/filterLibrary/{state}")
	public List<TestCaseLibraryDTO> filterWithState(@PathVariable(name = "state") TestCaseState state) {

		List<TestCaseLibraryDTO> testCaseLibraryDtoList = new ArrayList<>();
		List<TestCaseLibrary> testCaseLibraryList = testCaseLibraryService.filterWithState(state);
		for (TestCaseLibrary testcaseLibrary : testCaseLibraryList) {
			testCaseLibraryDtoList.add(mapper.mapToDTO(testcaseLibrary));
		}
		return testCaseLibraryDtoList;
	}

	@GetMapping("/AllTestCasesLibrary")
	public List<TestCaseLibraryDTO> getAllTestCasesLibrary() {

		List<TestCaseLibraryDTO> testCaseLibraryDtoList = new ArrayList<>();
		List<TestCaseLibrary> testCaseLibraryList = testCaseLibraryService.getAllTestCaseLibrary();
		for (TestCaseLibrary testcaseLibrary : testCaseLibraryList) {
			testCaseLibraryDtoList.add(mapper.mapToDTO(testcaseLibrary));
		}
		return testCaseLibraryDtoList;
	}

	@GetMapping("/testCaseLibrary/{id}")
	public TestCaseLibraryDTO getTestCaseLibraryById(@PathVariable(name = "id") long id) {
		TestCaseLibrary testCaseLibrary = testCaseLibraryService.getTestCaseLibraryById(id);
		return mapper.mapToDTO(testCaseLibrary);
	}

	@GetMapping("/testCaseLibraryByProductId/{productId}")
	public List<TestCaseLibraryDTO> getTestCaseLibraryByProductId(@PathVariable(name = "productId") long productId) {

		List<TestCaseLibraryDTO> testCaseLibraryDtoList = new ArrayList<>();
		List<TestCaseLibrary> testCasesLibrary = testCaseLibraryService.getTestCaseLibraryByIdProduct(productId);
		for (TestCaseLibrary testcaseLibrary : testCasesLibrary) {
			testCaseLibraryDtoList.add(mapper.mapToDTO(testcaseLibrary));
		}
		return testCaseLibraryDtoList;
	}

	@GetMapping("/testCasesLibrary/folder/{folderId}")
	public List<TestCaseLibraryDTO> getTestCasesLibraryByFolderId(@PathVariable(name = "folderId") long folderId) {

		List<TestCaseLibraryDTO> testCaseLibraryDtoList = new ArrayList<>();
		List<TestCaseLibrary> testCasesLibrary = testCaseLibraryService.getTestCasesLibraryByFolderId(folderId);
		for (TestCaseLibrary testcaseLibrary : testCasesLibrary) {
			testCaseLibraryDtoList.add(mapper.mapToDTO(testcaseLibrary));
		}
		return testCaseLibraryDtoList;
	}

	@PostMapping("/addTestCaseLibrary")
	public ResponseEntity<Long> addTestCaseLibrary(@RequestBody TestCaseLibraryDTO testCaseLibraryDTO,
			@RequestHeader(value = "actor") String userCode) {

		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());

		Long idTestCaseLibrary = null;
		try {
			TestCaseLibrary testCaseLibrary = mapper.mapToEntity(testCaseLibraryDTO);

			if (testCaseLibraryDTO.getFolder() != null) {
				Optional<Folder> optFolder = folderService.getFolder(testCaseLibraryDTO.getFolder().getFolderId());
				if (optFolder.isPresent()) {
					testCaseLibrary.setFolder(optFolder.get());
				}
			}

			testCaseLibrary.setState(TestCaseState.CREATED);
			testCaseLibrary.setCreator(userCode);
			testCaseLibraryService.addTestCaseLibrary(testCaseLibrary);

			idTestCaseLibrary = testCaseLibrary.getTestCaseLibraryId();
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
					.body(idTestCaseLibrary);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
					.body(idTestCaseLibrary);
		}
	}

	@PutMapping("/editTestCaseLibrary/{id}")
	public ResponseEntity<ResponseMessage> updateTestCaseLibrary(@PathVariable(name = "id") long id,
			@RequestBody TestCaseLibraryDTO testCaseLibraryDTO, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";
		try {
			TestCaseLibrary testCaseLibrary = mapper.mapToEntity(testCaseLibraryDTO);
			testCaseLibrary.setModifier(userCode);
			TestCaseLibrary t = testCaseLibraryService.updateTestCaseLibrary(id, testCaseLibrary);
			if (t != null) {
				message = "Test case Library: " + id + " updated successfully";
			}
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
					.body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not update the test case Library: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
					.body(new ResponseMessage(message));
		}
	}

	@DeleteMapping("/deleteTestCaseLibrary/{id}")
	public ResponseEntity<ResponseMessage> deleteTestCaseLibrary(@PathVariable(name = "id") long id) {
		String message = "";
		try {
			  testCaseLibraryService.deleteTestCase(id); 
				message = "Test case Library: " + id + " deleted successfully";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			
		} catch (Exception e) {
			message = "Could not delete the test case Library: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@PostMapping("/testCasesLibrary/{testCaseLibraryId}/copy")
	public ResponseEntity<TestCaseLibraryDTO> copyTestCaseLibrary(
			@PathVariable(name = "testCaseLibraryId") long testCaseLibraryId) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(mapper.mapToDTO(testCaseLibraryService.copyTestCaseLibrary(testCaseLibraryId)));
	}

	@PostMapping("/testCasesLibrary/{testCaseLibraryId}/cut")
	public ResponseEntity<TestCaseLibraryDTO> cutTestCaseLibrary(
			@PathVariable(name = "testCaseLibraryId") long testCaseLibraryId) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(mapper.mapToDTO(testCaseLibraryService.cutTestCaseLibrary(testCaseLibraryId)));
	}

	private List<Long> displayFolderRecursively(List<Folder> folderList, int folderCount, List<Long> listIDFolders,
			List<String> listPath) {
		folderList.forEach(folder -> {
			listIDFolders.add(folder.getFolderId());
			listPath.add(folder.getName());
			List<Folder> folderLi = folderService.getSubFoldersByParentFolderRefId(folder.getFolderId());
			displayFolderRecursively(folderLi, folderCount + 1, listIDFolders, listPath);
			listPath.add("new line");
		});

		return listIDFolders;
	}

	@GetMapping("/test-cases-library/export/product/{productId}")
	public ResponseEntity<Resource> exportProductTestCasesLibrary(
			@Parameter(name = "product id", required = true) @PathVariable("productId") Long productId) {

		log.info("Export Excel of Test cases from product with id: {} start ... ", productId);

		ArrayList<TestCaseLibrary> mergedTCs = new ArrayList<>();

		List<TestCaseLibrary> listTestCaseByProduct = testCaseLibraryService.getTestCaseLibraryByIdProduct(productId);

		if (!CollectionUtils.isEmpty(listTestCaseByProduct)) {
			mergedTCs.addAll(listTestCaseByProduct);
		}
		InputStreamResource file = new InputStreamResource(excelGeneratorService.exportDataTC(mergedTCs));
		log.info("Export Excel of Test cases from product with id: {} Finish ... ", productId);

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=excel.xls")
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

	@GetMapping("/test-cases-library/export/folder/{folderId}")
	public ResponseEntity<Resource> exportFolderTestCasesLibrary(
			@Parameter(name = "folder id", required = true) @PathVariable("folderId") Long folderId,
			@RequestParam String folderName) {

		log.info("Export Excel of Test cases in library start ... ");
		log.info("Export Excel :: Folder id {} " , folderId);

		ArrayList<TestCaseLibrary> mergeTCs = new ArrayList<>();
		List<String> listPath = new ArrayList<>();

		List<Folder> foldersByParent = folderService.getSubFoldersByParentFolderRefId(folderId);
		List<Long> foldersIds = new ArrayList<>();
		foldersIds.add(folderId);
		displayFolderRecursively(foldersByParent, 1, foldersIds, listPath).forEach(folderRef -> {
			List<TestCaseLibrary> listTestCaseBySubFolder = testCaseLibraryService
					.getTestCasesLibraryByFolderId(folderRef);
			mergeTCs.addAll(listTestCaseBySubFolder);
		});
		InputStreamResource file = new InputStreamResource(excelGeneratorService.exportDataTC(mergeTCs));
		log.info("Export Excel of Test cases Finish ... ");

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=excel.xls")
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

	@PostMapping(value = "/test-cases-library/importFolderExcel/{folderId}")
	public ResponseEntity<?> importTcOfFolderInLib(
			@Parameter(name = "folder id", required = true) @PathVariable(name = "folderId") long folderId,
			@Parameter(name = "product id", required = true) @RequestParam("productId") long productId,
			@RequestParam("file") MultipartFile file, @RequestHeader(value = "actor") String userCode,
			@RequestParam String sourceSystem)
			throws Exception,PtsImportException  {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		List<String> listErrorImport;

		log.info("Import Excel of Test cases in library start ... ");
		log.info("Import Excel :: product id {}" , productId);
		log.info("Import Excel :: folder id {} " , folderId);
		log.info("Import Excel :: source system  {}" , sourceSystem);

		listErrorImport = excelGeneratorService.importFromSheet(sourceSystem,file, productId, folderId);

		if (!CollectionUtils.isEmpty(listErrorImport)) {
			String result = listErrorImport.stream().map(String::valueOf).collect(Collectors.joining(",", " ", " "));
			return ResponseEntity.status(HttpStatus.OK)
					.body(new PtsImportException(HttpStatus.OK, result, ZonedDateTime.now(ZoneId.of("Z"))));
		}

		log.info("Import Excel of Test cases in library Finish ... ");
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("File imported successfully"));
	}

	@PostMapping(value = "/test-cases-library/importExcel/{productId}")
	public ResponseEntity<?> importTcOfProductInLib(
			@Parameter(name = "product id", required = true) @PathVariable(name = "productId") long productId,
			@RequestParam("file") MultipartFile file, @RequestHeader(value = "actor") String userCode
	        ,@RequestParam String sourceSystem)
			throws IOException,PtsImportException {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		List<String> listErrorImport;

		log.info("Import Excel :: of Test cases in library start ... ");

		listErrorImport = excelGeneratorService.importFromSheet(sourceSystem,file, productId, null);

		if (!CollectionUtils.isEmpty(listErrorImport)) {
			String result = listErrorImport.stream().map(String::valueOf).collect(Collectors.joining(",", " ", " "));
			return ResponseEntity.status(HttpStatus.OK)
					.body(new PtsImportException(HttpStatus.OK, result, ZonedDateTime.now(ZoneId.of("Z"))));
		}
		log.info("Import Excel of Test cases Finish ... ");
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("File imported successfully"));
	}

	@GetMapping("/library-test-cases")
	public ResponseEntity<List<TestCaseLibraryDTO>> getTestCasesLibrary(
			@RequestParam(value = "shortDescription", required = false) String shortDescription,
			@RequestParam(value = "testCaseLibraryId", required = false) String testCaseLibraryId,
			@RequestParam(value = "folderId", required = false) Long folderId,
			@RequestParam(value = "productId", required = false) Long productId) {

		List<TestCaseLibraryDTO> results = testCaseLibraryService
				.getSearchedTestCaseLibrary(shortDescription, testCaseLibraryId, folderId, productId).stream()
				.map(mapper::mapToDTO).collect(Collectors.toList());

		return ResponseEntity.ok(results);
	}

	@PutMapping("/testCaseLibrary/dragAndDropTestCase/{id}")
	public ResponseEntity<Long> dargAndDrop(@PathVariable(name = "id") Long id,
			@RequestBody  DragAndDropTestCaseDTO dragAndDropTestCaseDTO, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());

		try {

			testCaseLibraryService.dragAndDropTestCase(id,dragAndDropTestCaseDTO);
						
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
					.body(id);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
					.body(id);
		}
	}

}
