package com.thinktank.pts.qaservice.dto;

public class TestCaseInputDTO {

	private int testCaseLibraryId;
	private int testCaseVersion;
	private Long userStoryId;
	private Long sprintId;

	public int getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	public void setTestCaseLibraryId(int testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
	}

	public Long getUserStoryId() {
		return userStoryId;
	}

	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public int getTestCaseVersion() {
		return testCaseVersion;
	}

	public void setTestCaseVersion(int testCaseVersion) {
		this.testCaseVersion = testCaseVersion;
	}

}
