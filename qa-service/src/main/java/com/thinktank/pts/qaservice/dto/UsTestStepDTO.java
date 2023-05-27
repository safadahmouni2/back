package com.thinktank.pts.qaservice.dto;

import com.thinktank.pts.qaservice.enums.TestStepState;

public class UsTestStepDTO {

	private Long id;
	private String stepDescription;
	private String expectedResult;
	private int stepOrder;
	private TestStepState testStepState;
	private Long usTestCaseId;
	private Long testStepLibraryId;

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

	public void setUsTestCaseId(Long UsTestCaseId) {
		this.usTestCaseId = UsTestCaseId;
	}

	public Long getTestStepLibraryId() {
		return testStepLibraryId;
	}

	public void setTestStepLibraryId(Long testStepLibraryId) {
		this.testStepLibraryId = testStepLibraryId;
	}

}
