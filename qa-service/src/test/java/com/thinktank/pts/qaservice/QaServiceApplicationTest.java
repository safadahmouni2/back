package com.thinktank.pts.qaservice;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.thinktank.pts.qaservice.config.AbstractPersistenceTestBase;

public class QaServiceApplicationTest extends AbstractPersistenceTestBase {

	@Autowired
	private ApplicationContext context;

	@Ignore("liquibase change sets specific for mysql")
	void contextLoads() {
		Assertions.assertThat(this.context).isNotNull();
	}
}
