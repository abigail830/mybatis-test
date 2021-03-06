package com.github.abigail830.mybatictest.domain;

import com.github.abigail830.mybatictest.domain.exception.BizException;
import com.github.abigail830.mybatictest.domain.model.Wish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.github.abigail830.mybatictest.domain.exception.ErrorCode.WISH_NOT_FOUND;

@Service
@Slf4j
public class WishService {

    private WishInfrastructure wishInfrastructure;

    @Autowired
    public WishService(WishInfrastructure wishInfrastructure) {
        this.wishInfrastructure = wishInfrastructure;
    }


    public Wish getWishById(Integer wishId) {
        final Optional<Wish> wishById = wishInfrastructure.getWishById(wishId);
        if (wishById.isPresent())
            return wishById.get();
        else
            throw new BizException(WISH_NOT_FOUND);
    }
}
