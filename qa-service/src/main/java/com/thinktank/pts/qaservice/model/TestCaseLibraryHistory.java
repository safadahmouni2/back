package com.thinktank.pts.qaservice.model;

import java.math.BigDecimal;
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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
// UniqueConstraint : this unique constraint to allow the attach of TC to multiple US
@Table(name = "LIBRARY_TEST_CASE_HISTORY", uniqueConstraints = {
		@UniqueConstraint(name = "UniqueTCAndVersion", columnNames = { "LIBRARY_TEST_CASE_ID", "TEST_CASE_VERSION" }) })
public class TestCaseLibraryHistory extends AbstractTest implements Comparable<TestCaseLibraryHistory> {

	private static final long serialVersionUID = 1L;

	// id of new UsTestStep will be Generated Dynamically
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "library_test_case_id")
	private Long testCaseLibraryId;

	@Column(name = "TEST_CASE_VERSION")
	private Integer testCaseVersion;

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

	@Column(name = "PRODUCT_ID")
	private Long productId;

	@ManyToOne
	@JoinColumn(name = "FOLDER_ID", referencedColumnName = "FOLDER_ID", nullable = true)
	private Folder folder;

	@OneToMany(mappedBy = "testCaseLibraryHistory", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<TestStepLibraryHistory> testStepsLibraryHistory;

	@Override
	public int compareTo(TestCaseLibraryHistory other) {
		return this.created.compareTo(other.created);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	public void setTestCaseLibraryId(Long testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
	}

	public Integer getTestCaseVersion() {
		return testCaseVersion;
	}

	public void setTestCaseVersion(Integer testCaseVersion) {
		this.testCaseVersion = testCaseVersion;
	}

	@Override
	public String getShortDescription() {
		return shortDescription;
	}

	@Override
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String getPreCondition() {
		return preCondition;
	}

	@Override
	public void setPreCondition(String preCondition) {
		this.preCondition = preCondition;
	}

	@Override
	public BigDecimal getExecutionEstimationTime() {
		return executionEstimationTime;
	}

	@Override
	public void setExecutionEstimationTime(BigDecimal executionEstimationTime) {
		this.executionEstimationTime = executionEstimationTime;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public List<TestStepLibraryHistory> getTestStepsLibraryHistory() {
		return testStepsLibraryHistory;
	}

	public void setTestStepsLibraryHistory(List<TestStepLibraryHistory> testStepsLibraryHistory) {
		this.testStepsLibraryHistory = testStepsLibraryHistory;
	}

}
