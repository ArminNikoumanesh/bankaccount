package com.rayanen.bankAccount.dto;

public enum NumberTypesDto {
        MOBILE("mobile"),
    HOME("home"),
    WORK("work");


    private String value;

    NumberTypesDto(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
