package com.rayanen.bankAccount.dto;

public enum GenderTypeDto {


    Male("مرد"),
    Female("زن");


    private String value;

    GenderTypeDto(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
