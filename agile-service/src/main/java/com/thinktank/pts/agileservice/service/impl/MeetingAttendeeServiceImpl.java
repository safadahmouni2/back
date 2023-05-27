package com.thinktank.pts.agileservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.agileservice.api.graphql.input.MeetingAttendeeInput;
import com.thinktank.pts.agileservice.api.graphql.input.mapper.MeetingAttendeeInputMapper;
import com.thinktank.pts.agileservice.model.MeetingAttendee;
import com.thinktank.pts.agileservice.model.MeetingConfig;
import com.thinktank.pts.agileservice.repository.MeetingAttendeeRepository;
import com.thinktank.pts.agileservice.service.MeetingAttendeeService;
import com.thinktank.pts.agileservice.service.MeetingConfigService;
import com.thinktank.pts.apibase.graphql.exception.UnknownIDException;

/**
 * 
 * @author karabakaa
 * @since May 24, 2023
 *
 */
@Service
public class MeetingAttendeeServiceImpl implements MeetingAttendeeService {

	@Autowired
	private MeetingAttendeeRepository meetingAttendeeRepository;

	@Autowired
	private MeetingConfigService meetingConfigService;

	private MeetingAttendeeInputMapper meetingAttendeeInputMapper = new MeetingAttendeeInputMapper();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MeetingAttendee create(MeetingAttendeeInput input) {
		Optional<MeetingConfig> opt = meetingConfigService.findById(input.getMeetConfigId());

		if (!opt.isPresent()) {
			throw new UnknownIDException(input.getMeetConfigId());
		}

		MeetingAttendee attendee = meetingAttendeeInputMapper.create(input);
		attendee.setMeetingConfig(opt.get());

		return meetingAttendeeRepository.save(attendee);
	}

}