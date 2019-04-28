package integration.api;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import integration.IntegrationTestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * This is the integration test from controller->domain->infrastructure->H2
 * <p>
 * It is base on mockmvc lib which is spring/springboot depends
 * <p>
 * We could also use other ways to do similar stuff,
 * such as Rest-assured in UserRestAssuredIntegrationTest.java
 */

@AutoConfigureMockMvc
class UserMockMvcIntegrationTest extends IntegrationTestBase {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DatabaseSetup(value = "/dbunit/UserTest_allUsers.xml", type = DatabaseOperation.CLEAN_INSERT)
    void getAllUsers() throws Exception {
        //when
        final MvcResult mvcResult = mockMvc.perform(get("/users")).andReturn();

        //then
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());

        String expect = "[" +
                "{\"id\":1,\"userName\":\"user1\",\"gender\":\"F\",\"avatarUrl\":\"url1\"}," +
                "{\"id\":2,\"userName\":\"user2\",\"gender\":\"M\",\"avatarUrl\":null}" +
                "]";
        Assertions.assertEquals(expect, mvcResult.getResponse().getContentAsString());

    }
}