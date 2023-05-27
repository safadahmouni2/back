package com.thinktank.pts.qaservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thinktank.pts.qaservice.enums.ChangeRequestState;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TEST_CASE_CHANGE_REQUEST")
public class TestCaseChangeRequest extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATE")
	private ChangeRequestState state;

	@Column(name = "TEST_CASE_LIBRARY_ID")
	private Long testCaseId;

	@Column(name = "TEST_ID")
	private Long testId;

	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "SPRINT_ID")
	private Long sprintId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ChangeRequestState getState() {
		return state;
	}

	public void setState(ChangeRequestState state) {
		this.state = state;
	}

	public Long getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(Long testCaseId) {
		this.testCaseId = testCaseId;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
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

}
