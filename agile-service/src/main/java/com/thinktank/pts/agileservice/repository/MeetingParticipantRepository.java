package com.thinktank.pts.agileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.agileservice.model.MeetingParticipant;

/**
 * 
 * @author karabakaa
 * @since May 25, 2023
 *
 */
@Repository
public interface MeetingParticipantRepository extends JpaRepository<MeetingParticipant, Long> {
}