package com.opensource.qiaop.basicapp.network.rx.interceptor;


import com.opensource.qiaop.basicapp.network.rx.exception.ExceptionHandle;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by qiaopeng@yuntetong.net on 2017/5/2.
 */


public class HttpResponseFunc<T> implements Function<Throwable,Observable<T>> {
    @Override
    public Observable<T> apply(@NonNull Throwable throwable) throws Exception {
        return Observable.error(ExceptionHandle.handleException(throwable));
    }
}
