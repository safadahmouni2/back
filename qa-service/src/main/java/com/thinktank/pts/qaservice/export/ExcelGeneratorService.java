package com.thinktank.pts.qaservice.export;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thinktank.pts.qaservice.dto.TCWithTstpExportDTO;
import com.thinktank.pts.qaservice.enums.TestCaseState;
import com.thinktank.pts.qaservice.exception.PtsImportException;
import com.thinktank.pts.qaservice.mapper.TestCaseLibraryMapper;
import com.thinktank.pts.qaservice.model.Folder;
import com.thinktank.pts.qaservice.model.SourceSystem;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.model.TestStepLibrary;
import com.thinktank.pts.qaservice.service.FolderService;
import com.thinktank.pts.qaservice.service.SourceSystemService;
import com.thinktank.pts.qaservice.service.TestCaseLibraryService;

import lombok.extern.slf4j.Slf4j;

/**
 * Export/import TestCases Service
 */
@Slf4j
@Service
public class ExcelGeneratorService {

	private static final String FOLDER_PATH = "folderPath";

	private static final String FOLDER_NAME = "folderName";

	String[] columnHeaderExport = { "FOLDER PATH", "SOURCE SYSTEM", "TC ID", "VERSION", "CREATOR", "CREATED",
			"SHORT DESCRIPTION", "PRECONDITION", "CATEGORY", "STATE", "TEST STEP ID", "TEST STEP ORDER", "TEST STEP",
			"EXPECTED RESULT", "VERSION TEST STEP" };

	private final FolderService folderService;

	private final TestCaseLibraryService testCaseLibraryService;

	TestCaseLibraryMapper testCaseLibraryMapper = new TestCaseLibraryMapper();
	List<String> listErrorImport = new ArrayList<>();

	public ExcelGeneratorService(FolderService folderService, TestCaseLibraryService testCaseLibraryService,
			SourceSystemService sourceSystemService) {
		this.folderService = folderService;
		this.testCaseLibraryService = testCaseLibraryService;
	}

	public ByteArrayInputStream exportDataTC(List<TestCaseLibrary> testCaseLibraryList) {

		try (Workbook workBook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			Sheet sheet = workBook.createSheet(WorkbookUtil.createSafeSheetName("TEST_CASES"));
			log.info("Export Excel :: create Sheet of TEST_CASES");
			CellStyle headerStyle = setStyle(workBook);
			Row headerRow = sheet.createRow(0);
			writeHeader(columnHeaderExport, headerRow, headerStyle, sheet, workBook);
			List<TCWithTstpExportDTO> listTCwithTstp = prepareTcAndTestStepList(testCaseLibraryList);
			addDataToWorkbook(listTCwithTstp, sheet, workBook);
			workBook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("Fail to write data of tc to Excel file: {} " + e.getMessage());
		}
	}

	private void createHeader(Row row, int columnCount, String value, CellStyle style, Sheet sheet) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value != null) {
			cell.setCellValue(value);
		}
	}

	/*** Create a cell and put a value in it. ***/
	private void createCell(Row row, int columnCount, Object value, CellStyle style, Sheet sheet) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value != null) {
			Class<?> type = value.getClass();
			switch (type.getName()) {
			case "java.lang.String":
				cell.setCellValue(value.toString());
				cell.setCellStyle(style);
				break;
			case "java.lang.Boolean":
				cell.setCellValue((Boolean) value);
				cell.setCellStyle(style);
				break;
			case "java.lang.Long":
				cell.setCellValue((Long) value);
				cell.setCellStyle(style);
				break;
			case "java.util.Date":
				cell.setCellValue((Date) value);
				break;
			case "java.lang.Double":
				cell.setCellValue((Double) value);
				break;
			case "java.lang.Integer":
				cell.setCellValue((Integer) value);
				break;
			default:
				break;
			}
		}
	}

	private void writeHeader(String[] columnHeader, Row row, CellStyle style, Sheet sheet, Workbook workbook) {
		style = setStyle(workbook);
		// iterate over the column
		for (int i = 0; i < columnHeader.length; i++) {
			createHeader(row, i, columnHeader[i], style, sheet);
			log.info("Export Excel :: display header in Sheet  {} ... ", columnHeader[i]);
		}
	}

	private CellStyle setStyle(Workbook workBook) {
		CellStyle style = workBook.createCellStyle();
		Font headerFont = workBook.createFont();
		headerFont.setBold(true);
		style.setWrapText(true);
		style.setFont(headerFont);
		CellStyle headerCellStyle = workBook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		return style;
	}

	private List<TCWithTstpExportDTO> prepareTcAndTestStepList(List<TestCaseLibrary> testCaseLibraryList) {
		List<TCWithTstpExportDTO> tcWithTstpExportList = new ArrayList<>();

		for (TestCaseLibrary testCaseLibrary : testCaseLibraryList) {

			TCWithTstpExportDTO tcWithTstpExportDTO = new TCWithTstpExportDTO();
			if (!CollectionUtils.isEmpty(testCaseLibrary.getTestStepsLibrary())) {
				for (TestStepLibrary testStepLibrary : testCaseLibrary.getTestStepsLibrary()) {
					log.info("Export Excel :: Test step Description value {} ... ",
							testStepLibrary.getStepDescription());
					tcWithTstpExportDTO = new TCWithTstpExportDTO();
					tcWithTstpExportDTO = this.setTestCase(testCaseLibrary, tcWithTstpExportDTO);
					tcWithTstpExportDTO.setStepDescription(testStepLibrary.getStepDescription());
					tcWithTstpExportDTO.setExpectedResult(testStepLibrary.getExpectedResult());
					tcWithTstpExportDTO.setVersionTestStep(testStepLibrary.getVersion());
					tcWithTstpExportDTO.setStepOrder(testStepLibrary.getStepOrder());
					tcWithTstpExportDTO.setIdTestStep(testStepLibrary.getId());
					tcWithTstpExportList.add(tcWithTstpExportDTO);
				}
			} else {
				tcWithTstpExportDTO = this.setTestCase(testCaseLibrary, tcWithTstpExportDTO);
				tcWithTstpExportList.add(tcWithTstpExportDTO);
			}
		}
		return tcWithTstpExportList;
	}

	private TCWithTstpExportDTO setTestCase(TestCaseLibrary testCaseLibrary, TCWithTstpExportDTO tcWithTstpExportDTO) {
		log.info("Export Excel :: Test case folder Path {} ... ",
				testCaseLibraryMapper.mapToDTO(testCaseLibrary).getFolder().getFolderPath().substring(1));
		tcWithTstpExportDTO.setFolder(testCaseLibrary.getFolder());
		tcWithTstpExportDTO.setSourceSystemName(testCaseLibrary.getSourceSystem().getSourceSystemName());
		tcWithTstpExportDTO.setFolderPath(
				testCaseLibraryMapper.mapToDTO(testCaseLibrary).getFolder().getFolderPath().substring(1));
		tcWithTstpExportDTO.setTestCaseLibraryId(testCaseLibrary.getTestCaseLibraryId());
		tcWithTstpExportDTO.setTestCaseVersion(testCaseLibrary.getTestCaseVersion());
		tcWithTstpExportDTO.setShortDescription(testCaseLibrary.getShortDescription());
		tcWithTstpExportDTO.setPreCondition(testCaseLibrary.getPreCondition());
		tcWithTstpExportDTO.setCategory(testCaseLibrary.getCategory());
		tcWithTstpExportDTO.setState(testCaseLibrary.getState());
		tcWithTstpExportDTO.setCreator(testCaseLibrary.getCreator());
		tcWithTstpExportDTO.setCreated(testCaseLibrary.getCreated().toString());

		return tcWithTstpExportDTO;
	}

	private void addDataToWorkbook(List<TCWithTstpExportDTO> tcWithTstpExportList, Sheet sheet, Workbook workBook) {
		int rowNum = 1;
		CellStyle style = workBook.createCellStyle();
		Font font = workBook.createFont();
		style.setFont(font);
		style.setWrapText(true);
		log.info("Export Excel :: add Test cases data to workbook ... ");
		for (int i = 0; i <= tcWithTstpExportList.size() - 1; i++) {
			log.info("Export Excel :: add Test cases of Folder {}  ... ", tcWithTstpExportList.get(i).getFolderPath());
			log.info("Export Excel :: row {} added to Excel Sheet...", rowNum + i);
			Row row = sheet.createRow(rowNum + i);
			createCell(row, 0, tcWithTstpExportList.get(i).getFolderPath() + "/", style, sheet);
			createCell(row, 1, tcWithTstpExportList.get(i).getSourceSystemName(), style, sheet);

			createCell(row, 2, tcWithTstpExportList.get(i).getTestCaseLibraryId(), style, sheet);
			createCell(row, 3, tcWithTstpExportList.get(i).getTestCaseVersion(), style, sheet);
			createCell(row, 4, tcWithTstpExportList.get(i).getCreator(), style, sheet);
			createCell(row, 5, tcWithTstpExportList.get(i).getCreated(), style, sheet);
			createCell(row, 6, tcWithTstpExportList.get(i).getShortDescription(), style, sheet);
			createCell(row, 7, tcWithTstpExportList.get(i).getPreCondition(), style, sheet);
			createCell(row, 8, tcWithTstpExportList.get(i).getCategory(), style, sheet);
			createCell(row, 9, tcWithTstpExportList.get(i).getState().name(), style, sheet);
			createCell(row, 10, tcWithTstpExportList.get(i).getIdTestStep(), style, sheet);
			if (tcWithTstpExportList.get(i).getIdTestStep() != null) {
				createCell(row, 11, tcWithTstpExportList.get(i).getStepOrder(), style, sheet);
				createCell(row, 12, tcWithTstpExportList.get(i).getStepDescription(), style, sheet);
				createCell(row, 13, tcWithTstpExportList.get(i).getExpectedResult(), style, sheet);
				createCell(row, 14, tcWithTstpExportList.get(i).getVersionTestStep(), style, sheet);
			}

		}

	}

	private String getCellValueAsString(Cell cell) {
		String cellValue = null;
		if (cell != null) {
			CellType cellType = cell.getCellType();
			switch (cellType) {
			case _NONE:
				log.info("IMPORT excel  _NONE " + "\t");
				break;
			case FORMULA:
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			case STRING:
				cellValue = cell.getStringCellValue();
				break;
			case ERROR:
				log.info("IMPORT excel error in cell !");
				break;
			default:
				cellValue = "";
			}
		}
		if (cellValue != null) {
			cellValue = cellValue.trim();
		}
		return cellValue;
	}

	public static List<Map<String, String>> getFolderMap(String path) {
		List<Map<String, String>> folderList = new ArrayList<>();

		String[] folders = path.split("/");
		String folderPath = "";
		for (String folder : folders) {
			if (!folder.equals("")) {
				folderPath += "/" + folder;
				Map<String, String> folderObj = new HashMap<>();
				folderObj.put(FOLDER_NAME, folder);
				folderObj.put(FOLDER_PATH, folderPath);
				folderList.add(folderObj);
			}
		}
		return folderList;
	}

	public Folder createFolder(String path, Long productId) {

		List<Map<String, String>> folderPathMap = getFolderMap(path);

		Folder parentFolder = folderService.getFolderByParentIdAndFolderPath(productId,
				folderPathMap.get(0).get(FOLDER_PATH));
		if (parentFolder == null) {
			Folder folder = new Folder();
			folder.setName(folderPathMap.get(0).get(FOLDER_NAME));
			folder.setParentId(productId);

			parentFolder = folderService.saveOrUpdatefolder(folder);
			log.info(
					"Import excel :: create folder  " + parentFolder.getName() + " in " + parentFolder.getFolderPath());
		} else {
			log.info("Import excel :: folder  " + parentFolder.getName() + " already exist in "
					+ parentFolder.getFolderPath());
		}
		folderPathMap.remove(0);

		for (Map<String, String> folderObj : folderPathMap) {
			String folderName = folderObj.get(FOLDER_NAME);
			String folderPath = folderObj.get(FOLDER_PATH);

			Folder folderExist = folderService.getFolderByParentIdAndFolderPath(productId, folderPath);
			if (folderExist == null) {
				Folder folder = new Folder();
				folder.setName(folderName);
				folder.setParentId(productId);
				folder.setParentFolder(parentFolder);
				folder.setParentFolderRefId(parentFolder.getFolderId());
				parentFolder = folderService.saveOrUpdatefolder(folder);
				log.info("Import excel :: create folder  " + parentFolder.getName() + " in "
						+ parentFolder.getFolderPath());
			} else {
				parentFolder = folderExist;
				log.info("Import excel :: folder  " + parentFolder.getName() + " already exist in "
						+ parentFolder.getFolderPath());
			}
		}
		return parentFolder;
	}

	public List<String> importFromSheet(String sourceSystemName, MultipartFile file, long productId, Long folderId)
			throws IOException, PtsImportException {
		listErrorImport = new ArrayList<>();
		List<TestCaseLibrary> listOfTc = null;
		TCWithTstpExportDTO testCaseLibrary = null;
		TestCaseLibrary tstCaseLib = null;
		TestStepLibrary testStepLibrary;
		long idTC = 0L, idTestStp = 0L;
		String currentFolderName = null;

		HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
		HSSFSheet sheet = workbook.getSheetAt(0);
		if (sheet == null) {
			log.error(" Sheet not found in file ");
			listErrorImport.add("Sheet not found in file ");
		} else {
			int rowcount = sheet.getLastRowNum();
			log.info("Import Excel :: Total Row of imported file is {} ...", rowcount);
		}
		assert sheet != null;
		int rowCount = sheet.getLastRowNum();
		HSSFRow headersRow = sheet.getRow(0);
		int columnCount = headersRow.getLastCellNum();
		if (rowCount == 0) {
			listErrorImport.add("Empty file");
		}
		listOfTc = new ArrayList<>();
		HSSFRow headerRow = sheet.getRow(0);

		try {
			for (int i = 1; i <= rowCount; i++) {

				if (sheet.getRow(i) != null) {
					HSSFRow row = sheet.getRow(i);
					testCaseLibrary = new TCWithTstpExportDTO();
					testStepLibrary = new TestStepLibrary();
					tstCaseLib = new TestCaseLibrary();
					testCaseLibrary.setColumn(i);
					if (sourceSystemName != null) {
						SourceSystem referenceSource = new SourceSystem();
						referenceSource.setSourceSystemName(sourceSystemName);
						testCaseLibrary.setSourceSystem(referenceSource);
					}
					// for each row in sheet
					for (int j = 0; j < columnCount; j++) {
						// get cellule of row
						Cell cell = row.getCell(j);
						testCaseLibrary.setProductId(productId);
						Cell cellheader = headerRow.getCell(j);
						if (cell != null) {

							switch (getCellValueAsString(cellheader)) {
							case "FOLDER PATH":
								if (cell.getStringCellValue() != null) {

									if (cell.getStringCellValue().length() > 1) {
										currentFolderName = cell.getStringCellValue().substring(0,
												cell.getStringCellValue().length() - 1);
									}
									String folderPath = "/" + currentFolderName;
									if (folderId != null) {
										Folder folder = folderService.getFolderByFolderID(folderId);
										folderPath = folder.getFolderPath() + "/" + currentFolderName;
									}
									Folder folder = createFolder(folderPath, productId);
									testCaseLibrary.setFolder(folder);
								}
								break;

							case "EXTERNAL ID":
								testCaseLibrary.setExternalId(getCellValueAsString(cell));
								log.info("Import Excel :: read  EXTERNAL ID cell  {} ",
										testCaseLibrary.getExternalId());
								break;
							case "TC ID":
								idTC = (long) cell.getNumericCellValue();
								if (idTC != 0) {
									testCaseLibrary.setTestCaseLibraryId(idTC);
									log.info("Import Excel :: test Case  id  cell {} !", idTC);
								} else {
									testCaseLibrary.setTestCaseLibraryId(0l);
								}
								break;
							case "CREATOR":
								testCaseLibrary.setCreator(getCellValueAsString(cell));
								break;
							case "SHORT DESCRIPTION":
								testCaseLibrary.setShortDescription(getCellValueAsString(cell));
								log.info("Import Excel :: read  short description cell  {} ",
										testCaseLibrary.getShortDescription());
								break;
							case "PRECONDITION":
								testCaseLibrary.setPreCondition(getCellValueAsString(cell));
								break;
							case "CATEGORY":
								testCaseLibrary.setCategory(getCellValueAsString(cell));
								break;
							case "STATE":
								if (getCellValueAsString(cell) != null
										&& TestCaseState.valueOf(getCellValueAsString(cell)) != null) {
									testCaseLibrary.setTestCaseState(TestCaseState.valueOf(getCellValueAsString(cell)));
								} else {
									testCaseLibrary.setTestCaseState(TestCaseState.CREATED);
								}
								log.info("Import Excel :: Read test case state cell {} !",
										testCaseLibrary.getTestCaseState());
								break;
							case "TEST STEP ID":
								if (cell.getNumericCellValue() != 0) {
									idTestStp = (long) cell.getNumericCellValue();
									testStepLibrary.setId(idTestStp);
									testCaseLibrary.setIdTestStep(idTestStp);
								}
								break;
							case "TEST STEP ORDER":
								if (cell.getNumericCellValue() != 0) {
									int stepOrder = (int) cell.getNumericCellValue();
									testStepLibrary.setStepOrder(stepOrder);
									testCaseLibrary.setStepOrder(stepOrder);
								}
								break;
							case "TEST STEP":
								if (cell.getStringCellValue() != null) {
									testStepLibrary.setStepDescription(getCellValueAsString(cell));
									testCaseLibrary.setStepDescription(getCellValueAsString(cell));
									log.info("Import Excel :: read test step description  cell    {} ",
											testCaseLibrary.getStepDescription());
								}
								break;
							case "EXPECTED RESULT":
								if (cell.getStringCellValue() != null) {
									testStepLibrary.setExpectedResult(getCellValueAsString(cell));
									testCaseLibrary.setExpectedResult(getCellValueAsString(cell));
									log.info("Import Excel :: read expected result  cell   {} ",
											testCaseLibrary.getExpectedResult());
								}
								break;
							default:
								break;
							}
						}
					}

					if (testCaseLibrary.getExternalId() == null || testCaseLibrary.getExternalId() == "") {
						testCaseLibrary.setTestCaseLibraryId(0L);
					}
					// list of test cases
					if (testCaseLibrary.getTestCaseLibraryId() != null) {
						tstCaseLib.setTestCaseLibraryId(testCaseLibrary.getTestCaseLibraryId());
						patchTcForExcel(testCaseLibrary, tstCaseLib);
						listOfTc.add(tstCaseLib);
					}
				}
				log.info("Import excel :: reading row {} finish ", i);
			}
		} catch (IllegalStateException e) {
			listErrorImport.add("Import excel :: cell error value :" + e);
		}
		saveAllTcAndTestSTepInSheet(listOfTc);
		if (!CollectionUtils.isEmpty(listErrorImport)) {
			throw new PtsImportException("" + listErrorImport);
		}
		return listErrorImport;
	}

	private void saveAllTcAndTestSTepInSheet(List<TestCaseLibrary> tcList) {
		log.info("Import excel ::  start saving data from sheet .....");
		Map<Long, List<TestCaseLibrary>> listOfTcGroupedById = tcList.stream()
				.filter(tc -> tc.getTestCaseLibraryId() != null)
				.collect(Collectors.groupingByConcurrent(TestCaseLibrary::getTestCaseLibraryId));

		if (!CollectionUtils.isEmpty(listOfTcGroupedById)) {
			listOfTcGroupedById.forEach((idTC, testCases) -> {

				if (idTC == 0) {

					log.info("Import excel ::  add new test case.....");
					Map<String, Map<String, List<TestCaseLibrary>>> listOfTcGroupedByShortDescAndFullPath = testCases
							.stream().filter(tc -> tc.getTestCaseLibraryId() != null)
							.collect(Collectors.groupingBy(tc -> tc.getFolder().getFolderPath(),
									Collectors.groupingBy(TestCaseLibrary::getShortDescription)));

					listOfTcGroupedByShortDescAndFullPath
							.forEach((path, listOfTcGroupedByShortDescription) -> listOfTcGroupedByShortDescription
									.forEach((shortDescription, testCaseList) -> {

										List<TestStepLibrary> stepLibraryList = new ArrayList<>();
										testCaseList.forEach(testCase -> {
											if (testCase.getTestStepsLibrary() != null) {
												stepLibraryList.addAll(testCase.getTestStepsLibrary());
											}
										});
										TestCaseLibrary testCaseLibrary = testCaseList.get(0);
										if (!CollectionUtils.isEmpty(stepLibraryList)) {
											stepLibraryList.forEach(stp -> {
												stp.setStepOrder(stepLibraryList.indexOf(stp) + 1);
												stp.setTestCaseLibrary(testCaseLibrary);
											});
										}
										TestCaseLibrary savedTestCaseInLibByExternalIdAndSourceSystem = testCaseLibraryService
												.findTestCaseLibraryForProductByExternalIdAndSourceSystemName(
														testCaseLibrary.getProductId(), testCaseLibrary.getExternalId(),
														testCaseLibrary.getSourceSystem().getSourceSystemName());
										// EXTERNAL ID EXISTE AND TEST CASE ID EMPTY

										if (testCaseLibrary.getExternalId() != null
												&& testCaseLibrary.getExternalId() != ""
												&& savedTestCaseInLibByExternalIdAndSourceSystem == null) {
											log.info("Import excel :: cannot update test case External id {} ",
													testCaseLibrary.getExternalId(), " not valid");
											listErrorImport
													.add("Import excel :: cannot update test case with external id  "
															+ testCaseLibrary.getExternalId() + " not valid ");
										} else {
											testCaseLibrary.setTestStepsLibrary(stepLibraryList);
											log.info("Import excel :: create test case {} ",
													testCaseLibrary.getShortDescription(), " in  {}",
													testCaseLibrary.getFolder().getFolderPath());
											this.addTestCase(testCaseLibrary);
										}

									}));
				} else {
					TestCaseLibrary tcUpdate;
					List<TestStepLibrary> stepLibraryList = new ArrayList<>();
					testCases.forEach(tcDuplicate -> {
						if (tcDuplicate.getTestStepsLibrary() != null) {
							stepLibraryList.addAll(tcDuplicate.getTestStepsLibrary());
						}
					});
					TestCaseLibrary testCaseLibrary = testCases.get(0);
					if (!CollectionUtils.isEmpty(stepLibraryList)) {
						stepLibraryList.forEach(tps -> tps.setTestCaseLibrary(testCaseLibrary));
					}
					testCaseLibrary.setTestStepsLibrary(stepLibraryList);

					TestCaseLibrary savedTestCaseInLib = null;

					TestCaseLibrary savedTestCaseInLibByExternalIdAndSourceSystem = testCaseLibraryService
							.findTestCaseLibraryForProductByExternalIdAndSourceSystemName(
									testCaseLibrary.getProductId(), testCaseLibrary.getExternalId(),
									testCaseLibrary.getSourceSystem().getSourceSystemName());
					// test case id and (External Id) exist in excel External Id and not exist in test case table
					// =>Error
					// cannot update test case External not Valid
					if (testCaseLibrary.getExternalId() != null
							&& savedTestCaseInLibByExternalIdAndSourceSystem == null) {
						listErrorImport.add("Import excel :: cannot update test case with external id "
								+ testCaseLibrary.getExternalId() + " not valid ");
					} else {

						if (testCaseLibrary.getExternalId() != null
								&& testCaseLibrary.getSourceSystem().getSourceSystemName() != "PTS_QA") {
							savedTestCaseInLib = savedTestCaseInLibByExternalIdAndSourceSystem;
						} else {
							savedTestCaseInLib = testCaseLibraryService
									.findTestCaseLibraryForProductByIdAndSourceSystemName(
											testCaseLibrary.getProductId(), testCaseLibrary.getTestCaseLibraryId(),
											testCaseLibrary.getSourceSystem().getSourceSystemName());
						}
						if (savedTestCaseInLib == null) {
							addTestCase(testCaseLibrary);
						} else {
							tcUpdate = testCaseLibraryService.updateTestCaseForImport(
									savedTestCaseInLib.getTestCaseLibraryId(), testCaseLibrary);
							if (tcUpdate != null) {
								log.info("Import excel :: test case updated successfully with new source system "
										+ testCaseLibrary.getSourceSystem().getSourceSystemName() + " in folder "
										+ tcUpdate.getFolder().getFolderPath());
							}
						}
					}
				}
			});
		}

	}

	private void addTestCase(TestCaseLibrary tc) {
		testCaseLibraryService.addTestCaseLibrary(tc);
		log.info("Import Excel :: Test case created successfully with short description " + tc.getShortDescription());
	}

	public void patchTcForExcel(TCWithTstpExportDTO from, TestCaseLibrary to) {

		if (from != null && to != null) {
			to.setTestCaseLibraryId(from.getTestCaseLibraryId());
			to.setState(from.getTestCaseState());
			to.setCategory(from.getCategory());
			to.setShortDescription(from.getShortDescription());
			to.setPreCondition(from.getPreCondition());
			to.setProductId(from.getProductId());
			to.setShortDescription(from.getShortDescription());
			to.setState(from.getTestCaseState());
			to.setFolder(from.getFolder());
			to.setSourceSystem(from.getSourceSystem());
			to.setExternalId(from.getExternalId());
			patchStpsForExcel(from, to);

		}
	}

	private void patchStpsForExcel(TCWithTstpExportDTO from, TestCaseLibrary tCaseLib) {
		TestStepLibrary stepLibrary;
		if (from.getStepDescription() != null || from.getExpectedResult() != null) {
			stepLibrary = new TestStepLibrary();
			stepLibrary.setStepDescription(from.getStepDescription());
			stepLibrary.setExpectedResult(from.getExpectedResult());
			stepLibrary.setStepOrder(from.getStepOrder());
			stepLibrary.setTestCaseLibrary(tCaseLib);
			tCaseLib.setTestStepsLibrary(Arrays.asList(stepLibrary));
		}
	}

}