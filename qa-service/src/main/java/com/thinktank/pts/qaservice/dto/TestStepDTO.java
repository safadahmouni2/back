package com.thinktank.pts.qaservice.dto;

import com.thinktank.pts.qaservice.enums.TestStepState;

public class TestStepDTO {

	private Long id;
	private String stepDescription;
	private String expectedResult;
	private int stepOrder;
	private TestStepState testStepState;
	private Long usTestCaseId;
	private Long testStepLibraryId;
	private Long testCaseLibraryId;

	private Long testCaseChangesId;

	public Long getTestCaseChangesId() {
		return testCaseChangesId;
	}

	public void setTestCaseChangesId(Long testCaseChangesId) {
		this.testCaseChangesId = testCaseChangesId;
	}

	public Long getTestStepLibraryId() {
		return testStepLibraryId;
	}

	public void setTestStepLibraryId(Long testStepLibraryId) {
		this.testStepLibraryId = testStepLibraryId;
	}

	private Long testId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TestStepState getTestStepState() {
		return testStepState;
	}

	public void setTestStepState(TestStepState testStepState) {
		this.testStepState = testStepState;
	}

	public Long getUsTestCaseId() {
		return usTestCaseId;
	}

	public void setUsTestCaseId(Long usTestCaseId) {
		this.usTestCaseId = usTestCaseId;
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
}
