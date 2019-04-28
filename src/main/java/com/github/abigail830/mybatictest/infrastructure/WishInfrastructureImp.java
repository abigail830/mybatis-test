package com.github.abigail830.mybatictest.infrastructure;

import com.github.abigail830.mybatictest.domain.WishInfrastructure;
import com.github.abigail830.mybatictest.domain.model.Wish;
import com.github.abigail830.mybatictest.infrastructure.entity.WishEntity;
import com.github.abigail830.mybatictest.infrastructure.mapper.WishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@Repository
public class WishInfrastructureImp implements WishInfrastructure {

    private static final int SUCCESS = 1;

    private WishMapper wishMapper;

    @Autowired
    public WishInfrastructureImp(WishMapper wishMapper) {
        this.wishMapper = wishMapper;
    }



    @Override
    public Optional<Wish> getWishById(Integer id) {
        final Optional<WishEntity> wishEntity = wishMapper.getWishById(id);

        return wishEntity.map(WishEntity::toWish);
    }


    @Override
    public void updateWishDescriptionById(String description, Integer id) throws SQLIntegrityConstraintViolationException {
        final WishEntity wishEntity = WishEntity.fromExistWish(description, id);
        final Integer result = wishMapper.updateWishDescription(wishEntity);

        if (result != SUCCESS) {
            throw new SQLIntegrityConstraintViolationException();
        }
    }

    @Override
    public void deleteWish(Integer id) throws SQLIntegrityConstraintViolationException {
        final Integer result = wishMapper.deleteWish(id);

        if (result != SUCCESS) {
            throw new SQLIntegrityConstraintViolationException();
        }
    }
}
