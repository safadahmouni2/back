package com.thinktank.pts.qaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.thinktank.pts.qaservice.model.Install;

@RepositoryRestResource
@Repository
public interface InstallRepository extends JpaRepository<Install, Integer> {

	Install findByInstallId(int id);

	Install findByProductIdAndEnvIdAndPtsRefIsNull(long productId, long envId);

}
