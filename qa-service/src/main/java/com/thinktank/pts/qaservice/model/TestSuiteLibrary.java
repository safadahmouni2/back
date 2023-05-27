package com.thinktank.pts.qaservice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.thinktank.pts.qaservice.enums.TestRunState;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "LIBRARY_TEST_SUITE")
public class TestSuiteLibrary extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "TEST_SUITE_LIBRARY_ID", nullable = false)
	private Long testSuiteLibraryId;

	@Column(name = "ESTIMATED_EFFORT")
	private BigDecimal estimatedEffort;

	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;

	@Column(name = "DESCRIPTION")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATE")
	private TestRunState state;

	@Column(name = "INSTALLED_ID")
	private Long installedId;

	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "CREATION_TEST_RUN")
	private Long creationTestRun;

	@ManyToMany(mappedBy = "testSuites", cascade = CascadeType.ALL)
	private List<TestCaseLibrary> testCaseLibraries = new ArrayList<TestCaseLibrary>();

	public void addTestCaseLibrary(List<TestCaseLibrary> t) {
		for (TestCaseLibrary testCaseLibrary : t) {
			this.testCaseLibraries.add(testCaseLibrary);
			testCaseLibrary.getTestSuites().add(this);
		}

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

	public TestRunState getState() {
		return state;
	}

	public void setState(TestRunState state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getInstalledId() {
		return installedId;
	}

	public void setInstalledId(Long installedId) {
		this.installedId = installedId;
	}

	public Long getTestSuiteLibraryId() {
		return testSuiteLibraryId;
	}

	public void setTestSuiteLibraryId(Long testSuiteLibraryId) {
		this.testSuiteLibraryId = testSuiteLibraryId;
	}

	public Long getCreationTestRun() {
		return creationTestRun;
	}

	public void setCreationTestRun(Long creationTestRun) {
		this.creationTestRun = creationTestRun;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public List<TestCaseLibrary> getTestCaseLibraries() {
		return testCaseLibraries;
	}

	public void setTestCaseLibraries(List<TestCaseLibrary> testCaseLibraries) {
		this.testCaseLibraries = testCaseLibraries;
	}

	public BigDecimal getEstimatedEffort() {
		return estimatedEffort;
	}

	public void setEstimatedEffort(BigDecimal estimatedEffort) {
		this.estimatedEffort = estimatedEffort;
	}

}
