package com.rayanen.bankAccount.serviceController;


import com.rayanen.bankAccount.model.dao.LegalPersonDao;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ValidationLegalPerson {

    LegalPersonDao legalPersonDao;

    public ValidationLegalPerson(LegalPersonDao legalPersonDao) {
        this.legalPersonDao = legalPersonDao;
    }


    public void saveLegalValidation(LegalPerson legalPerson)throws Exception {
        String error = "";
        Boolean report = legalPersonDao.existsByCompanyCode(legalPerson.getCompanyCode());

        if(!report) {
            if ( Objects.isNull(legalPerson.getName()) || legalPerson.getName().length() < 2)
                error +="not valid name";
            if ( Objects.isNull(legalPerson.getManeger()) || legalPerson.getManeger().length() < 2)
                error +="not valid manager";
            if ( Objects.isNull(legalPerson.getCompanyCode()) || legalPerson.getCompanyCode().length()< 10 || legalPerson.getCompanyCode().length()>10)
                error +="not valid nationalCode";

        }else{ error +="کد ثبتی قبلا وارد شده";

        }
        if(!error.equals(""))
            throw  new Exception(error);

    }


    public void updateLegalValidation(LegalPerson legalPerson)throws Exception{
        Boolean report = legalPersonDao.existsByCompanyCode(legalPerson.getCompanyCode());
        String error = "";
        if(report){
            if ( Objects.isNull(legalPerson.getName()) || legalPerson.getName().length() < 2)
                error +="not valid name";

            if (Objects.isNull(legalPerson.getManeger()) || legalPerson.getManeger().length() < 2)
                error +="not valid manager";
            if ( Objects.isNull(legalPerson.getCompanyCode()) || legalPerson.getCompanyCode().length()<10 || legalPerson.getCompanyCode().length()>10)
                error +="not valid nationalCode";

        }else{ error +="کد ثبتی موجود نمی باشد";
        }
        if(!error.equals(""))
            throw  new Exception(error);
    }


    public void legalFind (String companyCode)throws Exception {
        String error = "";
        LegalPerson legalPersonDaoByCompanyCode= legalPersonDao.findByCompanyCode(companyCode);

        if (Objects.isNull(legalPersonDaoByCompanyCode)) {
            error+="کد ثبتی مورد نظر یافت نشد";
        }
        if(!error.equals(""))
            throw  new Exception(error);

    }
    public void legalFindAll(LegalPerson legalPerson)throws Exception{

        String error = "";
        List<LegalPerson> result = legalPersonDao.findByNameAndCompanyCode(legalPerson.getName(), legalPerson.getCompanyCode());
        if(Objects.isNull(result)){
            error += " این مشخصات یافت نشد";
        }
        if(!error.equals(""))
            throw  new Exception(error);
    }




}