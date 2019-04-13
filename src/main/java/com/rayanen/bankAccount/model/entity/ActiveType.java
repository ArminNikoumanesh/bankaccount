package com.rayanen.bankAccount.model.entity;

public enum ActiveType {

    ACTIVE("active"),
    NONACTIVE("nonactive");

    private String value;

    ActiveType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
