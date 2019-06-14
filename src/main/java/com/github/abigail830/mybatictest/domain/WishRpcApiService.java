package com.github.abigail830.mybatictest.domain;

import com.github.abigail830.mybatictest.api.WishRpcApi;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AutoJsonRpcServiceImpl
public class WishRpcApiService implements WishRpcApi {

    @Autowired
    private WishInfrastructure wishInfrastructure;

    @Override
    public int getWishById(Integer wishId) {
        return 0;
    }
}
