package com.thinktank.pts.agileservice.api.graphql.input;

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
public class MeetingAttendeeInput extends AbstractGraphQlArgumentAwareBaseInput {

	private Long stateId;

	private Long userId;

	private String userCode;

	private String role;

	private Long productId;

	private boolean isRequired;

	private Long meetConfigId;
}
