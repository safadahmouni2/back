package com.thinktank.pts.qaservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.InstallDTO;
import com.thinktank.pts.qaservice.enums.InstallState;
import com.thinktank.pts.qaservice.mapper.InstallMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.Install;
import com.thinktank.pts.qaservice.service.InstallService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "installs", description = "API for CRUD operations on installs.")
@RequestMapping("/installs")
@Transactional
public class InstallController {

	@Autowired
	private InstallService installService;

	private InstallMapper mapper = new InstallMapper();

	@PostMapping
	@ResponseBody
	public ResponseEntity<ResponseMessage> addInstall(@RequestBody InstallDTO installDTO,
			@RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";

		try {
			Install install = mapper.mapToEntity(installDTO);

			install.setState(InstallState.CREATED);
			installService.addInstall(install);

			message = "Install: " + install.getInstallId() + " created successfully";
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not create the install: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@PutMapping("/{installId}")
	@ResponseBody
	public ResponseEntity<ResponseMessage> updateInstall(@PathVariable(name = "installId") int installId,
			@RequestBody InstallDTO installDTO, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());
		String message = "";

		try {
			Install install = mapper.mapToEntity(installDTO);
			install.setModifier(userCode);
			install.setState(InstallState.CREATED);
			Install updatedInstall = installService.updateInstall(install);
			if (updatedInstall != null) {
				message = "Install request: " + install.getInstallId() + " updated successfully";
			}
			return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
					.body(new ResponseMessage(message));

		} catch (Exception e) {
			message = "Could not update the install: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@GetMapping
	public InstallDTO getInstallByProductIdAndEnvId(@RequestParam(name = "productId") Long productId,
			@RequestParam(name = "envId") Long envId) {
		Install install = installService.getNotYetExportedInstallByProductIdAndEnvId(productId, envId);
		return mapper.mapToDTO(install);
	}

}
