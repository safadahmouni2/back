package com.thinktank.pts.qaservice.dto;

import java.time.LocalDateTime;

import com.thinktank.pts.qaservice.enums.ChangeRequestState;

public class TestCaseChangeRequestDTO {

	private Long Id;
	private ChangeRequestState state;
	private Long testId;
	private Long testCaseId;
	private Long productId;
	private Long sprintId;
	private LocalDateTime modified;

	private String creator;

	private LocalDateTime created;

	private String modifier;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public ChangeRequestState getState() {
		return state;
	}

	public void setState(ChangeRequestState state) {
		this.state = state;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public Long getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(Long testCaseId) {
		this.testCaseId = testCaseId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
}
