package com.thinktank.pts.agileservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.agileservice.api.graphql.input.MeetingInput;
import com.thinktank.pts.agileservice.api.graphql.input.mapper.MeetingInputMapper;
import com.thinktank.pts.agileservice.model.Meeting;
import com.thinktank.pts.agileservice.model.MeetingConfig;
import com.thinktank.pts.agileservice.repository.MeetingRepository;
import com.thinktank.pts.agileservice.service.MeetingConfigService;
import com.thinktank.pts.agileservice.service.MeetingService;

/**
 * 
 * @author karabakaa
 * @since May 25, 2023
 *
 */
@Service
public class MeetingServiceImpl implements MeetingService {

	@Autowired
	private MeetingRepository meetingRepository;

	@Autowired
	private MeetingConfigService meetingConfigService;

	private MeetingInputMapper planningInputMapper = new MeetingInputMapper();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Meeting create(MeetingInput input) {
		Optional<MeetingConfig> opt = meetingConfigService.findById(input.getMeetingConfigId());
		Meeting planning = planningInputMapper.create(input);
		if (opt.isPresent()) {
			planning.setMeetingConfigId(opt.get().getId());
		}

		return meetingRepository.save(planning);
	}

	@Override
	public Optional<Meeting> findById(Long id) {
		return meetingRepository.findById(id);
	}

}