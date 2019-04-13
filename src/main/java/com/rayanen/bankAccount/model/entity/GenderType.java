package com.rayanen.bankAccount.model.entity;

public enum GenderType {

        Male("مرد"),
        Female("زن");


    private String value;

    GenderType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
