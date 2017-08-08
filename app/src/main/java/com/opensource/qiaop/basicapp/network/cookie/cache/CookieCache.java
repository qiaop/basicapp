package com.opensource.qiaop.basicapp.network.cookie.cache;

import java.util.Collection;

import okhttp3.Cookie;

/**
 * Created by qiaopeng@yuntetong.net on 2017/5/3.
 */

public interface CookieCache extends Iterable<Cookie> {

    /**
     * 将所有新的Cookie添加到会话中，现有的Cookie将被覆盖。
     * @param cookies
     */
    void addAll(Collection<Cookie> cookies);

    /**
     * 清除会话中的所有Cookie。
     */
    void clear();
}
