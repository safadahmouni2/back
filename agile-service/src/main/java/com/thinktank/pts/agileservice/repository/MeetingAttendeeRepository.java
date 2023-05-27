package com.thinktank.pts.agileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.agileservice.model.MeetingAttendee;

/**
 * 
 * @author karabakaa
 * @since May 23, 2023
 *
 */
@Repository
public interface MeetingAttendeeRepository extends JpaRepository<MeetingAttendee, Long> {
}