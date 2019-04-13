package com.rayanen.bankAccount.dto;


public class LegalPersonDto extends CustomerDto {


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
