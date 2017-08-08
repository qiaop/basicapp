package com.opensource.qiaop.basicapp;

import android.os.Bundle;
import android.widget.Button;

import com.opensource.qiaop.basicapp.entity.Message;
import com.opensource.qiaop.basicapp.entity.Result;
import com.opensource.qiaop.basicapp.network.ApiService;
import com.opensource.qiaop.basicapp.network.rx.interceptor.HttpResponseFunc;
import com.opensource.qiaop.basicapp.network.rx.interceptor.ServerResponseFun;
import com.opensource.qiaop.basicapp.network.rx.transformer.SchedulerTransformer;
import com.opensource.qiaop.basicapp.network.retrofit.RetrofitHelper;
import com.opensource.qiaop.basicapp.network.rx.subscriber.ApiSubscriber;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class MainActivity extends RxAppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button2);
        RetrofitHelper.creatApi(ApiService.class).getMessage("yuantong","200382770316")
                .compose(MainActivity.this.<Result<List<Message>>>bindUntilEvent(ActivityEvent.DESTROY))
                .compose(SchedulerTransformer.<Result<List<Message>>>transformer())
                .map(new ServerResponseFun<List<Message>>())
                .onErrorResumeNext(new HttpResponseFunc<List<Message>>())
                .subscribe(new ApiSubscriber<List<Message>>(MainActivity.this,true,false) {
                    @Override
                    public void onNext(@NonNull List<Message> messages) {
                        button.setText(messages.toString());
                    }
                });


    }
}
