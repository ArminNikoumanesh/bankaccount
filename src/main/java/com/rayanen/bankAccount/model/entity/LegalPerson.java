package com.rayanen.bankAccount.model.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ARN_LEGAL_PERSON")
@EntityListeners(AuditingEntityListener.class)
public class LegalPerson extends Customer {


    @Column(unique = true)
    private String companyCode;

    private String maneger;



    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getManeger() {
        return maneger;
    }

    public void setManeger(String maneger) {
        this.maneger = maneger;
    }


}
