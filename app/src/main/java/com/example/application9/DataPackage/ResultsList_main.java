package com.example.application9.DataPackage;

public class ResultsList_main {

    private String num, namet_t, name_gr, name_l, hour_all, hour_out;
    private int progress;

    public ResultsList_main(String num, String namet_t, String name_gr, String name_l, String hour_all, String hour_out, int progress) {
        this.num = num;
        this.namet_t = namet_t;
        this.name_gr = name_gr;
        this.name_l = name_l;
        this.hour_all = hour_all;
        this.hour_out = hour_out;
        this.progress = progress;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNamet_t() {
        return namet_t;
    }

    public void setNamet_t(String namet_t) {
        this.namet_t = namet_t;
    }

    public String getName_gr() {
        return name_gr;
    }

    public void setName_gr(String name_gr) {
        this.name_gr = name_gr;
    }

    public String getName_l() {
        return name_l;
    }

    public void setName_l(String name_l) {
        this.name_l = name_l;
    }

    public String getHour_all() {
        return hour_all;
    }

    public void setHour_all(String hour_all) {
        this.hour_all = hour_all;
    }

    public String getHour_out() {
        return hour_out;
    }

    public void setHour_out(String hour_out) {
        this.hour_out = hour_out;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
