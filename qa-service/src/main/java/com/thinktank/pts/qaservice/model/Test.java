package com.thinktank.pts.qaservice.model;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinktank.pts.qaservice.enums.TestState;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TEST")
public class Test extends AbstractTest implements Comparable<Test> {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "TEST_ID", nullable = false)
	private Long testId;

	@Column(name = "USER_STORY_ID")
	private Long userStoryId;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATE")
	private TestState testState;

	@ManyToOne
	@JoinColumn(name = "TEST_RUN_ID", referencedColumnName = "TEST_RUN_ID", nullable = false)
	private TestRun testRun;

	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JsonIgnore
	private List<TestStep> testSteps;

	@ManyToOne
	@JoinColumn(name = "TEST_CASE_LIBRARY_ID", referencedColumnName = "TEST_CASE_LIBRARY_ID")
	private TestCaseLibrary testCaseLibrary;

	@Column(name = "TEST_CASE_VERSION")
	private int testCaseVersion;

	@Column(name = "origin_sprint_id")
	private Long originSprintId;

	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<TestEffort> testEfforts;

	@Override
	public int compareTo(Test other) {
		return this.created.compareTo(other.created);
	}

	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Ticket> tickets;

	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Problem> problem;

	@OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<TestAttachment> documentTests;

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public TestState getTestState() {
		return testState;
	}

	public void setTestState(TestState testState) {
		this.testState = testState;
	}

	public TestRun getTestRun() {
		return testRun;
	}

	public void setTestRun(TestRun testRun) {
		this.testRun = testRun;
	}

	public List<TestStep> getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(List<TestStep> list) {
		this.testSteps = list;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TestCaseLibrary getTestCaseLibrary() {
		return testCaseLibrary;
	}

	public void setTestCaseLibrary(TestCaseLibrary testCaseLibrary) {
		this.testCaseLibrary = testCaseLibrary;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<Problem> getProblems() {
		return problem;
	}

	public void setProblems(List<Problem> problem) {
		this.problem = problem;
	}

	public Long getUserStoryId() {
		return userStoryId;
	}

	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	public List<TestEffort> getTestEfforts() {
		return testEfforts;
	}

	public void setTestEfforts(List<TestEffort> testEfforts) {
		this.testEfforts = testEfforts;
	}

	public int getTestCaseVersion() {
		return testCaseVersion;
	}

	public void setTestCaseVersion(int testCaseVersion) {
		this.testCaseVersion = testCaseVersion;
	}

	public Long getOriginSprintId() {
		return originSprintId;
	}

	public void setOriginSprintId(Long originSprintId) {
		this.originSprintId = originSprintId;
	}

	public List<TestAttachment> getDocumentTests() {
		return documentTests;
	}

	public void setDocumentTests(List<TestAttachment> documentTests) {
		this.documentTests = documentTests;
	}

}
