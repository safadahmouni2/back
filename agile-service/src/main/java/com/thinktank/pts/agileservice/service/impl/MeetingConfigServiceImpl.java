package com.thinktank.pts.agileservice.service.impl;

import static com.thinktank.pts.agileservice.repository.specs.MeetingConfigSpecs.specification;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.agileservice.api.graphql.input.SearchMeetingConfigInput;
import com.thinktank.pts.agileservice.model.MeetingConfig;
import com.thinktank.pts.agileservice.repository.MeetingConfigRepository;
import com.thinktank.pts.agileservice.service.MeetingConfigService;

/**
 * 
 * @author karabakaa
 * @since May 24, 2023
 *
 */
@Service
public class MeetingConfigServiceImpl implements MeetingConfigService {

	@Autowired
	private MeetingConfigRepository meetingConfigRepository;


	/**
	 * {@inheritDoc}
	 */
	@Override
	public MeetingConfig create(MeetingConfig meetingConfig) {
		return meetingConfigRepository.save(meetingConfig);
	}

	@Override
	public Optional<MeetingConfig> findById(Long id) {
		return meetingConfigRepository.findById(id);
	}

	@Override
	public List<MeetingConfig> searchMeetingConfigs(SearchMeetingConfigInput input) {
		return meetingConfigRepository.findAll(specification(input.getParentTicketId(), input.getParentType(),
				input.getMeetType(), input.isMeetInCurrentDay()));
	}

}