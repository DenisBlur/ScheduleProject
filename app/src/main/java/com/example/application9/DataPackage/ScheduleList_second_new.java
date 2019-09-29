package com.example.application9.DataPackage;

public class ScheduleList_second_new {
    private String lesson_name, cabinet_number, teacher_name, type_lesson, time_lesson;
    private int num_schedule;

    public ScheduleList_second_new(String lesson_name, String cabinet_number, String teacher_name, String type_lesson, String time_lesson, int num_schedule) {
        this.lesson_name = lesson_name;
        this.cabinet_number = cabinet_number;
        this.teacher_name = teacher_name;
        this.type_lesson = type_lesson;
        this.time_lesson = time_lesson;
        this.num_schedule = num_schedule;
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

    public String getTime_lesson() {
        return time_lesson;
    }

    public void setTime_lesson(String time_lesson) {
        this.time_lesson = time_lesson;
    }

    public int getNum_schedule() {
        return num_schedule;
    }

    public void setNum_schedule(int num_schedule) {
        this.num_schedule = num_schedule;
    }
}
