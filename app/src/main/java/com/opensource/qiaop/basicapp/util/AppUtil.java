package com.opensource.qiaop.basicapp.util;

/**
 * Created by qiaopeng@yuntetong.net on 2017/4/28.
 */

public class AppUtil {
    private static boolean debug;

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        AppUtil.debug = debug;
    }
}
