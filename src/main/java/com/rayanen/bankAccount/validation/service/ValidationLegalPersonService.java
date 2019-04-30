package com.rayanen.bankAccount.validation.service;


import com.rayanen.bankAccount.model.dao.LegalPersonDao;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ValidationLegalPersonService {

 private LegalPersonDao legalPersonDao;
 private Environment environment;

    public ValidationLegalPersonService(LegalPersonDao legalPersonDao, Environment environment) {
        this.legalPersonDao = legalPersonDao;
        this.environment = environment;
    }

    public void saveLegalValidation(LegalPerson legalPerson)throws Exception {
        String error = "";
        Boolean report = legalPersonDao.existsByCompanyCode(legalPerson.getCompanyCode());

        if(!report) {
            if ( Objects.isNull(legalPerson.getName()) || legalPerson.getName().length() < 2)
                error +=environment.getProperty("exc.message.validation.legal.name");
            if ( Objects.isNull(legalPerson.getManeger()) || legalPerson.getManeger().length() < 2)
                error +=environment.getProperty("exc.message.validation.legal.Maneger");
            if ( Objects.isNull(legalPerson.getCompanyCode()) || legalPerson.getCompanyCode().length()< 10 || legalPerson.getCompanyCode().length()>10)
                error +=environment.getProperty("exc.message.validation.legal.CompanyCode");

        }else{ error +=environment.getProperty("exc.message.validation.legal.CompanyCode.doplicate");

        }
        if(!error.equals(""))
            throw  new Exception(error);

    }


    public void updateLegalValidation(LegalPerson legalPerson)throws Exception{
        Boolean report = legalPersonDao.existsByCompanyCode(legalPerson.getCompanyCode());
        String error = "";
        if(report){
            if ( Objects.isNull(legalPerson.getName()) || legalPerson.getName().length() < 2)
                error +=environment.getProperty("exc.message.validation.legal.Maneger");

            if (Objects.isNull(legalPerson.getManeger()) || legalPerson.getManeger().length() < 2)
                error +=environment.getProperty("exc.message.validation.legal.CompanyCode");
            if ( Objects.isNull(legalPerson.getCompanyCode()) || legalPerson.getCompanyCode().length()<10 || legalPerson.getCompanyCode().length()>10)
                error +=environment.getProperty("exc.message.validation.legal.CompanyCode");

        }else{ error +=environment.getProperty("exc.message.validation.legal.CompanyCode.less");
        }
        if(!error.equals(""))
            throw  new Exception(error);
    }


    public void legalFind (String companyCode)throws Exception {
        String error = "";
        LegalPerson legalPersonDaoByCompanyCode= legalPersonDao.findByCompanyCode(companyCode);

        if (Objects.isNull(legalPersonDaoByCompanyCode)) {
            error+=environment.getProperty("exc.message.validation.legal.CompanyCode.find");
        }
        if(!error.equals(""))
            throw  new Exception(error);

    }
    public void legalFindAll(LegalPerson legalPerson)throws Exception{

        String error = "";
        List<LegalPerson> result = legalPersonDao.findByNameAndCompanyCode(legalPerson.getName(), legalPerson.getCompanyCode());
        if(Objects.isNull(result)){
            error += environment.getProperty("exc.message.validation.legal.CompanyCode.findAll");
        }
        if(!error.equals(""))
            throw  new Exception(error);
    }




}