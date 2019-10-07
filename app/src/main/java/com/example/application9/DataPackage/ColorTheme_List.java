package com.example.application9.DataPackage;

public class ColorTheme_List {

    private String name_color, code_name;
    private int color_name_res, theme;

    public ColorTheme_List(String name_color, String code_name, int color_name_res, int theme) {
        this.name_color = name_color;
        this.code_name = code_name;
        this.color_name_res = color_name_res;
        this.theme = theme;
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

    public int getColor_name_res() {
        return color_name_res;
    }

    public void setColor_name_res(int color_name_res) {
        this.color_name_res = color_name_res;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }
}
