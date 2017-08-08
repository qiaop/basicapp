package com.opensource.qiaop.basicapp.network.cookie.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import okhttp3.Cookie;

/**
 * Created by qiaopeng@yuntetong.net on 2017/5/3.
 */

class IdentifiableCookie {

    private Cookie cookie;

    static List<IdentifiableCookie> decorateAll(Collection<Cookie> cookies) {
        List<IdentifiableCookie> identifiableCookies = new ArrayList<>(cookies.size());
        for (Cookie cookie : cookies) {
            identifiableCookies.add(new IdentifiableCookie(cookie));
        }
        return identifiableCookies;
    }

    IdentifiableCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    Cookie getCookie() {
        return cookie;
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
        if (!(o instanceof IdentifiableCookie)) return false;

        IdentifiableCookie that = (IdentifiableCookie) o;

        return that.cookie.name().equals(this.cookie.name())
                && that.cookie.domain().equals(this.cookie.domain())
                && that.cookie.path().equals(this.cookie.path())
                && that.cookie.secure() == this.cookie.secure()
                && that.cookie.hostOnly() == this.cookie.hostOnly();

    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + cookie.name().hashCode();
        hash = 31 * hash + cookie.domain().hashCode();
        hash = 31 * hash + cookie.path().hashCode();
        hash = 31 * hash + (cookie.secure() ? 0 : 1);
        hash = 31 * hash + (cookie.hostOnly() ? 0 : 1);
        return hash;
    }
}
