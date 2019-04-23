package com.rayanen.bankAccount.serviceController.impl;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.model.dao.RealPersonDao;
import com.rayanen.bankAccount.model.entity.RealPerson;
import com.rayanen.bankAccount.serviceController.Validetion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Objects;



@Component
public class RealPersonServiceImpl {

    private static Logger logger= LoggerFactory.getLogger(RealPersonServiceImpl.class);


    private RealPersonDao realPersonDao;
    Validetion validetion;
    public RealPersonServiceImpl(RealPersonDao realPersonDao,Validetion validetion) {
        this.realPersonDao = realPersonDao;
        this.validetion = validetion;
    }





    public void saveReal(RealPerson realPerson) throws Exception {
        logger.info("startRealSaveService");

     validetion.RealValideton(realPerson);

            realPersonDao.save(realPerson);

            logger.info("endRealSaveService");

    }


    public void updateReal(RealPerson realPerson) throws Exception {
        logger.info("startUpdateUpdateService");

           validetion.realUpdateValidation(realPerson);


            logger.info("endUpdateUpdateService");
        }




    public ResponseDto<RealPerson> findReal(@RequestBody RealPerson realPerson) {
       RealPerson realPersonDaoByNationalCode = realPersonDao.findByNationalCode(realPerson.getNationalCode());
        logger.info("startFindingRealService");
        if (Objects.isNull(realPersonDaoByNationalCode)) {
            logger.error("real wasn't found");
            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("not find"));
        }
        logger.info("endRealFindingService");
        return new ResponseDto(ResponseStatus.Ok, realPersonDaoByNationalCode, null, null);
    }


    public ResponseDto<List<RealPerson>> findRealAll(@RequestBody RealPerson realPerson) {
        logger.info("startFindingAllRealService");
        List<RealPerson> result = realPersonDao.findByNameAndFamilyName(realPerson.getName(), realPerson.getFamilyName());

        logger.info("endRealFindingAllService");
        return new ResponseDto(ResponseStatus.Ok, result, null, null);
    }


    public ResponseDto<String> deleteRealAccount(@RequestBody RealPerson realPerson) {
        logger.info("startLegalUpdateRestController");
//        realPerson.getBankAccounts().get();
        logger.info("endLegalUpdateRestController");
        return new ResponseDto<>(ResponseStatus.Ok, null, "حساب مسدود شد", null);    }



}
