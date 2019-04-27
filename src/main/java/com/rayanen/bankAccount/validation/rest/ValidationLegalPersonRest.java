package com.rayanen.bankAccount.validation.rest;

import com.rayanen.bankAccount.dto.LegalPersonDto;
import com.rayanen.bankAccount.model.dao.LegalPersonDao;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


@Component
public class ValidationLegalPersonRest {


    public void LegalValidation(LegalPersonDto legalPerson)throws Exception {
        String error = "";


            if ( Objects.isNull(legalPerson.getName()) || legalPerson.getName().length() < 2)
                error +="not valid name";
            if ( Objects.isNull(legalPerson.getManeger()) || legalPerson.getManeger().length() < 2)
                error +="not valid manager";
            if ( Objects.isNull(legalPerson.getCompanyCode()) || legalPerson.getCompanyCode().length()< 10 || legalPerson.getCompanyCode().length()>10)
                error +="not valid nationalCode";


        if(!error.equals(""))
            throw  new Exception(error);

    }


}
