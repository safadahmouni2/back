package com.thinktank.pts.qaservice.service;

import java.util.List;
import java.util.Optional;

import com.thinktank.pts.qaservice.model.SourceSystem;

/**
 *
 * @author Taheni
 *
 */
public interface SourceSystemService {

	List<SourceSystem> getAllSourceSystems();

	SourceSystem saveSourceSystem(SourceSystem sourceSystem);

	Optional<SourceSystem> getSourceSystemBySourceSystemName(String sourceSystemName);

}
