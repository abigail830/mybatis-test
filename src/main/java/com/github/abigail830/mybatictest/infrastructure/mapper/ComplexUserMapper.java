package com.github.abigail830.mybatictest.infrastructure.mapper;

import com.github.abigail830.mybatictest.infrastructure.entity.ComplexUserEntity;
import com.github.abigail830.mybatictest.infrastructure.entity.WishEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComplexUserMapper {

    List<ComplexUserEntity> getAllComplexUsers();

    ComplexUserEntity getComplexUserById(Integer id);

}
