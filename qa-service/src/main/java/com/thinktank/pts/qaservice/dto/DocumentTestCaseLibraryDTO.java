package com.thinktank.pts.qaservice.dto;

import java.time.LocalDateTime;

import javax.persistence.Lob;

/**
 * 
 * @author trabelsimn
 *
 */
public class DocumentTestCaseLibraryDTO {

	private Long id;

	private String fileName;

	private String type;

	@Lob
	private byte[] data;

	private LocalDateTime uploadDate;

	private String shortDescription;

	private TestCaseLibraryDTO testCaseLibrary;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public TestCaseLibraryDTO getTestCaseLibrary() {
		return testCaseLibrary;
	}

	public void setTestCaseLibrary(TestCaseLibraryDTO testCaseLibrary) {
		this.testCaseLibrary = testCaseLibrary;
	}

}
