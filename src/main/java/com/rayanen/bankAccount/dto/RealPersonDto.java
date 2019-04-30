package com.rayanen.bankAccount.dto;




import java.util.Date;

public class RealPersonDto extends  CustomerDto {


    private Date birthday;

    private String familyName;


    private String nationalCode;

    private GenderTypeDto gender;



    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public GenderTypeDto getGender() {
        return gender;
    }

    public void setGender(GenderTypeDto gender) {
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
