package com.rayanen.bankAccount.validation.service;


import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.RealPersonDao;
import com.rayanen.bankAccount.model.entity.RealPerson;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;



@Component
public class ValidationRealPersonService {


    private Environment environment;
    private  RealPersonDao realPersonDao;
    private BankAccountDao bankAccountDao;

    public ValidationRealPersonService(Environment environment, RealPersonDao realPersonDao, BankAccountDao bankAccountDao) {
        this.environment = environment;
        this.realPersonDao = realPersonDao;
        this.bankAccountDao = bankAccountDao;
    }

    public void RealValidation(RealPerson realPerson)throws Exception {
 String error = "";
        boolean report = realPersonDao.existsByNationalCode(realPerson.getNationalCode());

        if (!report) {
            if (Objects.isNull(realPerson.getName()) || realPerson.getName().length() < 2)
                error +=environment.getProperty("exc.message.validation.real.name") ;
                if (Objects.isNull(realPerson.getFamilyName()) || realPerson.getFamilyName().length() < 2)
                    error +=environment.getProperty("exc.message.validation.real.family");
                    if (Objects.isNull(realPerson.getNationalCode()) || realPerson.getNationalCode().length() < 10 || realPerson.getNationalCode().length() > 10)
                        error +=environment.getProperty("exc.message.validation.real.NationalCode");

        }else{ error +=environment.getProperty("exc.message.validation.real.NationalCode.doplicate");
        }

                            if(!error.equals(""))
                             throw  new Exception(error);
    }





    public void realUpdateValidation(RealPerson realPerson)throws Exception{
        String error = "";
        boolean report = realPersonDao.existsByNationalCode(realPerson.getNationalCode());

        if(report) {
            if (Objects.isNull(realPerson.getName()) || realPerson.getName().length() < 2)
                error +=environment.getProperty("exc.message.validation.real.name") ;
            if (Objects.isNull(realPerson.getFamilyName()) || realPerson.getFamilyName().length() < 2)
                error +=environment.getProperty("exc.message.validation.real.family");
            if (Objects.isNull(realPerson.getNationalCode()) || realPerson.getNationalCode().length() < 10 || realPerson.getNationalCode().length() > 10)
                error +=environment.getProperty("exc.message.validation.real.NationalCode");


        } else{ error += environment.getProperty("exc.message.validation.real.NationalCode.less");

        }
        if(!error.equals(""))
            throw  new Exception(error);
    }


    public void realFindAll(RealPerson realPerson)throws Exception{
        String error = "";
        List<RealPerson> result = realPersonDao.findByNameAndFamilyName(realPerson.getName(), realPerson.getFamilyName());
        if(Objects.isNull(result)){
            error += environment.getProperty("exc.message.validation.real.NationalCode.findAll");
        }
        if(!error.equals(""))
            throw  new Exception(error);
    }



    public void findByNationalCode ( String  nationalCode)throws Exception {
        String error = "";
        RealPerson realPersonDaoByNationalCode = realPersonDao.findByNationalCode(nationalCode);


            if (Objects.isNull(realPersonDaoByNationalCode)){


            error += environment.getProperty("exc.message.validation.real.NationalCode.findByNationalCode");
        }
        if(!error.equals(""))
            throw  new Exception(error);

    }

}
