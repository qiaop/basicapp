package com.opensource.qiaop.basicapp.network;

import com.opensource.qiaop.basicapp.entity.Message;
import com.opensource.qiaop.basicapp.entity.Result;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by qiaopeng@yuntetong.net on 2017/4/25.
 */

public interface ApiService {

    @GET("query")
    Observable<Result<List<Message>>> getMessage(@Query("type") String type, @Query("postid") String postid);


}
