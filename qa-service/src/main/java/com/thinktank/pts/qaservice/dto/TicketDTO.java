package com.thinktank.pts.qaservice.dto;

import com.thinktank.pts.qaservice.enums.Urgency;

public class TicketDTO {

	private Long id;
	private String responsible;
	private Urgency urgency;
	private String productName;
	private String shortDescription;
	private String longDescription;
	private Long testId;
	private Long testStepId;
	private Long productId;
	private Long userStoryId;
	private Long targetSprintId;
	private Long originSprintId;
	private String project;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public Urgency getUrgency() {
		return urgency;
	}

	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public Long getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(Long testStepId) {
		this.testStepId = testStepId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public long getUserStoryId() {
		return userStoryId;
	}

	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	public Long getTargetSprintId() {
		return targetSprintId;
	}

	public void setTargetSprintId(Long targetSprintId) {
		this.targetSprintId = targetSprintId;
	}

	public Long getOriginSprintId() {
		return originSprintId;
	}

	public void setOriginSprintId(Long originSprintId) {
		this.originSprintId = originSprintId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}
	
	

}
