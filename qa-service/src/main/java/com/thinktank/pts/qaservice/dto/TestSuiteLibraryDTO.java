package com.thinktank.pts.qaservice.dto;

import java.math.BigDecimal;
import java.util.List;

import com.thinktank.pts.qaservice.enums.TestRunState;

public class TestSuiteLibraryDTO {

	private Long testSuiteLibraryId;
	private String shortDescription;
	private String description;
	private TestRunState state;
	private Long installed_id;
	private Long creationTestRun;
	private Long productId;
	private BigDecimal estimatedEffort;
	private List<TestCaseLibraryDTO> testCaseLibraries;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<TestCaseLibraryDTO> getTestCaseLibraries() {
		return testCaseLibraries;
	}

	public void setTestCaseLibraries(List<TestCaseLibraryDTO> testCaseLibraries) {
		this.testCaseLibraries = testCaseLibraries;
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

	public TestRunState getState() {
		return state;
	}

	public void setState(TestRunState state) {
		this.state = state;
	}

	public Long getInstalled_id() {
		return installed_id;
	}

	public void setInstalled_id(Long installed_id) {
		this.installed_id = installed_id;
	}

	public Long getTestSuiteLibraryId() {
		return testSuiteLibraryId;
	}

	public void setTestSuiteLibraryId(Long testSuiteLibraryId) {
		this.testSuiteLibraryId = testSuiteLibraryId;
	}

	public Long getCreationTestRun() {
		return creationTestRun;
	}

	public void setCreationTestRun(Long creationTestRun) {
		this.creationTestRun = creationTestRun;
	}

	public BigDecimal getEstimatedEffort() {
		return estimatedEffort;
	}

	public void setEstimatedEffort(BigDecimal estimatedEffort) {
		this.estimatedEffort = estimatedEffort;
	}

}
