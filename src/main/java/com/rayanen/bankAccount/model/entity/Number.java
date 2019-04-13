package com.rayanen.bankAccount.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "ARN_NUMBER_TEL")
public class Number {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="NUMBER_PHONE")
    private String number;

    private NumberTypes numberTypes;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public NumberTypes getNumberTypes() {
        return numberTypes;
    }

    public void setNumberTypes(NumberTypes numberTypes) {
        this.numberTypes = numberTypes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


