package com.rayanen.bankAccount.serviceController.impl;


import com.rayanen.bankAccount.model.dao.LegalPersonDao;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import com.rayanen.bankAccount.restController.LegalPersonRestController;
import com.rayanen.bankAccount.serviceController.LegalPersonService;
import com.rayanen.bankAccount.validation.service.ValidationLegalPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LegalPersonServiceImpl implements LegalPersonService {

    private static Logger logger= LoggerFactory.getLogger(LegalPersonRestController.class);


    private ValidationLegalPersonService validationLegalPersonService;
    private LegalPersonDao legalPersonDao;

    public LegalPersonServiceImpl(ValidationLegalPersonService validationLegalPersonService, LegalPersonDao legalPersonDao) {
        this.validationLegalPersonService = validationLegalPersonService;
        this.legalPersonDao = legalPersonDao;
    }



    public void saveLegal(LegalPerson legalPerson) throws Exception {
        logger.info("startSaveLegalService");
        validationLegalPersonService.saveLegalValidation(legalPerson);
        legalPersonDao.save(legalPerson);
        logger.info("endSaveLegalService");
    }


    public void updateLegal( LegalPerson legalPerson) throws Exception {
        logger.info("startUpdateLegalService");
        validationLegalPersonService.updateLegalValidation(legalPerson);
        legalPersonDao.save(legalPerson);
        logger.info("endUpdateLegalService");

    }


    public LegalPerson findByCompanyCode( String companyCode) throws Exception {
        logger.info("startFindingLegalService");
        validationLegalPersonService.legalFind(companyCode);
        LegalPerson legalPerson=legalPersonDao.findByCompanyCode(companyCode);
        logger.info("endFindingLegalService");
        return legalPerson;
    }


    public List<LegalPerson> findLegalAll( LegalPerson legalPerson) throws Exception {
        logger.info("startFindingAllLegalService");

       validationLegalPersonService.legalFindAll(legalPerson);
        List<LegalPerson> result = legalPersonDao.findByNameAndCompanyCode(legalPerson.getName(), legalPerson.getCompanyCode());

        logger.info("endFindingAllLegalService");
        return result;
    }





}
