package com.example.application9.DataPackage;

public class ScheduleList_second_new {
    private String lesson_name, cabinet_number, teacher_name, type_lesson, time_lesson, teacher_name_link, cabinet_number_link;
    private int num_schedule, separate;

    public ScheduleList_second_new(String lesson_name, String cabinet_number, String teacher_name, String type_lesson, String time_lesson, String teacher_name_link, String cabinet_number_link, int num_schedule, int separate) {
        this.lesson_name = lesson_name;
        this.cabinet_number = cabinet_number;
        this.teacher_name = teacher_name;
        this.type_lesson = type_lesson;
        this.time_lesson = time_lesson;
        this.teacher_name_link = teacher_name_link;
        this.cabinet_number_link = cabinet_number_link;
        this.num_schedule = num_schedule;
        this.separate = separate;
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

    public String getTeacher_name_link() {
        return teacher_name_link;
    }

    public void setTeacher_name_link(String teacher_name_link) {
        this.teacher_name_link = teacher_name_link;
    }

    public String getCabinet_number_link() {
        return cabinet_number_link;
    }

    public void setCabinet_number_link(String cabinet_number_link) {
        this.cabinet_number_link = cabinet_number_link;
    }

    public int getNum_schedule() {
        return num_schedule;
    }

    public void setNum_schedule(int num_schedule) {
        this.num_schedule = num_schedule;
    }

    public int getSeparate() {
        return separate;
    }

    public void setSeparate(int separate) {
        this.separate = separate;
    }
}
