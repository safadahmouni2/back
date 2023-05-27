package com.thinktank.pts.qaservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.SourceSystem;

/**
 *
 * @author Taheni
 *
 */
@Repository
public interface SourceSystemRepository extends JpaRepository<SourceSystem, Integer> {
    Optional<SourceSystem> findBySourceSystemName(String sourceSystemName);

}
