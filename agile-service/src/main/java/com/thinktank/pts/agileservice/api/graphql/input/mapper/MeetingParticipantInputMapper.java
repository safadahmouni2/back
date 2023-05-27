package com.thinktank.pts.agileservice.api.graphql.input.mapper;

import com.thinktank.pts.agileservice.api.graphql.input.MeetingParticipantInput;
import com.thinktank.pts.agileservice.model.MeetingParticipant;
import com.thinktank.pts.apibase.graphql.input.mapper.AbstractBaseGraphQlInputMapper;

/**
 * 
 * @author karabakaa
 * @since May 24, 2023
 *
 */
public class MeetingParticipantInputMapper
		extends AbstractBaseGraphQlInputMapper<MeetingParticipantInput, MeetingParticipant> {

	@Override
	public MeetingParticipant create(MeetingParticipantInput input) {

		MeetingParticipant result = null;
		if (input != null) {

			result = new MeetingParticipant();
			result.setStateId(input.getStateId());
			result.setUserCode(input.getUserCode());
			result.setUserId(input.getUserId());
			result.setJoinedAt(input.getJoinedAt());
			result.setProductId(input.getProductId());
		}

		return result;
	}

	@Override
	protected MeetingParticipant patchFields(MeetingParticipantInput input, MeetingParticipant entity) {

		patchStateId(input, entity);
		patchLeftAt(input, entity);
		return entity;
	}

	/**
	 * @param input
	 * @param entity
	 */
	private void patchLeftAt(MeetingParticipantInput input, MeetingParticipant entity) {

		if (input.canPatch(MeetingParticipantInput.Fields.leftAt)) {
			entity.setLeftAt(input.getLeftAt());
		}
	}

	/**
	 * @param input
	 * @param entity
	 */
	private void patchStateId(MeetingParticipantInput input, MeetingParticipant entity) {

		if (input.canPatch(MeetingParticipantInput.Fields.stateId)) {
			entity.setStateId(input.getStateId());
		}
	}

}
