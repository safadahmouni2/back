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
@Table(name = "TEST_STEP_CHANGES")
public class TestStepChanges extends AbstractBaseEntity implements Comparable<TestStepChanges> {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "TEST_CASE_CHANGE_REQUEST_ID", nullable = false)
	private Long testCaseChangeRequestId;

	@Column(name = "TEST_ID", nullable = false)
	private Long testId;

	@Column(name = "TEST_CASE_LIBRARY_ID", nullable = false)
	private Long testCaseLibraryId;

	@Column(name = "TEST_STEP_LIBRARY_ID")
	private Long testStepLibraryId;

	@Column(name = "STEP_DESCRIPTION")
	private String stepDescription;

	@Column(name = "EXPECTED_RESULT")
	private String expectedResult;

	@Column(name = "STEP_ORDER")
	private int stepOrder;

	@ManyToOne
	@JoinColumn(name = "TEST_CASE_CHANGES_ID", referencedColumnName = "ID", nullable = true)
	private TestCaseChanges testCaseChanges;

	@Override
	public int compareTo(TestStepChanges other) {
		return this.created.compareTo(other.created);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTestCaseChangeRequestId() {
		return testCaseChangeRequestId;
	}

	public void setTestCaseChangeRequestId(Long testCaseChangeRequestId) {
		this.testCaseChangeRequestId = testCaseChangeRequestId;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public Long getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	public void setTestCaseLibraryId(Long testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
	}

	public Long getTestStepLibraryId() {
		return testStepLibraryId;
	}

	public void setTestStepLibraryId(Long testStepLibraryId) {
		this.testStepLibraryId = testStepLibraryId;
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

	public TestCaseChanges getTestCaseChanges() {
		return testCaseChanges;
	}

	public void setTestCaseChanges(TestCaseChanges testCaseChanges) {
		this.testCaseChanges = testCaseChanges;
	}

}
