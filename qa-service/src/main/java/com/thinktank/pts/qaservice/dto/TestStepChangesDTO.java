package com.thinktank.pts.qaservice.dto;

public class TestStepChangesDTO {

	private Long id;
	private Long testCaseChangeRequestId;
	private Long testId;
	private Long testCaseLibraryId;
	private Long testStepLibraryId;
	private String stepDescription;
	private String expectedResult;
	private int stepOrder;

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

}
