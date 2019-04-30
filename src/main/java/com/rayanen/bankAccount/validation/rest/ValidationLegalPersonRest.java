package com.rayanen.bankAccount.validation.rest;

import com.rayanen.bankAccount.dto.LegalPersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


import java.util.Objects;


@Component
public class ValidationLegalPersonRest {

@Autowired
private Environment environment;

    public void LegalValidation(LegalPersonDto legalPerson)throws Exception {
        String error = "";


            if ( Objects.isNull(legalPerson.getName()) || legalPerson.getName().length() < 2)
                error +=environment.getProperty("exc.message.validation.legal.name")   ;
            if ( Objects.isNull(legalPerson.getManeger()) || legalPerson.getManeger().length() < 2)
                error +=environment.getProperty("exc.message.validation.legal.Maneger");
            if ( Objects.isNull(legalPerson.getCompanyCode()) || legalPerson.getCompanyCode().length()< 10 || legalPerson.getCompanyCode().length()>10)
                error +=environment.getProperty("exc.message.validation.legal.CompanyCode");


        if(!error.equals(""))
            throw  new Exception(error);

    }


}
