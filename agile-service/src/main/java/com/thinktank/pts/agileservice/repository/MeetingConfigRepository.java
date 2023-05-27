package com.thinktank.pts.agileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.agileservice.model.MeetingConfig;

/**
 * 
 * @author karabakaa
 * @since May 23, 2023
 *
 */
@Repository
public interface MeetingConfigRepository
		extends JpaRepository<MeetingConfig, Long>, JpaSpecificationExecutor<MeetingConfig> {
}