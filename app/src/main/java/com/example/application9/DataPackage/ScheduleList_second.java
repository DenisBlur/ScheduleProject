package com.example.application9.DataPackage;

public class ScheduleList_second {
    private String date_name, lesson_name, cabinet_number, teacher_name, type_lesson;
    private int type_schedule, num_schedule;

    public ScheduleList_second(String date_name, String lesson_name, String cabinet_number, String teacher_name, String type_lesson, int type_schedule, int num_schedule) {
        this.date_name = date_name;
        this.lesson_name = lesson_name;
        this.cabinet_number = cabinet_number;
        this.teacher_name = teacher_name;
        this.type_lesson = type_lesson;
        this.type_schedule = type_schedule;
        this.num_schedule = num_schedule;
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

    public String getType_lesson() {
        return type_lesson;
    }

    public void setType_lesson(String type_lesson) {
        this.type_lesson = type_lesson;
    }

    public int getType_schedule() {
        return type_schedule;
    }

    public void setType_schedule(int type_schedule) {
        this.type_schedule = type_schedule;
    }

    public int getNum_schedule() {
        return num_schedule;
    }

    public void setNum_schedule(int num_schedule) {
        this.num_schedule = num_schedule;
    }
}
