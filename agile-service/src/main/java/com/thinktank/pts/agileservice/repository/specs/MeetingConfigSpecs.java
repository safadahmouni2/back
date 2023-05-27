package com.thinktank.pts.agileservice.repository.specs;

import java.time.LocalDate;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.jpa.domain.Specification;

import com.thinktank.pts.agileservice.enums.MeetingParentTypeEnum;
import com.thinktank.pts.agileservice.enums.MeetingTypeEnum;
import com.thinktank.pts.agileservice.model.MeetingConfig;

/**
 * Specification generator. If you use meeting config comparison make sure to lower both the search-expression and the
 * attribute.
 * 
 * @author karabakaa
 * @since May 25, 2023
 *
 */

public class MeetingConfigSpecs {

	public static Specification<MeetingConfig> equalsToParentType(MeetingParentTypeEnum parentType) {

		return (meetingConfig, cq, cb) -> cb.equal(meetingConfig.get("parentType"), parentType);
	}

	public static Specification<MeetingConfig> equalsToParentTicketId(Long parentTicketId) {
		return (meetingConfig, cq, cb) -> cb.equal(meetingConfig.get("parentTicketId"), parentTicketId);
	}

	public static Specification<MeetingConfig> equalsToMeetType(MeetingTypeEnum meetType) {
		return (meetingConfig, cq, cb) -> cb.equal(meetingConfig.get("meetType"), meetType);
	}

	public static Specification<MeetingConfig> meetInCurrentDay() {
		LocalDate startOfDay = LocalDate.now();
		return (meetingConfig, cq, cb) -> cb.equal(meetingConfig.get("startDate"), startOfDay);
	}

	public static Specification<MeetingConfig> specification(Long parentTicketId, MeetingParentTypeEnum parentType,
			MeetingTypeEnum meetType, boolean meetInCurrentDay) {
		Specification<MeetingConfig> specification = Specification.where(null);

		if (parentTicketId != null) {
			specification = specification.and(equalsToParentTicketId(parentTicketId));
		}
		if (parentType != null) {
			specification = specification.and(equalsToParentType(parentType));
		}
		if (meetType != null) {
			specification = specification.and(equalsToMeetType(meetType));
		}
		if (BooleanUtils.isTrue(meetInCurrentDay)) {
			specification = specification.and(meetInCurrentDay());
		}

		return specification;
	}
}
