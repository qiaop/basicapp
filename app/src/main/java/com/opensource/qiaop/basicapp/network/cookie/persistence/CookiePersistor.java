package com.opensource.qiaop.basicapp.network.cookie.persistence;

import java.util.Collection;
import java.util.List;

import okhttp3.Cookie;

/**
 * Created by qiaop on 2017/5/3.
 * CookiePersistor处理持久化Cookie存储。
 */
public interface CookiePersistor {

    List<Cookie> loadAll();

    /**
     * 持久化所有Cookie，现有Cookie将被覆盖。
     * @param cookies
     */
    void saveAll(Collection<Cookie> cookies);

    /**
     * 从删除指定持久化的Cookie
     * @param cookies
     */
    void removeAll(Collection<Cookie> cookies);

    /**
     * 清除所有持久化的Cookie
     */
    void clear();
}
