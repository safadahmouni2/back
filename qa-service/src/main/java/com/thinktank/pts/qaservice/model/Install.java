package com.thinktank.pts.qaservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.thinktank.pts.qaservice.enums.InstallState;
import com.thinktank.pts.qaservice.enums.Urgency;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "INSTALL")
public class Install extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "INSTALL_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int installId;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "DATE")
	private LocalDateTime date;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATE")
	private InstallState state;

	@Column(name = "INSTALL_VERSION")
	private String installVersion;

	@Column(name = "REQUESTED_RELEASE")
	private String requestedRelease;

	@Column(name = "URGENCY")
	private Urgency urgency;

	@Column(name = "ENV_ID")
	private Long envId;

	@Column(name = "PRODUCT_ID")
	private Long productId;

	@Column(name = "TARGET_SPRINT_ID")
	private Long targetSprintId;

	@Column(name = "USER_STORY_ID")
	private Long userStoryId;

	@Column(name = "PTS_REF")
	private Long ptsRef;

	@Column(name = "PROJECT")
	private String project;

	public int getInstallId() {
		return installId;
	}

	public void setInstallId(int installId) {
		this.installId = installId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public InstallState getState() {
		return state;
	}

	public void setState(InstallState state) {
		this.state = state;
	}

	public String getInstallVersion() {
		return installVersion;
	}

	public void setInstallVersion(String installVersion) {
		this.installVersion = installVersion;
	}

	public Urgency getUrgency() {
		return urgency;
	}

	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRequestedRelease() {
		return requestedRelease;
	}

	public void setRequestedRelease(String requestedRelease) {
		this.requestedRelease = requestedRelease;
	}

	public Long getEnvId() {
		return envId;
	}

	public void setEnvId(Long envId) {
		this.envId = envId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getTargetSprintId() {
		return targetSprintId;
	}

	public void setTargetSprintId(Long targetSprintId) {
		this.targetSprintId = targetSprintId;
	}

	public Long getUserStoryId() {
		return userStoryId;
	}

	public void setUserStoryId(Long userStoryId) {
		this.userStoryId = userStoryId;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Long getPtsRef() {
		return ptsRef;
	}

	public void setPtsRef(Long ptsRef) {
		this.ptsRef = ptsRef;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

}
