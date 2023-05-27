package com.thinktank.pts.qaservice.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.thinktank.pts.qaservice.enums.TestCaseChangesState;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TEST_CASE_CHANGES")
public class TestCaseChanges extends AbstractBaseEntity implements Comparable<TestCaseChanges> {

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

	@Enumerated(EnumType.STRING)
	@Column(name = "STATE")
	private TestCaseChangesState state;

	@OneToMany(mappedBy = "testCaseChanges", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<TestStepChanges> testStepChanges;

	@Override
	public int compareTo(TestCaseChanges other) {
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

	public TestCaseChangesState getState() {
		return state;
	}

	public void setState(TestCaseChangesState state) {
		this.state = state;
	}

	public List<TestStepChanges> getTestStepChanges() {
		return testStepChanges;
	}

	public void setTestStepChanges(List<TestStepChanges> testStepChanges) {
		this.testStepChanges = testStepChanges;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
