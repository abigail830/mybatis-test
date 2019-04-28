package integration.api;

import com.github.abigail830.mybatictest.domain.UserService;
import com.github.abigail830.mybatictest.domain.model.User;
import integration.IntegrationTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * This is the integration test from controller->domain->infrastructure->H2
 * <p>
 * It is base on mockmvc lib which is spring/springboot depends
 * <p>
 * Additionally, here also used MockBean to simulate the return from uesrService
 * So, actually it is only testing the controller layer
 */
@AutoConfigureMockMvc
class UserMockBeanIntegrationTest extends IntegrationTestBase {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @BeforeEach
    public void setUp() {
        super.setUp();
        initMocks(this);
    }

    @Test
    void getAllUsers() throws Exception {
        //given
        List<User> users = Arrays.asList(new User(1, "user1", "M", "url1"));
        when(userService.getAllUsers()).thenReturn(users);

        //when
        final MvcResult mvcResult = mockMvc.perform(get("/users")).andReturn();

        //then
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

        String expect = "[{\"id\":1,\"userName\":\"user1\",\"gender\":\"M\",\"avatarUrl\":\"url1\"}]";
        Assertions.assertEquals(expect, mvcResult.getResponse().getContentAsString());

    }
}