package com.thinktank.pts.qaservice.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.SourceSystemDTO;
import com.thinktank.pts.qaservice.mapper.SourceSystemMapper;
import com.thinktank.pts.qaservice.model.SourceSystem;
import com.thinktank.pts.qaservice.service.SourceSystemService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
@Transactional
public class SourceSystemController {

    private final SourceSystemService sourceSystemService;

    private final SourceSystemMapper sourceSystemMapper = new SourceSystemMapper();

    public SourceSystemController(SourceSystemService sourceSystemService) {
        this.sourceSystemService = sourceSystemService;
    }

    @GetMapping("/source-systems")
	public List<SourceSystemDTO> getAllSourceSystems() {
        List<SourceSystem> sourceSystemList = sourceSystemService.getAllSourceSystems();
        return sourceSystemList.stream().map(sourceSystem -> sourceSystemMapper.mapToDTO(sourceSystem))
                .collect(Collectors.toList());
    }

	@PostMapping("/source-systems")
	public ResponseEntity<SourceSystem> addSourceSystem(@RequestBody SourceSystemDTO sourceSystemDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		SourceSystem sourceSystem = null;
		try {
			 sourceSystem = sourceSystemService
					.saveSourceSystem(sourceSystemMapper.mapToEntity(sourceSystemDTO));
				log.info("Source system created with name {}", sourceSystem.getSourceSystemName());
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
					.body(sourceSystem);
		} catch (Exception e) {
			log.info("Fail to create Source system {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
					.body(sourceSystem);
		}

	}

	@GetMapping("/source-systems/{sourceSystemName}")
	public ResponseEntity<?> getSourceSystemByName(@PathVariable(name = "sourceSystemName") String sourceSystemName) {
		try {
			Optional<SourceSystem> sourceSystemExist= sourceSystemService.getSourceSystemBySourceSystemName(sourceSystemName);
			if(sourceSystemExist.isPresent()){
				log.info("Source system  {} found", sourceSystemExist.get().getSourceSystemName());
				return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
						.body(sourceSystemExist.get());
			}
		} catch (Exception e) {
			log.info("Fail to get Source system {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON)
					.body("Source system  Not  found "+sourceSystemName);
		}
		return null;
	}



}
