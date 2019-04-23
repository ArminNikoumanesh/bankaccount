package com.rayanen.bankAccount.serviceController.impl;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.model.dao.RealPersonDao;
import com.rayanen.bankAccount.model.entity.RealPerson;
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

    public RealPersonServiceImpl(RealPersonDao realPersonDao) {
        this.realPersonDao = realPersonDao;
    }





    public ResponseDto<String> saveReal( @RequestBody RealPerson realPerson) {
        logger.info("startRealSaveService");
        Boolean report = realPersonDao.existsByNationalCode(realPerson.getNationalCode());

        if(!report) {
            if ( Objects.isNull(realPerson.getName()) || realPerson.getName().length() < 2) {
                logger.error("Real name save error");
                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("نام وارد شده صحیح نمی باشد"));
            }
            if ( Objects.isNull(realPerson.getFamilyName()) || realPerson.getFamilyName().length() < 2){
                logger.error("Real FamilyName save error");
                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("نام خانوادگی وارد شده صحیح نمی باشد"));
            }
            if ( Objects.isNull(realPerson.getNationalCode()) || realPerson.getNationalCode().length()< 10) {
                logger.error("Real NationalCode save error");
                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("کد ملی وارد شده صحیح نمی باشد"));
            }


            realPersonDao.save(realPerson);
            logger.info("endRealSaveService");
            return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
        }
        return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("کد ملی وارد شده موجود می باشد"));
    }


    public ResponseDto<String> updateReal(@RequestBody RealPerson realPerson) {
        logger.info("startUpdateUpdateService");
        Boolean report = realPersonDao.existsByNationalCode(realPerson.getNationalCode());

        if(report) {

            if ( Objects.isNull(realPerson.getName()) || realPerson.getName().length() < 2) {
                logger.error("Real name update error");
                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("نام وارد شده صحیح نمی باشد"));
            }
            if ( Objects.isNull(realPerson.getFamilyName()) || realPerson.getFamilyName().length() < 2){
                logger.error("Real FamilyName update error");
                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("نام خانوادگی وارد شده صحیح نمی باشد"));
            }
            if ( Objects.isNull(realPerson.getNationalCode()) || realPerson.getNationalCode().length() == 10) {
                logger.error("Real NationalCode update error");
                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("کد ملی وارد شده صحیح نمی باشد"));
            }
            logger.info("endUpdateUpdateService");
            return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
        }
        return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("کد ملی را نمی توانید تغییر دهید"));
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
