package com.opensource.qiaop.basicapp;

import android.app.Application;

/**
 * Created by qiaopeng@yuntetong.net on 2017/5/4.
 */

public class MyApplication extends Application {

    private static MyApplication mInstance;

    public static  MyApplication getmInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = (MyApplication) getApplicationContext();
    }
}
