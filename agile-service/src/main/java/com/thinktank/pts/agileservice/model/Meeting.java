package com.thinktank.pts.agileservice.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.thinktank.pts.agileservice.enums.MeetingParentTypeEnum;
import com.thinktank.pts.agileservice.enums.MeetingRecurrenceTypeEnum;
import com.thinktank.pts.agileservice.enums.MeetingTypeEnum;
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
@Table(name = "MEETING")
public class Meeting extends AbstractBaseMainTraceableEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "TICKET_ID", unique = true)
	private Long ticketId;

	@Column(name = "PRODUCT_ID", nullable = false)
	private Long productId;

	@Column(name = "STATE_ID")
	private Long stateId;

	@Column(name = "STARTED_AT")
	private LocalDateTime startedAt;

	@Column(name = "FINISHED_AT")
	private LocalDateTime finishedAt;

	@Column(name = "MEET_NAME")
	private String meetName;

	@Column(name = "ESTIMATED_START_DATE")
	private LocalDate estimatedStartDate;

	@Column(name = "ESTIMATED_END_DATE")
	private LocalDate estimatedEndDate;

	@Column(name = "ESTIMATED_MEET_START_TIME")
	private LocalTime estimatedMeetStartTime;

	@Column(name = "ESTIMATED_MEET_DURATION")
	private Long estimatedMeetDuration;

	@Column(name = "RECURRENCE_type")
	@Enumerated(EnumType.STRING)
	private MeetingRecurrenceTypeEnum recurrenceType;

	@Column(name = "RECURRENCE_NUMBER")
	private Long recurrenceNumber;

	@Column(name = "ORGANIZER_CODE")
	private String organizerCode;

	@Column(name = "ORGANIZER_ID")
	private Long organizerId;

	@Column(name = "LOCATION")
	private String location;

	@Column(name = "PARENT_TICKET_ID", nullable = false)
	private Long parentTicketId;

	@Column(name = "PARENT_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private MeetingParentTypeEnum parentType;

	@Column(name = "MEET_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private MeetingTypeEnum meetType;

	@Column(name = "MEETING_CONFIG_ID")
	private Long meetingConfigId;

	@OneToMany
	@JoinColumn(name = "MEETING_ID")
	private List<MeetingParticipant> participants;
}
