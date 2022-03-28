package com.br.main.models;

public enum GenderEnum {
    FEMALE("Female"),
    MALE("Male");

    private String value;
    private GenderEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
