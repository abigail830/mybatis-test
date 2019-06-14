package com.github.abigail830.mybatictest.api;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("/wish-rpc")
public interface WishRpcApi {

    int getWishById(@JsonRpcParam(value = "wishId") Integer wishId);
}
