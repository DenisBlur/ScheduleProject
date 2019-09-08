package com.example.application9.DataPackage;

public class VariableList {

    private String name, value, responsible, type;

    public VariableList(String name, String value, String responsible, String type) {
        this.name = name;
        this.value = value;
        this.responsible = responsible;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
