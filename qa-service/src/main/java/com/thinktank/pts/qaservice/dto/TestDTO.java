package com.thinktank.pts.qaservice.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.thinktank.pts.qaservice.enums.TestState;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class TestDTO extends TestCaseLibraryDTO {

	private Long testId;

	private TestState testState;

	private Long testCaseLibraryId;

	private List<TestStepDTO> testSteps;

	private Long userStoryId;

	private int testCaseVersion;

	private Long originSprintId;

	private Long testRunId;

	private Long environmentId;
	
	private String creator;
	
	private LocalDateTime created;
    
	private LocalDateTime modified;
	
	private String modifier;


	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public Long getUserStoryId() {
		return userStoryId;
	}

	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	public TestState getTestState() {
		return testState;
	}

	public void setTestState(TestState testState) {
		this.testState = testState;
	}

	public List<TestStepDTO> getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(List<TestStepDTO> list) {
		this.testSteps = list;
	}

	@Override
	public Long getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	@Override
	public void setTestCaseLibraryId(Long testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
	}

	@Override
	public int getTestCaseVersion() {
		return testCaseVersion;
	}

	@Override
	public void setTestCaseVersion(int testCaseVersion) {
		this.testCaseVersion = testCaseVersion;
	}

	public Long getOriginSprintId() {
		return originSprintId;
	}

	public void setOriginSprintId(Long originSprintId) {
		this.originSprintId = originSprintId;
	}

	public Long getTestRunId() {
		return testRunId;
	}

	public void setTestRunId(Long testRunId) {
		this.testRunId = testRunId;
	}

	public Long getEnvironmentId() {
		return environmentId;
	}

	public void setEnvironmentId(Long environmentId) {
		this.environmentId = environmentId;
	}

}
