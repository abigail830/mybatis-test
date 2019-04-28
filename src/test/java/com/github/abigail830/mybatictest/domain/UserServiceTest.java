package com.github.abigail830.mybatictest.domain;

import com.github.abigail830.mybatictest.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLClientInfoException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * This is the unit test for UserService
 * <p>
 * It had mock up the userInterface to pretence what would come back from infrastructure
 * So when domain layer have more and more complex biz logic,
 * we could also use this way to only test those biz logic
 * <p>
 * It does not required to startup spring, so it would run quicker
 */
//@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserInfrastructure userInfrastructure;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void should_get_all_users() {
        //given
        UserService userService = new UserService(userInfrastructure);
        final List<User> expectUserList = Arrays.asList(new User(1, "user1", "F", "url1"),
                new User(2, "user2", "M", null));
        when(userInfrastructure.getAll()).thenReturn(expectUserList);

        //when
        final List<User> allUsers = userService.getAllUsers();

        //then
        Assertions.assertEquals(2, allUsers.size());
        Assertions.assertEquals(expectUserList, allUsers);

    }

    @Test
    void should_get_user_by_id() {
        //given
        UserService userService = new UserService(userInfrastructure);
        final User expect = new User(1, "user1", "F", "url1");
        when(userInfrastructure.getUserById(1)).thenReturn(expect);

        //when
        final User user1 = userService.getUserById(1);

        //then
        Assertions.assertEquals(expect, user1);
    }

    @Test
    void should_add_user() {
        //given
        UserService userService = new UserService(userInfrastructure);
        final User user3 = new User("user3", "M", "url3");
        //when
        userService.addUser(user3);
        //then
        //assert userInfrastructure is being called 1 times and with param user3
        verify(userInfrastructure, times(1)).insertUser(user3);
    }

    @Test
    void should_update_user() throws SQLClientInfoException {
        //given
        UserService userService = new UserService(userInfrastructure);
        final User user2 = new User(2, "user2", null, "url2");
        //when
        userService.updateUser(user2);
        //then
        //assert userInfrastructure is being called 1 times and with param user3
        verify(userInfrastructure, times(1)).updateUser(user2);
    }

    @Test
    void should_throw_exception_when_update_user_not_exist() throws SQLClientInfoException {
        //given
        UserService userService = new UserService(userInfrastructure);
        final User user2 = new User(2, "user2", null, "url2");
        doThrow(new SQLClientInfoException()).when(userInfrastructure).updateUser(user2);

        Assertions.assertThrows(ResponseStatusException.class,()->{
            userService.updateUser(user2);
        });
    }



    @Test
    void should_delete_user_exist() throws SQLClientInfoException {
        //when
        UserService userService = new UserService(userInfrastructure);
        userService.deleteUser(1);
        //then
        verify(userInfrastructure, times(1)).deleteUser(1);
    }

    @Test
    void should_throw_exception_when_delete_user__not_exist() throws SQLClientInfoException {

        UserService userService = new UserService(userInfrastructure);
        doThrow(new SQLClientInfoException()).when(userInfrastructure).deleteUser(1);

        Assertions.assertThrows(ResponseStatusException.class,()->{
            userService.deleteUser(1);
        });
    }
}