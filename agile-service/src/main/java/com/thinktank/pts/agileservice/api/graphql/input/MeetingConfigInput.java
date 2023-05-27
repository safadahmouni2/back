package com.thinktank.pts.agileservice.api.graphql.input;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.thinktank.pts.agileservice.enums.MeetingParentTypeEnum;
import com.thinktank.pts.agileservice.enums.MeetingRecurrenceTypeEnum;
import com.thinktank.pts.agileservice.enums.MeetingTypeEnum;
import com.thinktank.pts.apibase.graphql.AbstractGraphQlArgumentAwareBaseInput;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

/**
 * 
 * @author karabakaa
 * @since May 23, 2023
 *
 */

@Data
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants(level = AccessLevel.PUBLIC)
public class MeetingConfigInput extends AbstractGraphQlArgumentAwareBaseInput {

	private Long productId;

	private String meetName;

	private LocalDate startDate;

	private LocalDate endDate;

	private LocalTime meetStartTime;

	private Long meetDuration;

	private MeetingRecurrenceTypeEnum recurrenceType;

	private Long recurrenceNumber;

	private String organizerCode;

	private Long organizerId;

	private String location;

	private Long parentTicketId;

	private MeetingParentTypeEnum parentType;

	private MeetingTypeEnum meetType;

	private List<MeetingAttendeeInput> attendees;
}
