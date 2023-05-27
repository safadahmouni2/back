package com.thinktank.pts.agileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.agileservice.model.Meeting;

/**
 * 
 * @author karabakaa
 * @since May 25, 2023
 *
 */
@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}