package com.thinktank.pts.qaservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOURCE_SYSTEM")
public class SourceSystem extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "source_system_id")
	private int sourceSystemId;

	@Column(name = "source_system_name", nullable = false)
	private String sourceSystemName;

	public int getSourceSystemId() {
		return sourceSystemId;
	}

	public void setSourceSystemId(int sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	public String getSourceSystemName() {
		return sourceSystemName;
	}

	public void setSourceSystemName(String sourceSystemName) {
		this.sourceSystemName = sourceSystemName;
	}
}
