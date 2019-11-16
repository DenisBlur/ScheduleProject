package com.example.application9.DataPackage;

public class NewsUpdate_List {

    private String title, image, text, progress, stage;

    public NewsUpdate_List(String title, String image, String text, String progress, String stage) {
        this.title = title;
        this.image = image;
        this.text = text;
        this.progress = progress;
        this.stage = stage;
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

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
