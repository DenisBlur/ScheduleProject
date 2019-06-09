package com.example.application9.DataPackage;

public class ScheduleList_second {
    private String date_name, lesson_name, cabinet_number, teacher_name;
    private int type_schedule;

    public ScheduleList_second(String date_name, String lesson_name, String cabinet_number, String teacher_name, int type_schedule) {
        this.date_name = date_name;
        this.lesson_name = lesson_name;
        this.cabinet_number = cabinet_number;
        this.teacher_name = teacher_name;
        this.type_schedule = type_schedule;
    }

    public String getDate_name() {
        return date_name;
    }

    public void setDate_name(String date_name) {
        this.date_name = date_name;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getCabinet_number() {
        return cabinet_number;
    }

    public void setCabinet_number(String cabinet_number) {
        this.cabinet_number = cabinet_number;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public int getType_schedule() {
        return type_schedule;
    }

    public void setType_schedule(int type_schedule) {
        this.type_schedule = type_schedule;
    }
}
