package com.thinktank.pts.qaservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.thinktank.pts.qaservice.enums.Urgency;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TICKET")
public class Ticket extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "TICKET_ID", nullable = false)
	private Long id;

	@Column(name = "RESPONSIBLE")
	private String responsible;

	@Column(name = "URGENCY")
	private Urgency urgency;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;

	@Column(name = "LONG_DESCRIPTION")
	private String longDescription;

	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "USER_STORY_ID")
	private Long userStoryId;

	@Column(name = "TARGET_SPRINT_ID")
	private Long targetSprintId;

	@Column(name = "origin_sprint_id")
	private Long originSprintId;

	@Column(name = "PROJECT")
	private String project;

	@ManyToOne
	@JoinColumn(name = "TEST_ID", referencedColumnName = "TEST_ID", nullable = false)
	private Test test;

	// change nullable to true to accept the value null when add ticket from test
	// case
	@ManyToOne
	@JoinColumn(name = "TEST_STEP_ID", referencedColumnName = "TEST_STEP_ID", nullable = true)
	private TestStep testStep;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public Urgency getUrgency() {
		return urgency;
	}

	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public TestStep getTestStep() {
		return testStep;
	}

	public void setTestStep(TestStep testStep) {
		this.testStep = testStep;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getUserStoryId() {
		return userStoryId;
	}

	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	public Long getTargetSprintId() {
		return targetSprintId;
	}

	public void setTargetSprintId(Long targetSprintId) {
		this.targetSprintId = targetSprintId;
	}

	public Long getOriginSprintId() {
		return originSprintId;
	}

	public void setOriginSprintId(Long originSprintId) {
		this.originSprintId = originSprintId;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

}
