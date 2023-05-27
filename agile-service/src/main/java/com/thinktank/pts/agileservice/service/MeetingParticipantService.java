package com.thinktank.pts.agileservice.service;

import com.thinktank.pts.agileservice.api.graphql.input.MeetingParticipantInput;
import com.thinktank.pts.agileservice.model.MeetingParticipant;

/**
 * 
 * @author karabakaa
 * @since May 25, 2023
 *
 */
public interface MeetingParticipantService {

	/**
	 * 
	 * @param input
	 * @return MeetingAttendee created {@link com.thinktank.pts.agileservice.model.MeetingParticipant}
	 */
	MeetingParticipant create(MeetingParticipantInput input);

}
