package com.rayanen.bankAccount.validation.rest;

import com.rayanen.bankAccount.dto.RealPersonDto;
import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.RealPersonDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.RealPerson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


@Component
public class ValidationRealPersonRest {
    RealPersonDao realPersonDao;
    BankAccountDao bankAccountDao;

    public ValidationRealPersonRest(RealPersonDao realPersonDao, BankAccountDao bankAccountDao) {
        this.realPersonDao = realPersonDao;
        this.bankAccountDao = bankAccountDao;
    }


    public void RealValidation(RealPersonDto realPerson)throws Exception {
        String error = "";

            if (Objects.isNull(realPerson.getName()) || realPerson.getName().length() < 2)
                error +="not valid name";
            if (Objects.isNull(realPerson.getFamilyName()) || realPerson.getFamilyName().length() < 2)
                error +="not valid family";
            if (Objects.isNull(realPerson.getNationalCode()) || realPerson.getNationalCode().length() < 10 || realPerson.getNationalCode().length() > 10)
                error +="not valid nationalCode";



        if(!error.equals(""))
            throw  new Exception(error);
    }





}
