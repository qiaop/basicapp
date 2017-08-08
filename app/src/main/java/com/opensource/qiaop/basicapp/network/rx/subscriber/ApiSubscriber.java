package com.opensource.qiaop.basicapp.network.rx.subscriber;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.opensource.qiaop.basicapp.network.rx.exception.ApiException;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by qiaopeng@yuntetong.net on 2017/5/2.
 */

public abstract class ApiSubscriber<T> implements DialogInterface.OnCancelListener, Observer<T> {

    private WeakReference<Context> context;
    private ProgressDialog dialog;
    private boolean cancleable = false;//是否可以取消
    private boolean isShow;
    private Disposable mDisposable;

    public ApiSubscriber(Context context, boolean isShow, boolean cancleable) {
        this.context = new WeakReference<>(context);
        this.cancleable = cancleable;
        this.isShow = isShow;
        initProgressDialog();

    }

    public ApiSubscriber(Context context, boolean isShow, boolean cancleable, String message){
        this.context = new WeakReference<>(context);
        this.isShow = isShow;
        this.cancleable = cancleable;
        initProgressDialog(message);

    }

    public ApiSubscriber(Context context, boolean cancleable, @NonNull ProgressDialog dialog){
        this.context = new WeakReference<>(context);
        this.dialog = dialog;
        this.cancleable = cancleable;
        dialog.setCancelable(cancleable);
        dialog.setOnCancelListener(this);
    }

    private void initProgressDialog(){
        Context context = this.context.get();
        if (dialog==null &&context!=null){
            dialog = new ProgressDialog(context);
            dialog.setMessage("加载中...");
            dialog.setCancelable(cancleable);
            dialog.setOnCancelListener(this);
        }
    }

    private void initProgressDialog(String message) {
        Context context = this.context.get();
        if (dialog == null && context != null) {
            dialog = new ProgressDialog(context);
            dialog.setMessage(message);
            dialog.setCancelable(cancleable);
            dialog.setOnCancelListener(this);
        }
    }

    private void showProgressDialog() {
        Context context = this.context.get();
        if (dialog == null || context == null||!isShow) return;
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    private void dismissProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    @Override
    public void onError(Throwable e) {
        ApiException ex = (ApiException) e;
        Context context = this.context.get();
        if (context!=null){
            Toast.makeText(context,ex.getDisplayMessage(),Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
        showProgressDialog();
    }


    @Override
    public void onCancel(DialogInterface dialog) {
        unSubscribe();
    }
    public void unSubscribe(){
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
