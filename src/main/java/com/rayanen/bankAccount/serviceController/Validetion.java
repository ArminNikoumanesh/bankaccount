package com.rayanen.bankAccount.serviceController;

import com.rayanen.bankAccount.dto.ResponseDto;
import com.rayanen.bankAccount.dto.ResponseException;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.model.dao.LegalPersonDao;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

public class Validetion {

//    private LegalPersonDao legalPersonDao;
////public String Massage;
//
//    public Validetion(LegalPersonDao legalPersonDao) {
//        this.legalPersonDao = legalPersonDao;
//
//    }
//    public String  saveLegal(@RequestBody LegalPerson legalPerson) {
////        logger.info("startSaveLegalService");
//        Boolean report = legalPersonDao.existsByCompanyCode(legalPerson.getCompanyCode());
//
//        if(!report) {
//            if (Objects.isNull(legalPerson.getName()) || legalPerson.getName().length() < 2) {
////                logger.error("name save legal error");
//                return "نام وارد شده صحیح نمی باشد";
//            }
//            if (Objects.isNull(legalPerson.getManeger()) || legalPerson.getManeger().length() < 2) {
////                logger.error("maneger save legal error");
//                return "نام وارد شده صحیح نمی باشد";
//                //  return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("نام مدیر صحیح نمی باشد"));
//            }
//            if (Objects.isNull(legalPerson.getCompanyCode()) || legalPerson.getCompanyCode().length() == 10) {
////                logger.error("CompanyCode save legal error");
//                // return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("کدثبتی شرکت صحیح نمی باشد"));
//                return "نام وارد شده صحیح نمی باشد";
//            }
//
//////            logger.info("endSaveLegalService");
////        }else{
////            return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("کدثبتی شرکت موجود می باشد"));
////        }
//        }
//
//      return  "کدثبتی شرکت موجود می باشد";
//    }
}
