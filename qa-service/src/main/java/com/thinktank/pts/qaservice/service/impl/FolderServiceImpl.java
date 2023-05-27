package com.thinktank.pts.qaservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.dto.FolderDTO;
import com.thinktank.pts.qaservice.model.Folder;
import com.thinktank.pts.qaservice.model.TestCaseLibrary;
import com.thinktank.pts.qaservice.repository.FolderRepository;
import com.thinktank.pts.qaservice.service.FolderService;
import com.thinktank.pts.qaservice.service.TestCaseLibraryService;

/**
 * 
 * @author karabakaa
 *
 */
@Service
public class FolderServiceImpl implements FolderService {

	@Autowired
	private FolderRepository folderRepository;

	@Autowired
	private TestCaseLibraryService testCaseLibraryService;

	@Override
	public List<Folder> getFoldersByParentId(Long parentId) {
		return folderRepository.findByParentId(parentId);
	}

	@Override
	public List<Folder> getSubFoldersByParentFolderRefId(Long parentFolderRefId) {
		return folderRepository.findByParentFolderRefId(parentFolderRefId);
	}

	/**
	 * 
	 * Recursively finds all subfolders of a given folder by its ID.
	 * 
	 * @param folderId the ID of the folder for which to find subfolders
	 * 
	 * @return a list of all subfolders of the given folder
	 */

	@Override
	public List<Folder> getSubFoldersByFolderId(Long folderId) {
		Folder folder = folderRepository.findById(folderId).orElse(null);
		List<Folder> subFolders = new ArrayList<>();
		if (folder != null) {
			subFolders.add(folder);
			List<Folder> children = folderRepository.findByParentFolderRefId(folderId);
			for (Folder child : children) {
				List<Folder> childSubFolders = getSubFoldersByFolderId(child.getFolderId());
				subFolders.addAll(childSubFolders);
			}
		}
		return subFolders;
	}

	@Override
	public Folder getFolderByFolderID(Long folderID) {
		return folderRepository.findByFolderId(folderID);
	}

	@Override
	public Folder saveOrUpdatefolder(Folder folder) {
		return folderRepository.save(folder);

	}

	@Override
	public Folder renameFolder(Long folderId, String folderName) {
		Optional<Folder> folderOpt = folderRepository.findById(folderId);
		Folder result = null;
		if (folderOpt.isPresent()) {
			result = folderOpt.get();
			result.setName(folderName);
			folderRepository.save(result);
		}
		return result;
	}

	@Override
	public void deleteFolder(Long folderId) {
		folderRepository.deleteById(folderId);
	}

	@Override
	public Optional<Folder> getFolder(long folderId) {
		return folderRepository.findById(folderId);
	}

	@Override
	public List<Folder> getAllFolders() {
		return folderRepository.findAll();
	}

	@Override
	public List<Folder> getFolderByNameAndProductId(String name, long productId) {
		return folderRepository.findByNameAndProductId(name, productId);
	}

	@Override
	public Folder dragAndDropFolder(FolderDTO folderDTO) {

		Folder folderToDrag = this.getFolderByFolderID(folderDTO.getFolderId());
		folderToDrag.setParentFolderRefId(folderDTO.getParentFolderRefId());
		folderToDrag.setParentId(folderDTO.getParentId());

		List<TestCaseLibrary> testCaseLibrarys = testCaseLibraryService
				.getTestCasesLibraryByFolderId(folderDTO.getFolderId());
		if (!testCaseLibrarys.isEmpty()) {
			for(TestCaseLibrary testCase : testCaseLibrarys) {
				testCase.setProductId(folderDTO.getParentId());
				this.testCaseLibraryService.addTestCaseLibrary(testCase);
			}
		}
		return saveOrUpdatefolder(folderToDrag);
	}
	@Override
	public Folder getFolderByParentIdAndFolderPath(Long parentId, String folderPath) {
		List<Folder> folderList = folderRepository.findByParentId(parentId);

		for (Folder folder : folderList) {
			if (folder.getFolderPath().equals(folderPath)) {
				return folder;
			}
        }
		return null;
	}

}
