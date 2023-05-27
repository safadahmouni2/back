package com.thinktank.pts.agileservice.service;

import java.util.Optional;

import com.thinktank.pts.agileservice.api.graphql.input.MeetingInput;
import com.thinktank.pts.agileservice.model.Meeting;

/**
 * 
 * @author karabakaa
 * @since May 24, 2023
 *
 */
public interface MeetingService {

	/**
	 * 
	 * @param input
	 * @return meeting created {@link com.thinktank.pts.agileservice.model.Meeting}
	 */
	Meeting create(MeetingInput input);

	/**
	 * this method is used to retrieve meeting by id
	 * 
	 * @param id
	 * @return Optional of meeting {@link com.thinktank.pts.agileservice.model.Meeting}
	 */
	Optional<Meeting> findById(Long id);
}
