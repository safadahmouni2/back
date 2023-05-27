package com.thinktank.pts.qaservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinktank.pts.qaservice.mapper.InstallMapper;
import com.thinktank.pts.qaservice.model.Install;
import com.thinktank.pts.qaservice.repository.InstallRepository;
import com.thinktank.pts.qaservice.service.InstallService;

@Service
public class InstallServiceImpl implements InstallService {

	@Autowired
	private InstallRepository installRepository;

	InstallMapper mapper = new InstallMapper();

	@Override
	public void addInstall(Install install) {
		if (install != null) {
			installRepository.save(install);
		}

	}

	@Override
	public Install updateInstall(Install install) {
		Install result = null;
		Install installToUpdate = getInstallByInstallId(install.getInstallId());
		if (install != null) {
			mapper.patch(install, installToUpdate);
			installRepository.save(installToUpdate);
			result = installToUpdate;
		}
		return result;
	}

	@Override
	public Install getInstallByInstallId(int id) {
		return installRepository.findByInstallId(id);
	}

	@Override
	public Install getNotYetExportedInstallByProductIdAndEnvId(long productId, long envId) {
		return installRepository.findByProductIdAndEnvIdAndPtsRefIsNull(productId, envId);
	}

}
