package com.rayanen.bankAccount.model.entity;

public enum AccountType {
    CURRENT("current"),
    NORMAL("normal");


    private String value;

    AccountType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
