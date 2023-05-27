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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.thinktank.pts.qaservice.enums.TestCaseState;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
// UniqueConstraint : this unique contraint to allow the attach of TC to multiple US
@Table(name = "US_TEST_CASE", uniqueConstraints = {
		@UniqueConstraint(name = "UniqueTCAndUS", columnNames = { "TEST_CASE_LIBRARY_ID", "USER_STORY_ID" }) })
public class UsTestCase extends AbstractTest implements Comparable<UsTestCase> {

	private static final long serialVersionUID = 1L;

	// id of new UsTestStep will be Generated Dynamically
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "US_TEST_CASE_ID", nullable = false)
	private Long usTestCaseId;

	@Column(name = "TEST_CASE_LIBRARY_ID")
	private Long testCaseLibraryId;

	@Column(name = "TEST_CASE_VERSION")
	private Integer testCaseVersion;

	@Column(name = "USER_STORY_ID")
	private Long userStoryId;

	@Column(name = "SPRINT_ID")
	private Long sprintId;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATE")
	private TestCaseState state;

	@ManyToOne()
	@JoinColumn(name = "FOLDER_ID", referencedColumnName = "FOLDER_ID", nullable = true)
	private Folder folder;

	@OneToMany(mappedBy = "usTestCase", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<UsTestStep> usTestSteps;

	@Override
	public int compareTo(UsTestCase other) {
		return this.created.compareTo(other.created);
	}

	public Long getUsTestCaseId() {
		return usTestCaseId;
	}

	public void setUsTestCaseId(Long usTestCaseId) {
		this.usTestCaseId = usTestCaseId;
	}

	public TestCaseState getState() {
		return state;
	}

	public void setState(TestCaseState state) {
		this.state = state;
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public List<UsTestStep> getTestSteps() {
		return usTestSteps;
	}

	public void setTestSteps(List<UsTestStep> usTestSteps) {
		this.usTestSteps = usTestSteps;
	}

	public Long getUserStoryId() {
		return userStoryId;
	}

	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	public void setTestCaseLibraryId(Long testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public Integer getTestCaseVersion() {
		return testCaseVersion;
	}

	public void setTestCaseVersion(Integer testCaseVersion) {
		this.testCaseVersion = testCaseVersion;
	}

	public List<UsTestStep> getUsTestSteps() {
		return usTestSteps;
	}

	public void setUsTestSteps(List<UsTestStep> usTestSteps) {
		this.usTestSteps = usTestSteps;
	}

}
