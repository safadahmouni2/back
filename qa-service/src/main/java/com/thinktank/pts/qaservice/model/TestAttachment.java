package com.thinktank.pts.qaservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TEST_ATTACHMENT")
public class TestAttachment extends AbstractBaseEntity {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "TYPE")
	private String type;

	@Lob
	@Column(name = "DATA")
	private byte[] data;

	@Column(name = "UPLOAD_DATE")
	private LocalDateTime uploadDate;

	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;

	@ManyToOne
	@JoinColumn(name = "TEST_ID", referencedColumnName = "TEST_ID", nullable = false)
	private Test test;

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

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

}
