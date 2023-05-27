package com.thinktank.pts.qaservice.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.thinktank.pts.qaservice.actor.ActorContextHolder;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// @NotNull
	@Column(name = "CREATOR", updatable = false)
	private String creator;

	// @NotNull
	@CreatedDate
	@Column(name = "CREATED", updatable = false)
	protected LocalDateTime created;

	// @NotNull
	// @LastModifiedBy
	@Column(name = "MODIFIER")
	private String modifier;

	// @NotNull
	@LastModifiedDate
	@Column(name = "MODIFIED")
	private LocalDateTime modified;

	@Version
	@Column(name = "VERSION")
	private Integer version;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@PrePersist
	public void persistCreator() {
		setCreator(ActorContextHolder.getActor().getCode());
		setModifier(ActorContextHolder.getActor().getCode());
	}

	@PreUpdate
	public void updateModifier() {
		setModifier(ActorContextHolder.getActor().getCode());
	}

}
