package com.thinktank.pts.qaservice.service;

import com.thinktank.pts.qaservice.model.Install;

public interface InstallService {

	Install getInstallByInstallId(int id);

	Install getNotYetExportedInstallByProductIdAndEnvId(long productId, long envId);

	void addInstall(Install install);

	Install updateInstall(Install install);

}
