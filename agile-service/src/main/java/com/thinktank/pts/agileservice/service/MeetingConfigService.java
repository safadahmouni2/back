package com.thinktank.pts.agileservice.service;

import java.util.List;
import java.util.Optional;

import com.thinktank.pts.agileservice.api.graphql.input.SearchMeetingConfigInput;
import com.thinktank.pts.agileservice.model.MeetingConfig;

/**
 * 
 * @author karabakaa
 * @since May 24, 2023
 *
 */
public interface MeetingConfigService {

	/**
	 * 
	 * @param meetingConfig
	 * @return MeetingConfig created {@link com.thinktank.pts.agileservice.model.MeetingConfig}
	 */
	MeetingConfig create(MeetingConfig meetingConfig);

	/**
	 * this method is used to retrieve meetingConfig by id
	 * 
	 * @param id
	 * @return Optional of meetingConfig {@link com.thinktank.pts.agileservice.model.MeetingConfig}
	 */
	Optional<MeetingConfig> findById(Long id);

	/**
	 * Method used to retrieve the list of MeetingConfigs matched search criteria
	 * 
	 * @param input
	 * @return List of meetingConfig {@link com.thinktank.pts.agileservice.model.MeetingConfig}
	 */
	List<MeetingConfig> searchMeetingConfigs(SearchMeetingConfigInput input);
}
