# basicapp
封装rxjava，okhttp与retrofit的网络请求基础模块



## 包含的功能

- 主要使用okhttp进行网络通信，封装okhttp日志打印
- 封装Cookie维持
- 封装使用Gson作为默认数据解析转化器
- 统一处理网络回调错误码
- 封装线程切换，进行网络请求不必考虑线程问题
- 封装ApiSubscriber，可设置是否显示ProgressDialog
- 使用rxlifecycle2进行生命周期的处理

基本上满足开发的一般需求。

## 使用示例

ApiService
```java
public interface ApiService {
    
    @GET("query")
    Observable<Result<List<Message>>> getMessage(@Query("type") String type, @Query("postid") String postid);
}

```


```java
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

```

# License

Copyright 2017 qiaop

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.