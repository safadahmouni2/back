package com.thinktank.pts.qaservice.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.thinktank.pts.qaservice.enums.InstallState;
import com.thinktank.pts.qaservice.enums.Urgency;

public class InstallDTO {

	private int installId;
	private String description;
	private LocalDateTime date;
	private InstallState state;
	private String installVersion;
	private String requestedRelease;
	private Urgency urgency;
	private List<TestRunDTO> testRuns;
	private Long envId;
	private Long productId;
	private Long targetSprintId;
	private Long userStoryId;
	private Long ptsRef;
	private String project;

	public Long getPtsRef() {
		return ptsRef;
	}

	public void setPtsRef(Long ptsRef) {
		this.ptsRef = ptsRef;
	}

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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
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

	public String getRequestedRelease() {
		return requestedRelease;
	}

	public void setRequestedRelease(String requestedRelease) {
		this.requestedRelease = requestedRelease;
	}

	public Urgency getUrgency() {
		return urgency;
	}

	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}

	public List<TestRunDTO> getTestRuns() {
		return testRuns;
	}

	public void setTestRuns(List<TestRunDTO> testRuns) {
		this.testRuns = testRuns;
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

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

}
