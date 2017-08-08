package com.opensource.qiaop.basicapp.network.rx.interceptor;

import com.opensource.qiaop.basicapp.entity.Result;
import com.opensource.qiaop.basicapp.network.rx.exception.ServerException;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * Created by qiaopeng@yuntetong.net on 2017/5/2.
 */

public class ServerResponseFun<T> implements  Function<Result<T>, T> {

    @Override
    public T apply(@NonNull Result<T> httpResult) throws Exception {
        if (httpResult.getStatus()!=200){
            throw new ServerException(httpResult.getStatus(),httpResult.getMessage());
        }
        return httpResult.getData();
    }
}
