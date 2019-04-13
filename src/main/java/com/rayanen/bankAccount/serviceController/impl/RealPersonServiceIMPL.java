package com.rayanen.bankAccount.serviceController.impl;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.model.dao.RealPersonDao;
import com.rayanen.bankAccount.model.entity.ActiveType;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.RealPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Objects;

public class RealPersonServiceIMPL {

    private static Logger logger= LoggerFactory.getLogger(RealPersonServiceIMPL.class);


    private RealPersonDao realPersonDao;

    public RealPersonServiceIMPL(RealPersonDao realPersonDao) {
        this.realPersonDao = realPersonDao;
    }





    public ResponseDto<String> saveReal( @RequestBody RealPerson realPerson) {
        logger.info("start real save");
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
            if ( Objects.isNull(realPerson.getNationalCode()) || realPerson.getNationalCode().length() == 10 ) {
                logger.error("Real NationalCode save error");
                return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("کد ملی وارد شده صحیح نمی باشد"));
            }
            for (BankAccount bankAccount: realPerson.getBankAccounts())
            {
                bankAccount.setActiveType(ActiveType.ACTIVE);
            }
            logger.info("end real save");
            return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
        }
        return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("کد ملی وارد شده موجود می باشد"));
    }


    public ResponseDto<String> updateReal(@RequestBody RealPerson realPerson) {
        logger.info("start update save");
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
            logger.info("end update save");
            return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
        }
        return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("کد ملی را نمی توانید تغییر دهید"));
    }



    public ResponseDto<RealPerson> findReal(@RequestParam String nationalCode) {
       RealPersonDto realPersonDto = realPersonDao.findByNationalCode(nationalCode);
        logger.info("start finding Real");
        if (Objects.isNull(realPersonDto)) {
            logger.error("real wasn't found");
            return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("not find"));
        }
        logger.info("real was found");
        return new ResponseDto(ResponseStatus.Ok, realPersonDto, null, null);
    }



    @RequestMapping(value = "ws/findRealAll", method = RequestMethod.POST)
    public ResponseDto<List<RealPersonDto>> findLegalAll(@RequestBody RealPersonDto realPersonDto) {
        List<RealPersonDto> result = realPersonDao.findByNameAndFamilyName(realPersonDto.getName(), realPersonDto.getFamilyName());
        return new ResponseDto(ResponseStatus.Ok, result, null, null);
    }
}
