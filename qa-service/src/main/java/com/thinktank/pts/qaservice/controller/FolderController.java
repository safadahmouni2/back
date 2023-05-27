package com.thinktank.pts.qaservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.thinktank.pts.qaservice.actor.ActorContext;
import com.thinktank.pts.qaservice.actor.ActorContextHolder;
import com.thinktank.pts.qaservice.dto.FolderDTO;
import com.thinktank.pts.qaservice.mapper.FolderMapper;
import com.thinktank.pts.qaservice.message.ResponseMessage;
import com.thinktank.pts.qaservice.model.Folder;
import com.thinktank.pts.qaservice.service.FolderService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author karabakaa
 *
 */
@RestController
@CrossOrigin
@Tag(name = "folder", description = "API for CRUD operations on folder.")
@Slf4j
@Transactional
public class FolderController {

	@Autowired
	private FolderService folderService;

	private FolderMapper mapper = new FolderMapper();

	@GetMapping("/folders/parent/{parentId}")
	public List<FolderDTO> getFoldersByParentId(@PathVariable(name = "parentId") Long parentId) {

		List<Folder> folderList = folderService.getFoldersByParentId(parentId);

		List<FolderDTO> folderDTOList = folderList.stream().filter(folder -> folder.getParentFolderRefId() == null)
				.map(folder -> mapper.mapToDTO(folder)).collect(Collectors.toList());
		return folderDTOList;
	}

	@GetMapping("/folders/subFolder/{parentFolderRefId}")
	public List<FolderDTO> getSubFoldersByRefFolderId(
			@PathVariable(name = "parentFolderRefId") Long parentFolderRefId) {

		List<Folder> folderList = folderService.getSubFoldersByParentFolderRefId(parentFolderRefId);
		List<FolderDTO> folderDTOList = folderList.stream().filter(folder -> folder.getParentFolderRefId() != null)
				.map(folder -> mapper.mapToDTO(folder)).collect(Collectors.toList());
		return folderDTOList;
	}

	@PostMapping("/folders")
	public FolderDTO addFolder(@RequestBody FolderDTO folderDTO, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());

		Folder folder = mapper.mapToEntity(folderDTO);
		return mapper.mapToDTO(folderService.saveOrUpdatefolder(folder));

	}

	@PutMapping("/folders/{folderId}/rename")
	public ResponseEntity<ResponseMessage> renameFolder(@PathVariable(name = "folderId") Long folderId,
			@RequestBody String folderName, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());

		String message = "";
		try {
			Folder result = folderService.renameFolder(folderId, folderName);
			if (result != null) {
				message = String.format("Folder %s renamed successfully", folderId);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
		} catch (Exception e) {
			log.error("Failed to rename Folder with id {}", folderId, e);
			message = "Could not rename the folder: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

	@DeleteMapping("/folders/{folderId}")
	public ResponseEntity<ResponseMessage> deleteFolder(@PathVariable(name = "folderId") Long folderId) {
		String message = "";
		try {
			folderService.deleteFolder(folderId);
			message = String.format("Folder %s deleted successfully", folderId);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
		} catch (Exception e) {
			log.error("Failed to delete Folder with id {}", folderId, e);
			message = "Could not delete the folder: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

		}
	}

	@PutMapping("/folders/dragAndDropFolder/{folderId}")
	public ResponseEntity<ResponseMessage> dargAndDrop(@PathVariable(name = "folderId") Long folderId,
			@RequestBody FolderDTO folderDTO, @RequestHeader(value = "actor") String userCode) {
		ActorContextHolder.setActor(ActorContext.builder().code(userCode).build());

		String message = "";
		try {
			Folder result = folderService.dragAndDropFolder(folderDTO);
			if (result != null) {
				message = String.format("Folder %s moved by drag and drop successfully", folderId);
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(message));
		} catch (Exception e) {
			log.error("Failed to move Folder with id {}", folderId, e);
			message = "Could not drag an drop the folder: " + e.getMessage() + "!";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
		}
	}

}
