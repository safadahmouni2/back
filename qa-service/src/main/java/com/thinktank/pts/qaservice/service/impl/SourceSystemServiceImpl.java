package com.thinktank.pts.qaservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.model.SourceSystem;
import com.thinktank.pts.qaservice.repository.SourceSystemRepository;
import com.thinktank.pts.qaservice.service.SourceSystemService;

/**
 *
 * @author Taheni
 *
 */
@Service
public class SourceSystemServiceImpl implements SourceSystemService {

	private final SourceSystemRepository sourceSystemRepository;

	public SourceSystemServiceImpl(SourceSystemRepository sourceSystemRepository) {
		this.sourceSystemRepository = sourceSystemRepository;
	}

	@Override
	public SourceSystem saveSourceSystem(SourceSystem sourceSystem) {
		return sourceSystemRepository.save(sourceSystem);
	}

@Override
	public  Optional<SourceSystem> getSourceSystemBySourceSystemName(String sourceSystemName) {
	return sourceSystemRepository.findBySourceSystemName(sourceSystemName);
	}
	@Override
	public List<SourceSystem> getAllSourceSystems(){
		return sourceSystemRepository.findAll();
	}
}
