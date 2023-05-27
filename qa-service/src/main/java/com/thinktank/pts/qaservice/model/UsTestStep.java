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

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "US_TEST_STEP")
public class UsTestStep extends AbstractBaseEntity implements Comparable<UsTestStep> {

	private static final long serialVersionUID = 1L;

	// id of new UsTestStep will be Generated Dynamically
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "US_TEST_STEP_ID", nullable = false)
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
	@JoinColumn(name = "US_TEST_CASE_ID", referencedColumnName = "US_TEST_CASE_ID", nullable = true)
	private UsTestCase usTestCase;

	@Column(name = "test_step_library_id")
	private Long testStepLibraryId;

	@Override
	public int compareTo(UsTestStep other) {
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

	public UsTestCase getUsTestCase() {
		return usTestCase;
	}

	public void setUsTestCase(UsTestCase testCase) {
		this.usTestCase = testCase;
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

}
