package com.thinktank.pts.agileservice.api.graphql.input.mapper;

import com.thinktank.pts.agileservice.api.graphql.input.MeetingAttendeeInput;
import com.thinktank.pts.agileservice.model.MeetingAttendee;
import com.thinktank.pts.apibase.graphql.input.mapper.AbstractBaseGraphQlInputMapper;

/**
 * 
 * @author karabakaa
 * @since May 24, 2023
 *
 */
public class MeetingAttendeeInputMapper extends AbstractBaseGraphQlInputMapper<MeetingAttendeeInput, MeetingAttendee> {

	@Override
	public MeetingAttendee create(MeetingAttendeeInput input) {

		MeetingAttendee result = null;
		if (input != null) {

			result = new MeetingAttendee();
			result.setStateId(input.getStateId());
			result.setUserCode(input.getUserCode());
			result.setRole(input.getRole());
			result.setRequired(input.isRequired());
			result.setProductId(input.getProductId());
		}

		return result;
	}

	@Override
	protected MeetingAttendee patchFields(MeetingAttendeeInput input, MeetingAttendee entity) {

		patchStateId(input, entity);
		patchUserCode(input, entity);
		patchRole(input, entity);
		patchIsRequired(input, entity);
		return entity;
	}

	/**
	 * @param input
	 * @param entity
	 */
	private void patchStateId(MeetingAttendeeInput input, MeetingAttendee entity) {

		if (input.canPatch(MeetingAttendeeInput.Fields.stateId)) {
			entity.setStateId(input.getStateId());
		}
	}

	/**
	 * @param input
	 * @param entity
	 */
	private void patchUserCode(MeetingAttendeeInput input, MeetingAttendee entity) {

		if (input.canPatch(MeetingAttendeeInput.Fields.userCode)) {
			entity.setUserCode(input.getUserCode());
		}
	}

	/**
	 * @param input
	 * @param entity
	 */
	private void patchRole(MeetingAttendeeInput input, MeetingAttendee entity) {

		if (input.canPatch(MeetingAttendeeInput.Fields.role)) {
			entity.setRole(input.getRole());
		}
	}

	/**
	 * @param input
	 * @param entity
	 */
	private void patchIsRequired(MeetingAttendeeInput input, MeetingAttendee entity) {

		if (input.canPatch(MeetingAttendeeInput.Fields.isRequired)) {
			entity.setRequired(input.isRequired());
		}
	}

}
