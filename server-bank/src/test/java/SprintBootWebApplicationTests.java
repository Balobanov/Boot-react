import com.balobanov.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
@TestPropertySource(locations = "classpath:application.properties")
public class SprintBootWebApplicationTests {
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void getAll() throws Exception {
        String authorization = "Basic Y2xpZW50YXBwOjEyMzQ1Ng==";
        String contentAsString = mockMvc.perform(
                post("/oauth/token")
                        .header("Authorization", authorization)
                        .contentType(
                                MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "denis.aimprosoft@gmail.com")
                        .param("password", "NJrccBOLRReJ")
                        .param("grant_type", "password")
                        .param("scope", "read write")
                        .param("client_id", "clientapp")
                        .param("client_secret", "123456"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}