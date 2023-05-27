package com.thinktank.pts.agileservice.api.graphql.input.mapper;

import com.thinktank.pts.agileservice.api.graphql.input.MeetingConfigInput;
import com.thinktank.pts.agileservice.model.MeetingConfig;
import com.thinktank.pts.apibase.graphql.input.mapper.AbstractBaseGraphQlInputMapper;

/**
 * 
 * @author karabakaa
 * @since Apr 5, 2023
 *
 */
public class MeetingConfigInputMapper extends AbstractBaseGraphQlInputMapper<MeetingConfigInput, MeetingConfig> {

	@Override
	public MeetingConfig create(MeetingConfigInput input) {

		MeetingConfig result = null;
		if (input != null) {

			result = new MeetingConfig();

			result.setProductId(input.getProductId());
			result.setEndDate(input.getEndDate());
			result.setLocation(input.getLocation());
			result.setMeetDuration(input.getMeetDuration());
			result.setMeetName(input.getMeetName());
			result.setMeetStartTime(input.getMeetStartTime());
			result.setMeetType(input.getMeetType());
			result.setOrganizerCode(input.getOrganizerCode());
			result.setOrganizerId(input.getOrganizerId());
			result.setParentTicketId(input.getParentTicketId());
			result.setParentType(input.getParentType());
			result.setRecurrenceNumber(input.getRecurrenceNumber());
			result.setRecurrenceType(input.getRecurrenceType());
			result.setStartDate(input.getStartDate());
		}
		return result;

	}

	@Override
	protected MeetingConfig patchFields(MeetingConfigInput input, MeetingConfig entity) {

		patchEndDate(input, entity);
		patchProductId(input, entity);
		patchLocation(input, entity);

		patchMeetDuration(input, entity);
		patchMeetName(input, entity);
		patchMeetStartTime(input, entity);

		patchMeetType(input, entity);
		patchOrganizerCode(input, entity);
		patchOrganizerId(input, entity);
		patchStartDate(input, entity);

		patchParentTicketId(input, entity);
		patchParentType(input, entity);
		patchRecurrenceNumber(input, entity);
		patchRecurrenceType(input, entity);
		return entity;
	}

	private void patchEndDate(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.endDate)) {
			entity.setEndDate(input.getEndDate());
		}
	}

	private void patchProductId(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.productId)) {
			entity.setProductId(input.getProductId());
		}
	}

	private void patchLocation(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.location)) {
			entity.setLocation(input.getLocation());
		}
	}

	private void patchMeetDuration(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.meetDuration)) {
			entity.setMeetDuration(input.getMeetDuration());
		}
	}

	private void patchMeetName(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.meetName)) {
			entity.setMeetName(input.getMeetName());
		}
	}

	private void patchMeetStartTime(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.meetStartTime)) {
			entity.setMeetStartTime(input.getMeetStartTime());
		}
	}

	private void patchMeetType(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.meetType)) {
			entity.setMeetType(input.getMeetType());
		}
	}

	private void patchOrganizerCode(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.organizerCode)) {
			entity.setOrganizerCode(input.getOrganizerCode());
		}
	}

	private void patchOrganizerId(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.organizerId)) {
			entity.setOrganizerId(input.getOrganizerId());
		}
	}

	private void patchParentTicketId(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.parentTicketId)) {
			entity.setParentTicketId(input.getParentTicketId());
		}
	}

	private void patchParentType(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.parentType)) {
			entity.setParentType(input.getParentType());
		}
	}

	private void patchRecurrenceNumber(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.recurrenceNumber)) {
			entity.setRecurrenceNumber(input.getRecurrenceNumber());
		}
	}

	private void patchRecurrenceType(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.organizerId)) {
			entity.setRecurrenceType(input.getRecurrenceType());
		}
	}

	private void patchStartDate(MeetingConfigInput input, MeetingConfig entity) {
		if (input.canPatch(MeetingConfigInput.Fields.startDate)) {
			entity.setStartDate(input.getStartDate());
		}
	}

}
