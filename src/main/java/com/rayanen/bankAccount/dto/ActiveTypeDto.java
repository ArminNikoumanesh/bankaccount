package com.rayanen.bankAccount.dto;

public enum ActiveTypeDto {
    ACTIVE("active"),
    NONACTIVE("nonactive");

    private String value;

    ActiveTypeDto(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
