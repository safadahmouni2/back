package com.thinktank.pts.agileservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.thinktank.pts.apibase.model.base.AbstractBaseMainTraceableEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 * @author karabakaa
 * @since May 23, 2023
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEETING_ATTENDEE")
public class MeetingAttendee extends AbstractBaseMainTraceableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "TICKET_ID", unique = true)
	private Long ticketId;

	@Column(name = "STATE_ID")
	private Long stateId;

	@Column(name = "USER_ID")
	private Long userId;

	@Column(name = "USER_CODE", length = 10)
	private String userCode;

	@Column(name = "ROLE", length = 250)
	private String role;

	@Column(name = "PRODUCT_ID", nullable = false)
	private Long productId;

	@Column(name = "IS_REQUIRED")
	private boolean isRequired;

	@ManyToOne
	@JoinColumn(name = "MEETING_CONFIG_ID", referencedColumnName = "id", nullable = false)
	private MeetingConfig meetingConfig;

}
