package com.github.abigail830.mybatictest.domain;

import com.github.abigail830.mybatictest.domain.exception.CustomizeException;
import com.github.abigail830.mybatictest.domain.exception.ErrorCode;
import com.github.abigail830.mybatictest.domain.model.User;
import com.github.abigail830.mybatictest.domain.model.Wish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private UserInfrastructure userInfrastructure;

    @Autowired
    public UserService(UserInfrastructure userInfrastructure) {
        this.userInfrastructure = userInfrastructure;
    }

    public List<User> getAllUsers(){
        return userInfrastructure.getAll();
    }


    public User getUserById(Integer id){
        final Optional<User> user = userInfrastructure.getUserById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            log.warn("Fail to query user {}", id);
            throw new CustomizeException(HttpStatus.NOT_FOUND);
        }
    }

    public void addUser(User user){
        userInfrastructure.insertUser(user);
    }

    public void updateUser(User user){
        final Integer result = userInfrastructure.updateUser(user);

        if (!userInfrastructure.isSuccess(result)) {
            log.warn("Fail to update user {}", user);
            throw new CustomizeException(HttpStatus.BAD_REQUEST, ErrorCode.FAIL_TO_UPDATE_USER.name());
        }
    }

    public void deleteUser(Integer id){
        final Integer result = userInfrastructure.deleteUser(id);
        if (!userInfrastructure.isSuccess(result)) {
            log.warn("User {} not exist, so nth to delete", id);
        }

    }

    public List<Wish> getAllWishesByUser(Integer userId) {
        return userInfrastructure.getAllWishesByUser(userId);
    }

    public void insertWishForUser(Integer userId, Wish wish) {
        try {
            userInfrastructure.insertWish(wish, userId);
        } catch (SQLIntegrityConstraintViolationException e) {
            log.warn("Fail to insert wish {} for user with id {}", wish, userId);
            throw new CustomizeException(HttpStatus.BAD_REQUEST, ErrorCode.FAIL_TO_INSERT_WISH.name(), e);
        }
    }

}
