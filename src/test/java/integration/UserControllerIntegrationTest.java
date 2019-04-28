package integration;

import com.github.abigail830.mybatictest.api.dto.SimpleUserResponseDTO;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.hasItems;

/**
 * This is the integration test from controller->domain->infrastructure->H2
 *
 * The annotation for startup springboot had been moved to IntegrationTestBase
 * <p>
 * It is base on rest-assured lib which is not spring depends
 * <p>
 * We could also use other ways to do similar stuff, such as MockMvc or TestRestTemplate
 */

public class UserControllerIntegrationTest extends IntegrationTestBase {

    @Test
    @DatabaseSetup(value = "/dbunit/UserServiceTest_allUsers.xml", type = DatabaseOperation.CLEAN_INSERT)
    void should_get_all_users() {
        when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body("userName", hasItems("user1", "user2"))
                .body("id", hasItems(1, 2));
    }

    @Test
    @DatabaseSetup(value = "/dbunit/UserServiceTest_allUsers.xml", type = DatabaseOperation.CLEAN_INSERT)
    void should_get_user_by_id() {

        final SimpleUserResponseDTO result = when()
                .get("/users/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .extract()
                .as(SimpleUserResponseDTO.class);

        Assertions.assertEquals("url1", result.getAvatarUrl());
        Assertions.assertEquals("user1", result.getUserName());
        Assertions.assertEquals("F", result.getGender());
    }

}