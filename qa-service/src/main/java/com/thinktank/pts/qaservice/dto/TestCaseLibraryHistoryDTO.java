package com.thinktank.pts.qaservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.thinktank.pts.qaservice.model.Folder;

public class TestCaseLibraryHistoryDTO {
	private Long id;
	private LocalDateTime date;
	private String creator;
	private Long testCaseLibraryId;
	private Integer testCaseVersion;
	private String shortDescription;
	private String description;
	private String category;
	private String preCondition;
	private BigDecimal executionEstimationTime;
	private Long productId;
	private Folder folder;
	private List<TestStepLibraryHistoryDTO> testStepsLibraryHistory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	public void setTestCaseLibraryId(Long testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
	}

	public Integer getTestCaseVersion() {
		return testCaseVersion;
	}

	public void setTestCaseVersion(Integer testCaseVersion) {
		this.testCaseVersion = testCaseVersion;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public List<TestStepLibraryHistoryDTO> getTestStepsLibraryHistory() {
		return testStepsLibraryHistory;
	}

	public void setTestStepsLibraryHistory(List<TestStepLibraryHistoryDTO> testStepsLibraryHistory) {
		this.testStepsLibraryHistory = testStepsLibraryHistory;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

}
