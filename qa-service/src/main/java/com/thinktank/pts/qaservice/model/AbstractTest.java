package com.thinktank.pts.qaservice.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractTest extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "PRE_CONDITION")
	private String preCondition;

	@Column(name = "EXECUTION_ESTIMATION_TIME")
	private BigDecimal executionEstimationTime;

	// @Column(name = "SPRINT_ID")
	// private Long sprintId;

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

	public BigDecimal getExecutionEstimationTime() {
		return executionEstimationTime;
	}

	public void setExecutionEstimationTime(BigDecimal executionEstimationTime) {
		this.executionEstimationTime = executionEstimationTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// public Long getSprintId() {
	// return sprintId;
	// }

	// public void setSprintId(Long sprintId) {
	// this.sprintId = sprintId;
	// }

}
