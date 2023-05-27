package com.thinktank.pts.agileservice.api.graphql.input.mapper;

import com.thinktank.pts.agileservice.api.graphql.input.MeetingInput;
import com.thinktank.pts.agileservice.model.Meeting;
import com.thinktank.pts.apibase.graphql.input.mapper.AbstractBaseGraphQlInputMapper;

/**
 * 
 * @author karabakaa
 * @since Apr 5, 2023
 *
 */

public class MeetingInputMapper
		extends AbstractBaseGraphQlInputMapper<MeetingInput, Meeting> {

	@Override
	public Meeting create(MeetingInput input) {

		Meeting result = null;
		if (input != null) {

			result = new Meeting();

			result.setProductId(input.getProductId());
			result.setStartedAt(input.getStartedAt());
			result.setStateId(input.getStateId());
			result.setEstimatedEndDate(input.getEstimatedEndDate());
			result.setLocation(input.getLocation());
			result.setEstimatedMeetDuration(input.getEstimatedMeetDuration());
			result.setMeetName(input.getMeetName());
			result.setEstimatedMeetStartTime(input.getEstimatedMeetStartTime());
			result.setOrganizerCode(input.getOrganizerCode());
			result.setOrganizerId(input.getOrganizerId());
			result.setParentTicketId(input.getParentTicketId());
			result.setParentType(input.getParentType());
			result.setRecurrenceNumber(input.getRecurrenceNumber());
			result.setRecurrenceType(input.getRecurrenceType());
			result.setEstimatedStartDate(input.getEstimatedStartDate());
			result.setMeetType(input.getMeetType());
		}
		return result;

	}


	@Override
	protected Meeting patchFields(MeetingInput input, Meeting entity) {

		patchStateId(input, entity);
		patchFinishedAt(input, entity);
		return entity;
	}

	private void patchFinishedAt(MeetingInput input, Meeting entity) {
		if (input.canPatch(MeetingInput.Fields.finishedAt)) {
			entity.setFinishedAt(input.getFinishedAt());
		}
	}

	private void patchStateId(MeetingInput input, Meeting entity) {
		if (input.canPatch(MeetingInput.Fields.stateId)) {
			entity.setStateId(input.getStateId());
		}
	}


}
