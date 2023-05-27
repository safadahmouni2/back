package com.thinktank.pts.qaservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.thinktank.pts.qaservice.enums.TestStepState;

@Entity
@Table(name = "Library_TEST_STEP")
public class TestStepLibrary extends AbstractBaseEntity implements Comparable<TestStepLibrary> {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "TEST_STEP_LIBRARY_ID", nullable = false)
	private Long id;

	@Column(name = "STEP_DESCRIPTION")
	private String stepDescription;

	@Column(name = "EXPECTED_RESULT")
	private String expectedResult;

	@Column(name = "STEP_ORDER")
	private int stepOrder;

	@Column(name = "TEST_STEP_STATE")
	private TestStepState testStepState;

	@ManyToOne
	@JoinColumn(name = "TEST_CASE_LIBRARY_ID", referencedColumnName = "TEST_CASE_LIBRARY_ID", nullable = true)
	private TestCaseLibrary testCaseLibrary;

	@Override
	public int compareTo(TestStepLibrary other) {
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

	public TestStepState getTestStepState() {
		return testStepState;
	}

	public void setTestStepState(TestStepState testStepState) {
		this.testStepState = testStepState;
	}

	public TestCaseLibrary getTestCaseLibrary() {
		return testCaseLibrary;
	}

	public void setTestCaseLibrary(TestCaseLibrary testCaseLibrary) {
		this.testCaseLibrary = testCaseLibrary;
	}

}
