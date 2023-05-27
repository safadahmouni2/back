package com.thinktank.pts.qaservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.thinktank.pts.qaservice.enums.TestCaseState;

public class TestCaseLibraryDTO {

	private Long testCaseLibraryId;
	private String shortDescription;
	private String description;
	private String category;
	private TestCaseState state;
	private String preCondition;
	private BigDecimal executionEstimationTime;
	private FolderDTO folder;
	private Long productId;
	private LocalDateTime modified;
	private String modifier;
	private String creator;
	private LocalDateTime created;
	private int testCaseVersion;
	private SourceSystemDTO SourceSystem;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	private List<TestStepLibraryDTO> testStepsLibrary;

	public Long getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public TestCaseState getState() {
		return state;
	}

	public void setState(TestCaseState state) {
		this.state = state;
	}

	public String getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

	public BigDecimal getExecutionEstimationTime() {
		return executionEstimationTime;
	}

	public void setExecutionEstimationTime(BigDecimal executionEstimationTime) {
		this.executionEstimationTime = executionEstimationTime;
	}

	public FolderDTO getFolder() {
		return folder;
	}

	public void setFolder(FolderDTO folder) {
		this.folder = folder;
	}

	public void setTestCaseLibraryId(Long testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
	}

	public List<TestStepLibraryDTO> getTestStepsLibrary() {
		return testStepsLibrary;
	}

	public void setTestStepsLibrary(List<TestStepLibraryDTO> testStepsLibrary) {
		this.testStepsLibrary = testStepsLibrary;
	}

	public int getTestCaseVersion() {
		return testCaseVersion;
	}

	public void setTestCaseVersion(int testCaseVersion) {
		this.testCaseVersion = testCaseVersion;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public SourceSystemDTO getSourceSystem() {
		return SourceSystem;
	}

	public void setSourceSystem(SourceSystemDTO SourceSystem) {
		this.SourceSystem = SourceSystem;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

}
