package io.maksutov.heroes.battlegrounds;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Dmytro_Maksutov
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {HeroApplication.class, TestConfig.class})
public class HeroApplicationTest {


	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void mongoIsAvailable() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.get("/heroes")
			.accept(MediaType.APPLICATION_JSON)
		).andExpect(MockMvcResultMatchers.jsonPath("$.length()")
				.value(1));
	}
	
}
