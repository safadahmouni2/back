package com.thinktank.pts.agileservice.service;

import com.thinktank.pts.agileservice.api.graphql.input.MeetingAttendeeInput;
import com.thinktank.pts.agileservice.model.MeetingAttendee;

/**
 * 
 * @author karabakaa
 * @since May 24, 2023
 *
 */
public interface MeetingAttendeeService {

	/**
	 * 
	 * @param input
	 * @return MeetingAttendee created {@link com.thinktank.pts.agileservice.model.MeetingAttendee}
	 */
	MeetingAttendee create(MeetingAttendeeInput input);

}
