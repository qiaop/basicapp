package com.opensource.qiaop.basicapp.network.cookie;

import com.opensource.qiaop.basicapp.network.cookie.cache.CookieCache;
import com.opensource.qiaop.basicapp.network.cookie.persistence.CookiePersistor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by qiaopeng@yuntetong.net on 2017/5/4.
 */

public class PersistenceCookieJar implements ClearableCookieJar {

    private CookieCache cache;
    private CookiePersistor persistor;

    public PersistenceCookieJar(CookieCache cache, CookiePersistor persistor) {
        this.cache = cache;
        this.persistor = persistor;
        this.cache.addAll(persistor.loadAll());
    }

    @Override
    synchronized public void clearSession() {
        cache.clear();
        cache.addAll(persistor.loadAll());
    }

    @Override
    public void clear() {
        cache.clear();
        persistor.clear();
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cache.addAll(cookies);
        persistor.saveAll(filterPersistenceCookies(cookies));
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> cookiesToRemove = new ArrayList<>();
        List<Cookie> validCookies = new ArrayList<>();

        for (Iterator<Cookie> it = cache.iterator(); it.hasNext();) {
            Cookie currentCookie = it.next();
            if (isCookieExpired(currentCookie)){
                cookiesToRemove.add(currentCookie);
                it.remove();
            }else if (currentCookie.matches(url)){
                validCookies.add(currentCookie);
            }
        }
        persistor.removeAll(cookiesToRemove);
        return validCookies;
    }

    private static List<Cookie> filterPersistenceCookies(List<Cookie> cookies){
        List<Cookie> persistenceCookies = new ArrayList<>();
        for (Cookie cookie : cookies) {
            if (cookie.persistent()){
                persistenceCookies.add(cookie);
            }
        }
        return persistenceCookies;
    }

    private static boolean isCookieExpired(Cookie cookie){
        return cookie.expiresAt()<System.currentTimeMillis();
    }
}
