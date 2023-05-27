package com.thinktank.pts.qaservice.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "LIBRARY_TEST_CASE_EFFORT")
public class LibraryTestCaseEffort extends AbstractBaseEntity  {
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "LIBRARY_TEST_CASE_EFFORT_ID", nullable = false)
	private Long id;

	@Column(name = "START_TIME")
	private Time startTime;

	@Column(name = "END_TIME")
	private Time endTime;

	@Column(name = "DATE")
	private Date date;

	@Column(name = "EFFORT_BY_LINE")
	private Time effortByLine;
	
	@ManyToOne
	@JoinColumn(name = "LIBRARY_TEST_CASE_ID", referencedColumnName = "TEST_CASE_LIBRARY_ID", nullable = false)
	private TestCaseLibrary testCaseLibrary;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getEffortByLine() {
		return effortByLine;
	}

	public void setEffortByLine(Time effortByLine) {
		this.effortByLine = effortByLine;
	}

	public TestCaseLibrary getTestCaseLibrary() {
		return testCaseLibrary;
	}

	public void setTestCaseLibrary(TestCaseLibrary testCaseLibrary) {
		this.testCaseLibrary = testCaseLibrary;
	}
	

}
