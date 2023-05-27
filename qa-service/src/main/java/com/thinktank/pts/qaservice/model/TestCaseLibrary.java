package com.thinktank.pts.qaservice.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinktank.pts.qaservice.enums.TestCaseState;

@Entity
@Table(name = "LIBRARY_TEST_CASE")
public class TestCaseLibrary extends AbstractBaseEntity implements Comparable<TestCaseLibrary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "TEST_CASE_LIBRARY_ID", nullable = false)
	private Long testCaseLibraryId;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATE")
	private TestCaseState state;

	@Column(name = "SHORT_DESCRIPTION", nullable = false)
	private String shortDescription;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "PRE_CONDITION")
	private String preCondition;

	@Column(name = "EXECUTION_ESTIMATION_TIME")
	private BigDecimal executionEstimationTime;

	@Column(name = "PRODUCT_ID", nullable = false)
	private Long productId;

	@Column(name = "test_case_version")
	private int testCaseVersion;
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "LIBRARY_TEST_CASE_TEST_SUITE", joinColumns = {
			@JoinColumn(name = "testCaseLibraryId") }, inverseJoinColumns = {
					@JoinColumn(name = "TEST_SUITE_LIBRARY_ID") })
	private List<TestSuiteLibrary> testSuites;

	@ManyToOne
	@JoinColumn(name = "FOLDER_ID", referencedColumnName = "FOLDER_ID", nullable = false)
	@JsonIgnore
	private Folder folder;

	@OneToMany(mappedBy = "testCaseLibrary", fetch = FetchType.EAGER)
	@JsonIgnore
	@OrderBy("stepOrder ASC")
	@Fetch(value = FetchMode.SUBSELECT)
	private List<TestStepLibrary> testStepsLibrary;

	@OneToMany(mappedBy = "testCaseLibrary", fetch = FetchType.EAGER)
	private List<LibraryTestCaseEffort> libraryTestCaseEfforts;
	
	@ManyToOne
	@JoinColumn(name = "source_system_id", referencedColumnName = "source_system_id")
	@JsonIgnore
	private SourceSystem sourceSystemId;

	@Column(name = "external_id")
	private String externalId;

	@Override
	public int compareTo(TestCaseLibrary other) {
		return this.created.compareTo(other.created);
	}

	@JsonIgnore
	public List<TestSuiteLibrary> getTestSuites() {
		return testSuites;
	}

	public void setTestSuites(List<TestSuiteLibrary> testSuites) {
		this.testSuites = testSuites;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public Long getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	public void setTestCaseLibraryId(Long testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
	}

	public TestCaseState getState() {
		return state;
	}

	public void setState(TestCaseState state) {
		this.state = state;
	}

	@JsonIgnore
	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	@JsonIgnore
	public List<TestStepLibrary> getTestStepsLibrary() {
		return testStepsLibrary;
	}

	public void setTestStepsLibrary(List<TestStepLibrary> testStepsLibrary) {
		this.testStepsLibrary = testStepsLibrary;
	}

	public int getTestCaseVersion() {
		return testCaseVersion;
	}

	public void setTestCaseVersion(int testCaseVersion) {
		this.testCaseVersion = testCaseVersion;
	}

	@JsonIgnore
	public SourceSystem getSourceSystem() {
		return sourceSystemId;
	}
	public void setSourceSystem(SourceSystem sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	public SourceSystem getSourceSystemId() {
		return sourceSystemId;
	}

	public void setSourceSystemId(SourceSystem sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public List<LibraryTestCaseEffort> getLibraryTestCaseEfforts() {
		return libraryTestCaseEfforts;
	}

	public void setLibraryTestCaseEfforts(List<LibraryTestCaseEffort> libraryTestCaseEfforts) {
		this.libraryTestCaseEfforts = libraryTestCaseEfforts;
	}

}
