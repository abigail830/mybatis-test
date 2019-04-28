package integration;

import com.github.abigail830.mybatictest.domain.model.User;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the integration test from controller->domain->infrastructure->H2
 * <p>
 * The annotation for startup springboot had been moved to IntegrationTestBase
 * <p>
 * It is base on rest-assured lib which is not spring depends, We could also use other ways to do similar stuff,
 * such as MockMvc way in UserMockMvcIntegrationTest.java
 */

public class UserRestTemplateIntegrationTest extends IntegrationTestBase {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    @DatabaseSetup(value = "/dbunit/UserServiceTest_allUsers.xml", type = DatabaseOperation.CLEAN_INSERT)
    void should_get_all_users() {
        List<User> userList = new ArrayList<>();
        final ResponseEntity<? extends List> result = testRestTemplate
                .exchange("/users", HttpMethod.GET, null, userList.getClass());

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(2, result.getBody().size());
    }

    @Test
    @DatabaseSetup(value = "/dbunit/UserServiceTest_allUsers.xml", type = DatabaseOperation.CLEAN_INSERT)
    void should_get_user_by_id() {

        //when
        //It would required to have NoArgsConstructor for User
        final ResponseEntity<User> result = testRestTemplate
                .exchange("/users/1", HttpMethod.GET, null, User.class);

        //then
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(1, result.getBody().getId());
        Assertions.assertEquals("F", result.getBody().getGender());
        Assertions.assertEquals("url1", result.getBody().getAvatarUrl());
        Assertions.assertEquals("user1", result.getBody().getUserName());
    }

}