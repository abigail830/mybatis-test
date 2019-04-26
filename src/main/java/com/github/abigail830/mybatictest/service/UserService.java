package com.github.abigail830.mybatictest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserInfrastructure userInfrastructure;


    public List<User> getAllUsers(){
        return userInfrastructure.getAll();
    }
}
