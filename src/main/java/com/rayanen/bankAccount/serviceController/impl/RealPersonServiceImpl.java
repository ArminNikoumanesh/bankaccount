package com.rayanen.bankAccount.serviceController.impl;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.RealPersonDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import com.rayanen.bankAccount.model.entity.RealPerson;
import com.rayanen.bankAccount.serviceController.ValidationRealPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class RealPersonServiceImpl {

    private static Logger logger= LoggerFactory.getLogger(RealPersonServiceImpl.class);

    private BankAccountDao bankAccountDao;
    private RealPersonDao realPersonDao;
    ValidationRealPerson validationRealPerson;

    public RealPersonServiceImpl(BankAccountDao bankAccountDao, RealPersonDao realPersonDao, ValidationRealPerson validationRealPerson) {
        this.bankAccountDao = bankAccountDao;
        this.realPersonDao = realPersonDao;
        this.validationRealPerson = validationRealPerson;
    }

    public void saveReal(RealPerson realPerson) throws Exception {
        logger.info("startRealSaveService");
        validationRealPerson.RealValidation(realPerson);
            realPersonDao.save(realPerson);
            logger.info("endRealSaveService");

    }


    public void updateReal(RealPerson realPerson) throws Exception {
        logger.info("startUpdateUpdateService");
           validationRealPerson.realUpdateValidation(realPerson);

            logger.info("endUpdateUpdateService");
        }




    public RealPerson findReal( RealPerson realPerson) throws Exception {
        logger.info("startFindingRealService");

         validationRealPerson.realFind(realPerson);

        logger.info("endRealFindingService");
        return realPerson;
    }


    public List<RealPerson> findRealAll( RealPerson realPerson)throws Exception {
        logger.info("startFindingAllRealService");
        validationRealPerson.realFindAll(realPerson);
        List<RealPerson> result = realPersonDao.findByNameAndFamilyName(realPerson.getName(), realPerson.getFamilyName());

        logger.info("endRealFindingAllService");
        return result;
    }


    public void deleteRealAccount( BankAccount bankAccount) throws Exception {
        logger.info("startLegalUpdateRestController");
        validationRealPerson.deleteRealAccount(bankAccount);
        bankAccount.setActive(false);
        bankAccountDao.save(bankAccount);
        logger.info("endLegalUpdateRestController");


    }

}
