package com.thinktank.pts.qaservice.dto;

import java.sql.Date;
import java.sql.Time;

public class LibraryTestCaseEffortDTO {
	private Long id;
	private Time startTime;
	private Time endTime;
	private Date date;
	private Time effortByLine;
	private Long testCaseLibraryId;

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

	public Long getTestCaseLibraryId() {
		return testCaseLibraryId;
	}

	public void setTestCaseLibraryId(Long testCaseLibraryId) {
		this.testCaseLibraryId = testCaseLibraryId;
	}

	
}
