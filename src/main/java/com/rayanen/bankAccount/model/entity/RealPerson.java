package com.rayanen.bankAccount.model.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ARN_REAL_PERSON")
@EntityListeners(AuditingEntityListener.class)
public class RealPerson extends Customer {




    private Date birthday;

    private String familyName;

    @Column(unique = true)
    private String nationalCode;

    private GenderType gender;



    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public GenderType getGender() {
         return gender;
     }

     public void setGender(GenderType gender) {
         this.gender = gender;
     }

     public String getFamilyName() {
         return familyName;
     }

     public void setFamilyName(String familyName) {
         this.familyName = familyName;
     }

     public String getNationalCode() {
         return nationalCode;
     }

     public void setNationalCode(String nationalCode) {
         this.nationalCode = nationalCode;
     }


}

