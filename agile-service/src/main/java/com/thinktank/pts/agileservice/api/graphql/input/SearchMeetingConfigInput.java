package com.thinktank.pts.agileservice.api.graphql.input;

import com.thinktank.pts.agileservice.enums.MeetingParentTypeEnum;
import com.thinktank.pts.agileservice.enums.MeetingTypeEnum;
import com.thinktank.pts.apibase.graphql.AbstractGraphQlArgumentAwareBaseInput;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldNameConstants;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants(level = AccessLevel.PUBLIC)
public class SearchMeetingConfigInput extends AbstractGraphQlArgumentAwareBaseInput {

	private Long parentTicketId;

	private MeetingParentTypeEnum parentType;

	private MeetingTypeEnum meetType;

	private boolean meetInCurrentDay;

}
