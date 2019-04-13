package com.rayanen.bankAccount.dto;

public enum AccountTypeDto {

    CURRENT("current"),
    NORMAL("normal");


    private String value;

    AccountTypeDto(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
