package com.wisewater.base;



import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
	locations={
			"file:src/test/resources/spring/appServlet/servlet-context.xml",
			"file:src/test/resources/spring/services.xml",
			"file:src/test/resources/spring/jpa.xml"
})
public class BaseControllerAbstractTest extends AbstractJUnit4SpringContextTests{
	
	@Autowired
    private WebApplicationContext wac;
	
	protected MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
}
