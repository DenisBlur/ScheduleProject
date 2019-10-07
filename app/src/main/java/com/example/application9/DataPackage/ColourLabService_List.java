package com.example.application9.DataPackage;

public class ColourLabService_List {

    private String name_color, code_name, code_name_1, code_name_2, bg_url;
    private int color_name_res, color_name_res_1, color_name_res_2;

    public ColourLabService_List(String name_color, String code_name, String code_name_1, String code_name_2, String bg_url, int color_name_res, int color_name_res_1, int color_name_res_2) {
        this.name_color = name_color;
        this.code_name = code_name;
        this.code_name_1 = code_name_1;
        this.code_name_2 = code_name_2;
        this.bg_url = bg_url;
        this.color_name_res = color_name_res;
        this.color_name_res_1 = color_name_res_1;
        this.color_name_res_2 = color_name_res_2;
    }

    public String getName_color() {
        return name_color;
    }

    public void setName_color(String name_color) {
        this.name_color = name_color;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public String getCode_name_1() {
        return code_name_1;
    }

    public void setCode_name_1(String code_name_1) {
        this.code_name_1 = code_name_1;
    }

    public String getCode_name_2() {
        return code_name_2;
    }

    public void setCode_name_2(String code_name_2) {
        this.code_name_2 = code_name_2;
    }

    public String getBg_url() {
        return bg_url;
    }

    public void setBg_url(String bg_url) {
        this.bg_url = bg_url;
    }

    public int getColor_name_res() {
        return color_name_res;
    }

    public void setColor_name_res(int color_name_res) {
        this.color_name_res = color_name_res;
    }

    public int getColor_name_res_1() {
        return color_name_res_1;
    }

    public void setColor_name_res_1(int color_name_res_1) {
        this.color_name_res_1 = color_name_res_1;
    }

    public int getColor_name_res_2() {
        return color_name_res_2;
    }

    public void setColor_name_res_2(int color_name_res_2) {
        this.color_name_res_2 = color_name_res_2;
    }
}
