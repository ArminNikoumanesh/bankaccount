package com.rayanen.bankAccount.serviceController;


import com.rayanen.bankAccount.model.dao.RealPersonDao;
import com.rayanen.bankAccount.model.entity.RealPerson;
import org.springframework.stereotype.Component;

import java.util.Objects;



@Component
public class Validetion {

    RealPersonDao realPersonDao;


    public Validetion(RealPersonDao realPersonDao) {
        this.realPersonDao = realPersonDao;

    }

    public void RealValideton(RealPerson realPerson)throws Exception {
 String error = "";
        Boolean report = realPersonDao.existsByNationalCode(realPerson.getNationalCode());

        if (!report) {
            if (Objects.isNull(realPerson.getName()) || realPerson.getName().length() < 2)
                error +="not valid name";
                if (Objects.isNull(realPerson.getFamilyName()) || realPerson.getFamilyName().length() < 2)
                    error +="not valid family";
                    if (Objects.isNull(realPerson.getNationalCode()) || realPerson.getNationalCode().length() < 10 || realPerson.getNationalCode().length() > 10)
                        error +="not valid nationalCode";

        }else{ error +="کد ملی قبلا وارد شده";
        }

                            if(!error.equals(""))
                             throw  new Exception(error);
    }





    public void realUpdateValidation(RealPerson realPerson)throws Exception{
        String error = "";
        Boolean report = realPersonDao.existsByNationalCode(realPerson.getNationalCode());

        if(!report) {

            error += "کد ملی شخص مورد نظر موجود نمیباشد";

        }
        if(!error.equals(""))
            throw  new Exception(error);
    }

}
