package com.thinktank.pts.agileservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * JSON object add to used in method getMyDailyScrum as response
 *
 * @author karabakaa
 * @since Apr 24, 2023
 *
 */
@Data
@RequiredArgsConstructor
public class MyDailyScrumDto {

	private final Long sprintId;

	private final Long dsId;

	private final Long sprintTicketId;

	private final String sprintName;

	private final LocalDate startDate;

	private final LocalDate endDate;

	private final LocalTime dsStartTime;

	private final Long dsDuration;

	private final LocalDateTime dsStartedAt;

	private final LocalDateTime dsFinishedAt;

	private final Long sprintStateId;

	private final Long productId;

	private final LocalDateTime dsCreated;

	private final Long dsSprintId;

	private final String scrumMasterBySprint;

	private final BigDecimal sprintProgress;

	// fields used only in result
	private String dsStatusInfo;
	private LocalTime dsEndTime;
}
