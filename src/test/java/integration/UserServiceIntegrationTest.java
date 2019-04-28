package integration;

import com.github.abigail830.mybatictest.domain.UserService;
import com.github.abigail830.mybatictest.domain.model.User;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

/**
 * This is the integration test from domain->infrastructure->H2
 * <p>
 * Given it would start up spring for test, so it would takes longer time,
 * we always suggest to have more unit test then integration test,
 * so the CI could run quicker
 *
 * Said if you already have integration test from controller layer,
 * then maybe u don't need this test, and vice versa.
 *
 */
class UserServiceIntegrationTest extends IntegrationTestBase {

    @Autowired
    UserService userService;


    @Test
    @DatabaseSetup(value = "/dbunit/UserServiceTest_allUsers.xml", type = DatabaseOperation.CLEAN_INSERT)
    void should_get_all_users() {
        //when
        final List<User> allUsers = userService.getAllUsers();

        //then
        final List<User> expectUserList = Arrays.asList(new User(1, "user1", "F", "url1"),
                new User(2, "user2", "M", null));
        Assertions.assertEquals(2, allUsers.size());
        Assertions.assertEquals(expectUserList, allUsers);

    }

    @Test
    @DatabaseSetup(value = "/dbunit/UserServiceTest_allUsers.xml", type = DatabaseOperation.CLEAN_INSERT)
    void should_get_user_by_id() {
        //when
        final User user1 = userService.getUserById(1);

        //then
        final User expect = new User(1, "user1", "F", "url1");
        Assertions.assertEquals(expect, user1);
    }

    @Test
    @DatabaseSetup(value = "/dbunit/UserServiceTest_allUsers.xml", type = DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(value = "/dbunit/UserServiceTest_insertUser_expect.xml",
            table = "user_tbl",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    void should_add_user() {
        //given
        final User user3 = new User("user3", "M", "url3");
        //when
        userService.addUser(user3);
        //then
        //assert result is in above xml config
    }

    @Test
    @DatabaseSetup(value = "/dbunit/UserServiceTest_allUsers.xml", type = DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(value = "/dbunit/UserServiceTest_updateUser_expect.xml",
            table = "user_tbl",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    void should_update_user_if_exist() {
        //given
        final User user2 = new User(2, "user2", null, "url2");
        //when
        userService.updateUser(user2);
        //then
        //assert result is in above xml config
    }

    @Test
    void should_throw_exception_when_update_user_not_exit() {
        final User notExistUser = new User(10, "user2", null, "url2");

        Assertions.assertThrows(ResponseStatusException.class, () -> {
            userService.updateUser(notExistUser);
        });
    }

    @Test
    @DatabaseSetup(value = "/dbunit/UserServiceTest_allUsers.xml", type = DatabaseOperation.CLEAN_INSERT)
    @ExpectedDatabase(value = "/dbunit/UserServiceTest_deleteUser_expect.xml",
            table = "user_tbl",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    void should_delete_user_if_exist() {
        //when
        userService.deleteUser(1);
        //then
        //assert result is in above xml config
    }

    @Test
    void should_delete_user_if_not_exist() {
        Assertions.assertThrows(ResponseStatusException.class, () -> {
            userService.deleteUser(10);
        });
    }
}