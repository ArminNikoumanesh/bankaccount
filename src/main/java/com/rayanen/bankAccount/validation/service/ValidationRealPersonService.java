package com.rayanen.bankAccount.validation.service;


import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.RealPersonDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.RealPerson;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;



@Component
public class ValidationRealPersonService {

    RealPersonDao realPersonDao;
    BankAccountDao bankAccountDao;

    public ValidationRealPersonService(RealPersonDao realPersonDao, BankAccountDao bankAccountDao) {
        this.realPersonDao = realPersonDao;
        this.bankAccountDao = bankAccountDao;
    }


    public void RealValidation(RealPerson realPerson)throws Exception {
 String error = "";
        boolean report = realPersonDao.existsByNationalCode(realPerson.getNationalCode());

        if (!report) {
            if (Objects.isNull(realPerson.getName()) || realPerson.getName().length() < 2)
                error +="فیلد نام صحیح پر نشده است";
                if (Objects.isNull(realPerson.getFamilyName()) || realPerson.getFamilyName().length() < 2)
                    error +="فیلد نام خوانوادگی صحیح پر نشده";
                    if (Objects.isNull(realPerson.getNationalCode()) || realPerson.getNationalCode().length() < 10 || realPerson.getNationalCode().length() > 10)
                        error +="فیلد شماره کد ملی صحیح پر نشده";

        }else{ error +="کد ملی قبلا وارد شده";
        }

                            if(!error.equals(""))
                             throw  new Exception(error);
    }





    public void realUpdateValidation(RealPerson realPerson)throws Exception{
        String error = "";
        boolean report = realPersonDao.existsByNationalCode(realPerson.getNationalCode());

        if(!report) {

            error += "کد ملی شخص مورد نظر موجود نمیباشد";

        }
        if(!error.equals(""))
            throw  new Exception(error);
    }


    public void realFindAll(RealPerson realPerson)throws Exception{
        String error = "";
        List<RealPerson> result = realPersonDao.findByNameAndFamilyName(realPerson.getName(), realPerson.getFamilyName());
        if(Objects.isNull(result)){
            error += "شخصی با این مشخصات یافت نشد";
        }
        if(!error.equals(""))
            throw  new Exception(error);
    }



    public void findByNationalCode ( String  nationalCode)throws Exception {
        String error = "";
        RealPerson realPersonDaoByNationalCode = realPersonDao.findByNationalCode(nationalCode);


            if (Objects.isNull(realPersonDaoByNationalCode)){


            error += "کد ملی شخص مورد نظر موجود نمیباشد";
        }
        if(!error.equals(""))
            throw  new Exception(error);

    }

}
