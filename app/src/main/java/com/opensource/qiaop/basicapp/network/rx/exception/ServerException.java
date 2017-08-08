package com.opensource.qiaop.basicapp.network.rx.exception;

/**
 * Created by qiaopeng@yuntetong.net on 2017/5/2.
 */

public class ServerException extends RuntimeException {

    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
