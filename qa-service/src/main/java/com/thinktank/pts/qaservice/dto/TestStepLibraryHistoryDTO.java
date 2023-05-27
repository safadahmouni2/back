package com.thinktank.pts.qaservice.dto;

public class TestStepLibraryHistoryDTO {
	private Long id;
	private Long testStepLibraryId;
	private String stepDescription;
	private String expectedResult;
	private int stepOrder;
	private Long TestCaseLibraryHistoryId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTestStepLibraryId() {
		return testStepLibraryId;
	}

	public void setTestStepLibraryId(Long testStepLibraryId) {
		this.testStepLibraryId = testStepLibraryId;
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

	public int getStepOrder() {
		return stepOrder;
	}

	public void setStepOrder(int stepOrder) {
		this.stepOrder = stepOrder;
	}

	public Long getTestCaseLibraryHistoryId() {
		return TestCaseLibraryHistoryId;
	}

	public void setTestCaseLibraryHistoryId(Long testCaseLibraryHistoryId) {
		TestCaseLibraryHistoryId = testCaseLibraryHistoryId;
	}

}
