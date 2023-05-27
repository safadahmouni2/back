package com.thinktank.pts.qaservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "LIBRARY_TEST_STEP_HISTORY")
public class TestStepLibraryHistory extends AbstractBaseEntity implements Comparable<TestStepLibraryHistory> {
	private static final long serialVersionUID = 1L;

	// id of new UsTestStep will be Generated Dynamically
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "library_test_step_id")
	private Long testStepLibraryId;

	@Column(name = "STEP_DESCRIPTION")
	private String stepDescription;

	@Column(name = "EXPECTED_RESULT")
	private String expectedResult;

	@Column(name = "STEP_ORDER")
	private int stepOrder;

	@ManyToOne
	@JoinColumn(name = "LIBRARY_TEST_CASE_HISTORY_ID", referencedColumnName = "ID", nullable = true)
	private TestCaseLibraryHistory testCaseLibraryHistory;

	@Override
	public int compareTo(TestStepLibraryHistory other) {
		return this.created.compareTo(other.created);
	}

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getTestStepLibraryId() {
		return testStepLibraryId;
	}

	public void setTestStepLibraryId(Long testStepLibraryId) {
		this.testStepLibraryId = testStepLibraryId;
	}

	public TestCaseLibraryHistory getTestCaseLibraryHistory() {
		return testCaseLibraryHistory;
	}

	public void setTestCaseLibraryHistory(TestCaseLibraryHistory testCaseLibraryHistory) {
		this.testCaseLibraryHistory = testCaseLibraryHistory;
	}

}
