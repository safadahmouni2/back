package com.thinktank.pts.qaservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.thinktank.pts.qaservice.enums.TestCaseChangesState;

public class TestCaseChangesDTO {

	private Long id;
	private Long testCaseChangeRequestId;
	private Long testId;
	private Long testCaseLibraryId;
	private String shortDescription;
	private String description;
	private String category;
	private String preCondition;
	private BigDecimal executionEstimationTime;
	private TestCaseChangesState state;
	private List<TestStepChangesDTO> testStepChanges;

	private LocalDateTime modified;

	private String modifier;

	private String creator;

	private LocalDateTime created;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTestCaseChangeRequestId() {
		return testCaseChangeRequestId;
	}

	public void setTestCaseChangeRequestId(Long testCaseChangeRequestId) {
		this.testCaseChangeRequestId = testCaseChangeRequestId;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public Long getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	public void setTestCaseLibraryId(Long testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
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

	public TestCaseChangesState getState() {
		return state;
	}

	public void setState(TestCaseChangesState state) {
		this.state = state;
	}

	public List<TestStepChangesDTO> getTestStepChanges() {
		return testStepChanges;
	}

	public void setTestStepChanges(List<TestStepChangesDTO> testStepChanges) {
		this.testStepChanges = testStepChanges;
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
}
