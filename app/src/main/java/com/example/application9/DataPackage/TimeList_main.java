package com.example.application9.DataPackage;

public class TimeList_main {

    private String time_support;
    private int type;

    public TimeList_main(String time_support, int type) {
        this.time_support = time_support;
        this.type = type;
    }

    public String getTime_support() {
        return time_support;
    }

    public void setTime_support(String time_support) {
        this.time_support = time_support;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
