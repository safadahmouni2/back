package com.thinktank.pts.qaservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.thinktank.pts.qaservice.enums.TestRunState;

public class TestRunDTO {

	private Long testRunId;
	private String shortDescription;
	private String description;
	private TestRunState state;
	private Long installed_id;
	private BigDecimal estimatedEffort;
	private List<TestDTO> tests;
	private String flag;
	private Long environmentId;
	private Long originSprintId;
	private Integer totalTests;
	private Integer totalTestOk;
	private Integer totalTestNotOk;
	private String responsible;

	private LocalDateTime modified;

	private String modifier;

	private String creator;

	private LocalDateTime created;

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public Integer getTotalTests() {
		return totalTests;
	}

	public void setTotalTests(Integer totalTests) {
		this.totalTests = totalTests;
	}

	public Integer getTotalTestOk() {
		return totalTestOk;
	}

	public void setTotalTestOk(Integer totalTestOk) {
		this.totalTestOk = totalTestOk;
	}

	public Integer getTotalTestNotOk() {
		return totalTestNotOk;
	}

	public void setTotalTestNotOk(Integer totalTestNotOk) {
		this.totalTestNotOk = totalTestNotOk;
	}

	public Long getOriginSprintId() {
		return originSprintId;
	}

	public void setOriginSprintId(Long originSprintId) {
		this.originSprintId = originSprintId;
	}

	public Long getEnvironmentId() {
		return environmentId;
	}

	public void setEnvironmentId(Long environmentId) {
		this.environmentId = environmentId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Long getTestRunId() {
		return testRunId;
	}

	public void setTestRunId(Long testRunId) {
		this.testRunId = testRunId;
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

	public List<TestDTO> getTests() {
		return tests;
	}

	public void setTests(List<TestDTO> tests) {
		this.tests = tests;
	}

	public Long getInstalled_id() {
		return installed_id;
	}

	public void setInstalled_id(Long installed_id) {
		this.installed_id = installed_id;
	}

	public BigDecimal getEstimatedEffort() {
		return estimatedEffort;
	}

	public void setEstimatedEffort(BigDecimal estimatedEffort) {
		this.estimatedEffort = estimatedEffort;
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
