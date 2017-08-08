package com.wisewater.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@ComponentScan
@Transactional
@ContextConfiguration(
		locations={
				"file:src/test/resources/spring/jpa.xml",
				"file:src/test/resources/spring/services.xml"
})
public abstract class BaseServiceAbstractTest extends AbstractTransactionalJUnit4SpringContextTests{

	
}
