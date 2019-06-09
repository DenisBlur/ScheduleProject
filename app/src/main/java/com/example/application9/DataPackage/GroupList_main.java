package com.example.application9.DataPackage;

public class GroupList_main {

    private String group_TITLE, group_ID;

    public GroupList_main(String group_TITLE, String group_ID) {
        this.group_TITLE = group_TITLE;
        this.group_ID = group_ID;
    }

    public String getGroup_TITLE() {
        return group_TITLE;
    }

    public void setGroup_TITLE(String group_TITLE) {
        this.group_TITLE = group_TITLE;
    }

    public String getGroup_ID() {
        return group_ID;
    }

    public void setGroup_ID(String group_ID) {
        this.group_ID = group_ID;
    }
}
