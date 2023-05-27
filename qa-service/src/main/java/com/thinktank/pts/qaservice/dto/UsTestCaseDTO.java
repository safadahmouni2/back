package com.thinktank.pts.qaservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.thinktank.pts.qaservice.enums.TestCaseState;

public class UsTestCaseDTO {

	private Long usTestCaseId;

	private Long testCaseLibraryId;

	private String shortDescription;

	private String description;

	private String category;

	private TestCaseState state;

	private String preCondition;

	private BigDecimal executionEstimationTime;

	private Long userStoryId;

	private FolderDTO folder;

	private Long sprintId;

	private List<UsTestStepDTO> testSteps;

	private int testCaseVersion;

	private LocalDateTime modified;

	private String modifier;

	private String creator;

	private LocalDateTime created;

	private int totalTestResult;

	public int getTestCaseVersion() {
		return testCaseVersion;
	}

	public void setTestCaseVersion(int testCaseVersion) {
		this.testCaseVersion = testCaseVersion;
	}

	public Long getUsTestCaseId() {
		return usTestCaseId;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public void setUsTestCaseId(Long usTestCaseId) {
		this.usTestCaseId = usTestCaseId;
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

	public Long getUserStoryId() {
		return userStoryId;
	}

	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	public List<UsTestStepDTO> getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(List<UsTestStepDTO> testSteps) {
		this.testSteps = testSteps;
	}

	public FolderDTO getFolder() {
		return folder;
	}

	public void setFolder(FolderDTO folder) {
		this.folder = folder;
	}

	public Long getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	public void setTestCaseLibraryId(Long testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
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

	public int getTotalTestResult() {
		return totalTestResult;
	}

	public void setTotalTestResult(int totalTestResult) {
		this.totalTestResult = totalTestResult;
	}
}
