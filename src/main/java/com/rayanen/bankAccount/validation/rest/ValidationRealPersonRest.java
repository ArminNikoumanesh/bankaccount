package com.rayanen.bankAccount.validation.rest;

import com.rayanen.bankAccount.dto.RealPersonDto;
import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.RealPersonDao;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


import java.util.Objects;


@Component
public class ValidationRealPersonRest {
  private  RealPersonDao realPersonDao;
  private  BankAccountDao bankAccountDao;
  private  Environment environment;

    public ValidationRealPersonRest(RealPersonDao realPersonDao, BankAccountDao bankAccountDao, Environment environment) {
        this.realPersonDao = realPersonDao;
        this.bankAccountDao = bankAccountDao;
        this.environment = environment;
    }

    public void RealValidation(RealPersonDto realPerson)throws Exception {
        String error = "";

            if (Objects.isNull(realPerson.getName()) || realPerson.getName().length() < 2)
                error +=environment.getProperty("exc.message.validation.real.name");
            if (Objects.isNull(realPerson.getFamilyName()) || realPerson.getFamilyName().length() < 2)
                error +=environment.getProperty("exc.message.validation.real.family");
            if (Objects.isNull(realPerson.getNationalCode()) || realPerson.getNationalCode().length() < 10 || realPerson.getNationalCode().length() > 10)
                error +=environment.getProperty("exc.message.validation.real.NationalCode");



        if(!error.equals(""))
            throw  new Exception(error);
    }





}
