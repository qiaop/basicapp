package com.opensource.qiaop.basicapp.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.opensource.qiaop.basicapp.BuildConfig;
import com.opensource.qiaop.basicapp.MyApplication;
import com.opensource.qiaop.basicapp.network.cookie.ClearableCookieJar;
import com.opensource.qiaop.basicapp.network.cookie.PersistenceCookieJar;
import com.opensource.qiaop.basicapp.network.cookie.cache.SetCookieCache;
import com.opensource.qiaop.basicapp.network.cookie.persistence.SharedPrefsCookiePersistor;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qiaopeng@yuntetong.net on 2017/4/25.
 */

public class RetrofitHelper {

    private volatile static Retrofit retrofitInstance = null;

    public static <T> T creatApi(Class<T> clazz){
        return build().create(clazz);
    }

    private static Retrofit build(){
        if(null == retrofitInstance){
            synchronized (Retrofit.class){
                if(null == retrofitInstance){ // 双重检验锁,仅第一次调用时实例化
                    retrofitInstance = new Retrofit.Builder()
                            // baseUrl总是以/结束，@URL不要以/开头
                            .baseUrl(BuildConfig.API_SERVER_URL)
                            // 使用OkHttp Client
                            .client(buildOkHttpClient())
                            // 集成RxJava处理
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            // 集成Gson转换器
                            .addConverterFactory(buildGsonConverterFactory())
                            .build();
                }
            }
        }
        return retrofitInstance;
    }



    /**
     * 构建OkHttpClient
     * @return
     */
    private static OkHttpClient buildOkHttpClient(){
        //持久化cookie
        ClearableCookieJar cookieJar = new PersistenceCookieJar(new SetCookieCache(),new SharedPrefsCookiePersistor(MyApplication.getmInstance()));
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DeubgEnv?HttpLoggingInterceptor.Level.HEADERS:HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient().newBuilder().addInterceptor(loggingInterceptor)
                .cookieJar(cookieJar)
                .retryOnConnectionFailure(true)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .build();
    }

    /**
     * 构建GSON转换器
     * @return GsonConverterFactory
     */
    private static GsonConverterFactory buildGsonConverterFactory(){
        GsonBuilder builder = new GsonBuilder();
        builder.setLenient();

        // 注册类型转换适配器
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return null == json ? null : new Date(json.getAsLong());
            }
        });

        Gson gson = builder.create();
        return GsonConverterFactory.create(gson);
    }


}
