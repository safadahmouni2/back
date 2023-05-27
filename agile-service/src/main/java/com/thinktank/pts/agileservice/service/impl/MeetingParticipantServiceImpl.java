package com.thinktank.pts.agileservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.agileservice.api.graphql.input.MeetingParticipantInput;
import com.thinktank.pts.agileservice.api.graphql.input.mapper.MeetingParticipantInputMapper;
import com.thinktank.pts.agileservice.model.Meeting;
import com.thinktank.pts.agileservice.model.MeetingParticipant;
import com.thinktank.pts.agileservice.repository.MeetingParticipantRepository;
import com.thinktank.pts.agileservice.service.MeetingParticipantService;
import com.thinktank.pts.agileservice.service.MeetingService;
import com.thinktank.pts.apibase.graphql.exception.UnknownIDException;

/**
 * 
 * @author karabakaa
 * @since May 24, 2023
 *
 */
@Service
public class MeetingParticipantServiceImpl implements MeetingParticipantService {

	@Autowired
	private MeetingParticipantRepository meetingParticipantRepository;

	@Autowired
	private MeetingService meetingService;

	private MeetingParticipantInputMapper meetingParticipantInputMapper = new MeetingParticipantInputMapper();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MeetingParticipant create(MeetingParticipantInput input) {
		Optional<Meeting> opt = meetingService.findById(input.getMeetingId());

		if (!opt.isPresent()) {
			throw new UnknownIDException(input.getMeetingId());
		}

		MeetingParticipant attendee = meetingParticipantInputMapper.create(input);
		attendee.setMeeting(opt.get());

		return meetingParticipantRepository.save(attendee);
	}

}