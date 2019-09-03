package com.example.application9.FirebaseDatabaseBackdrop;

public class Backdrop {

    String approved, group_name,image_urls;

    public Backdrop() {}

    public Backdrop(String approved, String group_name, String image_urls) {
        this.approved = approved;
        this.group_name = group_name;
        this.image_urls = image_urls;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getImage_urls() {
        return image_urls;
    }

    public void setImage_urls(String image_urls) {
        this.image_urls = image_urls;
    }
}
