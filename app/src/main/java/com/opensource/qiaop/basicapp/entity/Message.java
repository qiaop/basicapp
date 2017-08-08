package com.opensource.qiaop.basicapp.entity;

import java.io.Serializable;

/**
 * Created by qiaopeng@yuntetong.net on 2017/4/28.
 */

public class Message implements Serializable {

    private String time;
    private String ftime;
    private String context;
    private String location;

    public Message(String time, String ftime, String context, String location) {
        this.time = time;
        this.ftime = ftime;
        this.context = context;
        this.location = location;
    }

    public Message() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Message{" +
                "time='" + time + '\'' +
                ", ftime='" + ftime + '\'' +
                ", context='" + context + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
