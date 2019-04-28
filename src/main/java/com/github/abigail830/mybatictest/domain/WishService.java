package com.github.abigail830.mybatictest.domain;

import com.github.abigail830.mybatictest.domain.model.Wish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WishService {

    private WishInfrastructure wishInfrastructure;

    @Autowired
    public WishService(WishInfrastructure wishInfrastructure) {
        this.wishInfrastructure = wishInfrastructure;
    }

    public List<Wish> getAllWishesByUser(Integer userId) {
        return wishInfrastructure.getAllWishesByUser(userId);
    }
}
