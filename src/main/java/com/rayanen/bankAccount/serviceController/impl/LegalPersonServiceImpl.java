package com.rayanen.bankAccount.serviceController.impl;


import com.rayanen.bankAccount.dto.ResponseDto;
import com.rayanen.bankAccount.dto.ResponseException;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.model.dao.LegalPersonDao;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import com.rayanen.bankAccount.restController.LegalPersonRestController;
import com.rayanen.bankAccount.serviceController.ValidationLegalPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Objects;

@Component
public class LegalPersonServiceImpl {

    private static Logger logger= LoggerFactory.getLogger(LegalPersonRestController.class);


    private ValidationLegalPerson validationLegalPerson;
    private LegalPersonDao legalPersonDao;

    public LegalPersonServiceImpl(ValidationLegalPerson validationLegalPerson, LegalPersonDao legalPersonDao) {
        this.validationLegalPerson = validationLegalPerson;
        this.legalPersonDao = legalPersonDao;
    }



    public void saveLegal(LegalPerson legalPerson) throws Exception {
        logger.info("startSaveLegalService");
        validationLegalPerson.saveLegalValidation(legalPerson);
        legalPersonDao.save(legalPerson);
        logger.info("endSaveLegalService");
    }


    public void updateLegal( LegalPerson legalPerson) throws Exception {
        logger.info("startUpdateLegalService");
       validationLegalPerson.updateLegalValidation(legalPerson);
        logger.info("endUpdateLegalService");

    }


    public LegalPerson findByCompanyCode( String companyCode) throws Exception {
        logger.info("startFindingLegalService");
        validationLegalPerson.legalFind(companyCode);
        LegalPerson legalPerson=legalPersonDao.findByCompanyCode(companyCode);
        logger.info("endFindingLegalService");
        return legalPerson;
    }


    public List<LegalPerson> findLegalAll( LegalPerson legalPerson) throws Exception {
        logger.info("startFindingAllLegalService");

       validationLegalPerson.legalFindAll(legalPerson);
        List<LegalPerson> result = legalPersonDao.findByNameAndCompanyCode(legalPerson.getName(), legalPerson.getCompanyCode());

        logger.info("endFindingAllLegalService");
        return result;
    }


    public ResponseDto<String> deleteLegalAccount(@RequestBody LegalPerson legalPerson) {
        logger.info("startLegalUpdateRestController");
//       legalPerson.getBankAccounts().get();

        logger.info("endLegalUpdateRestController");
        return new ResponseDto<>(ResponseStatus.Ok, null, "حساب مسدود شد", null);    }




}
