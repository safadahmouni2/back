package com.thinktank.pts.qaservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.thinktank.pts.qaservice.enums.TestStepState;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TEST_STEP")
public class TestStep extends AbstractBaseEntity implements Comparable<TestStep> {

	private static final long serialVersionUID = 1L;
	// Requirement Nr: 10465414 : this sequence was commented in order to accept copy of exist id and not generate a new
	// id when trying to copy test steps library into test step

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "TEST_STEP_ID", nullable = false)
	private Long id;

	@Column(name = "STEP_DESCRIPTION")
	private String stepDescription;

	@Column(name = "EXPECTED_RESULT")
	private String expectedResult;

	@Column(name = "STEP_ORDER")
	private int stepOrder;

	@Column(name = "TEST_STEP_STATE")
	private TestStepState testStepState;

	@Column(name = "test_step_library_id")
	private Long testStepLibraryId;

	@ManyToOne
	@JoinColumn(name = "TEST_CASE_LIBRARY_ID", referencedColumnName = "TEST_CASE_LIBRARY_ID")
	private TestCaseLibrary testCaseLibrary;

	@ManyToOne
	@JoinColumn(name = "TEST_ID", referencedColumnName = "TEST_ID", nullable = false)
	private Test test;

	@OneToMany(mappedBy = "testStep", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Ticket> tickets;

	@OneToMany(mappedBy = "testStep", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Problem> problems;

	@Override
	public int compareTo(TestStep other) {
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

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TestCaseLibrary getTestCaseLibrary() {
		return this.testCaseLibrary;
	}

	public void setTestCaseLibrary(TestCaseLibrary testCaseLibrary) {
		this.testCaseLibrary = testCaseLibrary;
	}

	public Long getTestStepLibraryId() {
		return testStepLibraryId;
	}

	public void setTestStepLibraryId(Long testStepLibraryId) {
		this.testStepLibraryId = testStepLibraryId;
	}

}
