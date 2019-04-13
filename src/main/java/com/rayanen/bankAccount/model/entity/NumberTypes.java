package com.rayanen.bankAccount.model.entity;

public enum NumberTypes {

     MOBILE("mobile"),
     HOME("home"),
     WORK("work");


     private String value;

     NumberTypes(String value){
         this.value=value;
     }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
