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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinktank.pts.qaservice.enums.TestRunState;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TEST_RUN")
public class TestRun extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "TEST_RUN_ID", nullable = false)
	private Long testRunId;

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

	@Column(name = "ENVIRONMENT_ID")
	private Long environmentId;

	@OneToMany(mappedBy = "testRun", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Test> tests;

	@OneToMany(mappedBy = "testRun", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<TestEffort> testEfforts;

	@Column(name = "RESPONSIBLE")
	private String responsible;

	public Long getTestRunId() {
		return testRunId;
	}

	public void setTestRunId(Long testRunId) {
		this.testRunId = testRunId;
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

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
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

	public BigDecimal getEstimatedEffort() {
		return estimatedEffort;
	}

	public void setEstimatedEffort(BigDecimal estimatedEffort) {
		this.estimatedEffort = estimatedEffort;
	}

	public List<TestEffort> getTestEfforts() {
		return testEfforts;
	}

	public void setTestEfforts(List<TestEffort> testEfforts) {
		this.testEfforts = testEfforts;
	}

	public Long getEnvironmentId() {
		return environmentId;
	}

	public void setEnvironmentId(Long environmentId) {
		this.environmentId = environmentId;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

}
