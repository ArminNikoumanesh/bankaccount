package com.rayanen.bankAccount.dto;



public class NumberDto {

    private Integer id;
    private String number;
    private NumberTypesDto numberTypes;



    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public NumberTypesDto getNumberTypes() {
        return numberTypes;
    }

    public void setNumberTypes(NumberTypesDto numberTypes) {
        this.numberTypes = numberTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
