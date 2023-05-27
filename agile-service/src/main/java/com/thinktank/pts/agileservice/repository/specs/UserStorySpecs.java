package com.thinktank.pts.agileservice.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import com.thinktank.pts.agileservice.model.UserStory;

/**
 * Specification generator. If you use string comparison make sure to lower both the search-expression and the
 * attribute.
 * 
 * @author chemkhih
 * @since 05-24-2023
 *
 */

public class UserStorySpecs {

	public static Specification<UserStory> equalsToSprintTicketId(Long sprintTicketId) {

		return (userStory, cq, cb) -> cb.equal(userStory.get("sprint").get("ticketId"), sprintTicketId);
	}

	public static Specification<UserStory> equalsToSprintId(Long sprintId) {
		return (userStory, cq, cb) -> cb.equal(userStory.get("sprint").get("id"), sprintId);
	}

	public static Specification<UserStory> specification(Long sprintId, Long sprintTicketId) {
		Specification<UserStory> specification = Specification.where(null);

		if (sprintId != null) {
			specification = specification.and(equalsToSprintId(sprintId));
		}
		if (sprintTicketId != null) {
			specification = specification.and(equalsToSprintTicketId(sprintTicketId));
		}

		return specification;
	}
}
