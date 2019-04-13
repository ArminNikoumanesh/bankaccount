package com.rayanen.bankAccount.model.entity;

public enum TransactionType {
    PICKEDUP("برداشت"),
    DEPOSIT("واریز");


    private String value;

    TransactionType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
