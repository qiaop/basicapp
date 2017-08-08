package com.opensource.qiaop.basicapp.network.rx.exception;

/**
 * Created by qiaopeng@yuntetong.net on 2017/5/2.
 * 自定义异常类
 */

public class ApiException extends Exception {

    private int code;
    private String displayMessage;

    public ApiException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }
}
