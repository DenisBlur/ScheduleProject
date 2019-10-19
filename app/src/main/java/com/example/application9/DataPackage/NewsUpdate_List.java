package com.example.application9.DataPackage;

public class NewsUpdate_List {

    private String title, image, text;

    public NewsUpdate_List(String title, String image, String text) {
        this.title = title;
        this.image = image;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
