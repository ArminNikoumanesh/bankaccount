package com.rayanen.bankAccount.dto;

public enum MenuItemType {
    MENU("منو"), PAGE("صفحه");
    private String value;

    MenuItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
