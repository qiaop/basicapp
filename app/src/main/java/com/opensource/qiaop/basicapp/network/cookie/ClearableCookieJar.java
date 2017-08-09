package com.opensource.qiaop.basicapp.network.cookie;

import okhttp3.CookieJar;

/**
 * Created by qiaop on 2017/5/3.
 */

public interface ClearableCookieJar extends CookieJar {

    /**
     * 清除所有会话cookie，同时保留持久的cookie。
     */
    void clearSession();

    /**
     * 清除所有cookie
     */
    void clear();
}
