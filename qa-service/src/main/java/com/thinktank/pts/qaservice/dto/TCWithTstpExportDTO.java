package com.thinktank.pts.qaservice.dto;

import java.util.List;

import com.thinktank.pts.qaservice.enums.TestCaseState;
import com.thinktank.pts.qaservice.model.Folder;
import com.thinktank.pts.qaservice.model.SourceSystem;
import com.thinktank.pts.qaservice.model.TestStepLibrary;

public class TCWithTstpExportDTO {

	private Long testCaseLibraryId;

	private String shortDescription;

	private String category;

	private String preCondition;

	private String folderName;

	private String folderPath;

	private String stepDescription;

	private String expectedResult;

	private String creator;

	private String created;

	private String executionEstimationTime;

	private String effort;

	private TestCaseState state;

	private TestCaseState testCaseState;

	private Integer testCaseVersion;

	private Integer versionTestStep;

	private int stepOrder;

	private Long idTestStep;

	private Folder folder;

	private Long productId;

	private TestStepLibrary testStep;

	private List<TestStepLibraryDTO> testStepsLibrary;

	private TestStepLibraryDTO testStepsLib;

	private SourceSystem sourceSystem;

	private String externalId;

	private String sourceSystemName;

	private int column;

	private int row;

	public Long getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public void setTestCaseLibraryId(Long testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
	}

	public String getStepDescription() {
		return stepDescription;
	}

	public void setStepDescription(String stepDescription) {
		this.stepDescription = stepDescription;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getExecutionEstimationTime() {
		return executionEstimationTime;
	}

	public void setExecutionEstimationTime(String executionEstimationTime) {
		this.executionEstimationTime = executionEstimationTime;
	}

	public String getEffort() {
		return effort;
	}

	public void setEffort(String effort) {
		this.effort = effort;
	}


	public TestCaseState getState() {
		return state;
	}

	public void setState(TestCaseState state) {
		this.state = state;
	}

	public Integer getTestCaseVersion() {
		return testCaseVersion;
	}

	public void setTestCaseVersion(Integer testCaseVersion) {
		this.testCaseVersion = testCaseVersion;
	}

	public Integer getVersionTestStep() {
		return versionTestStep;
	}

	public void setVersionTestStep(Integer versionTestStep) {
		this.versionTestStep = versionTestStep;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public int getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(int stepOrder) {
		this.stepOrder = stepOrder;
	}

	public Long getIdTestStep() {
		return idTestStep;
	}

	public void setIdTestStep(Long idTestStep) {
		this.idTestStep = idTestStep;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public TestStepLibrary getTestStep() {
		return testStep;
	}

	public void setTestStep(TestStepLibrary testStep) {
		this.testStep = testStep;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public TestCaseState getTestCaseState() {
		return testCaseState;
	}

	public void setTestCaseState(TestCaseState testCaseState) {
		this.testCaseState = testCaseState;
	}

	public List<TestStepLibraryDTO> getTestStepsLibrary() {
		return testStepsLibrary;
	}

	public void setTestStepsLibrary(List<TestStepLibraryDTO> testStepsLibrary) {
		this.testStepsLibrary = testStepsLibrary;
	}

	public TestStepLibraryDTO getTestStepsLib() {
		return testStepsLib;
	}

	public void setTestStepsLib(TestStepLibraryDTO testStepsLib) {
		this.testStepsLib = testStepsLib;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public SourceSystem getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(SourceSystem sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getSourceSystemName() {
		return sourceSystemName;
	}

	public void setSourceSystemName(String sourceSystemName) {
		this.sourceSystemName = sourceSystemName;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	@Override
	public String toString() {
		String testCase = "TC From sheet :: " + " | testCaseLibraryId=" + testCaseLibraryId + " |  shortDescription='"
				+ shortDescription + '\'' + " |  folderPath='" + folderPath + '\'' + " | TestStepLibrary :: "
				+ " |  stepDescription='" + stepDescription + '\'' + " |  expectedResult='" + expectedResult + '\''
				+ " | creator='" + creator + '\'' + " |  created='" + created + '\'' + " |  executionEstimationTime='"
				+ executionEstimationTime + '\'' + " | effort='" + effort + '\'' + " |  state='" + state + '\''
				+ " |  testCaseState=" + testCaseState + " |  testCaseVersion=" + testCaseVersion
				+ " |  versionTestStep=" + versionTestStep + " |  stepOrder=" + stepOrder + " |  idTestStep="
				+ idTestStep + " |  folder=" + folder + " |  productId=" + productId;
		String testStp = " |  testStep=" + testStep + " |  testStepsLibrary=" + testStepsLibrary + '|';
		return testCase + "/n" + testStp;
	}
}
