package com.rayanen.bankAccount.serviceController.impl;

import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.RealPersonDao;
import com.rayanen.bankAccount.model.entity.RealPerson;
import com.rayanen.bankAccount.serviceController.RealPersonService;
import com.rayanen.bankAccount.validation.service.ValidationRealPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class RealPersonServiceImpl implements RealPersonService {

    private static Logger logger= LoggerFactory.getLogger(RealPersonServiceImpl.class);

    private BankAccountDao bankAccountDao;
    private RealPersonDao realPersonDao;
    ValidationRealPersonService validationRealPersonService;

    public RealPersonServiceImpl(BankAccountDao bankAccountDao, RealPersonDao realPersonDao, ValidationRealPersonService validationRealPersonService) {
        this.bankAccountDao = bankAccountDao;
        this.realPersonDao = realPersonDao;
        this.validationRealPersonService = validationRealPersonService;
    }



    public void saveReal(RealPerson realPerson) throws Exception {
        logger.info("startRealSaveService");
        validationRealPersonService.RealValidation(realPerson);
            realPersonDao.save(realPerson);
            logger.info("endRealSaveService");

    }


    public void updateReal(RealPerson realPerson) throws Exception {
        logger.info("startUpdateUpdateService");
           validationRealPersonService.realUpdateValidation(realPerson);
            realPersonDao.save(realPerson);
            logger.info("endUpdateUpdateService");
        }




    public RealPerson findByNationalCode(String nationalCode) throws Exception {
        logger.info("startFindByNationalCodeService");
        validationRealPersonService.findByNationalCode(nationalCode);
        RealPerson realPerson = realPersonDao.findByNationalCode(nationalCode);
        logger.info("endFindByNationalCodeService");

        return realPerson;
    }


    public List<RealPerson> findRealAll( RealPerson realPerson)throws Exception {
        logger.info("startFindingAllRealService");
        validationRealPersonService.realFindAll(realPerson);
        List<RealPerson> result = realPersonDao.findByNameAndFamilyName(realPerson.getName(), realPerson.getFamilyName());

        logger.info("endRealFindingAllService");
        return result;
    }







}
