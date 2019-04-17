package com.rayanen.bankAccount.serviceController.impl;


import com.rayanen.bankAccount.dto.LegalPersonDto;
import com.rayanen.bankAccount.dto.ResponseDto;
import com.rayanen.bankAccount.dto.ResponseException;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.LegalPersonDao;
import com.rayanen.bankAccount.model.entity.ActiveType;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.Customer;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import com.rayanen.bankAccount.restController.LegalPersonRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


@Component
public class LegalPersonServiceImpl {

    private static Logger logger= LoggerFactory.getLogger(LegalPersonRestController.class);
    private LegalPersonDao legalPersonDao;


    public LegalPersonServiceImpl(LegalPersonDao legalPersonDao) {
        this.legalPersonDao = legalPersonDao;

    }
    public ResponseDto<String> saveLegal(@Valid @RequestBody LegalPerson legalPerson) {
        logger.info("start save legal");
        Boolean report = legalPersonDao.existsByCompanyCode(legalPerson.getCompanyCode());

        if(!report) {
            if ( Objects.isNull(legalPerson.getName()) || legalPerson.getName().length() < 2) {
                logger.error("name save legal error");
                return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("نام وارد شده صحیح نمی باشد"));
            }
            if ( Objects.isNull(legalPerson.getManeger()) || legalPerson.getManeger().length() < 2) {
                logger.error("maneger save legal error");
                return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("نام مدیر صحیح نمی باشد"));
            }
            if ( Objects.isNull(legalPerson.getCompanyCode()) || legalPerson.getCompanyCode().length() == 10 ) {
                logger.error("CompanyCode save legal error");
                return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("کدثبتی شرکت صحیح نمی باشد"));
            }

                            for (BankAccount bankAccount:legalPerson.getBankAccounts() )
                  {
                     bankAccount.setActiveType(ActiveType.ACTIVE);
            }

            logger.info("end save legal");
        }else{
            return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("کدثبتی شرکت موجود می باشد"));
        }

        return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
    }


    public ResponseDto<String> updateLegal(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("start update legal");
        Boolean report = legalPersonDao.existsByCompanyCode(legalPersonDto.getCompanyCode());
        if(report){
            if ( Objects.isNull(legalPersonDto.getName()) || legalPersonDto.getName().length() < 2) {
                logger.error("name error");
                return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("نام وارد شده صحیح نمی باشد"));
            }
            if (Objects.isNull(legalPersonDto.getManeger()) || legalPersonDto.getManeger().length() < 2) {
                logger.error("Maneger error");
                return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("نام مدیر صحیح نمی باشد"));
            }
            if ( Objects.isNull(legalPersonDto.getCompanyCode()) || legalPersonDto.getCompanyCode().length() == 10 )  {
                logger.error("CompanyCode error");
                return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("کدثبتی شرکت صحیح نمی باشد"));
            }
            logger.info("end update legal");
            return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
        }
        return new ResponseDto(ResponseStatus.Error, null, null, new ResponseException("کد ثبتی شرکت مورد نظر را نمی توانید تغییر دهید."));
    }


    public ResponseDto<LegalPerson> findLegal(@RequestParam String code) {
        LegalPersonDto legalPersonDto = legalPersonDao.findByCompanyCode(code);
        logger.info("start finding legal");
        if (Objects.isNull(legalPersonDto)) {
            logger.error("not found legal");
            return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("شخصی با این مشخصات یافت نشد"));
        }
        logger.info("finding legal");
        return new ResponseDto(ResponseStatus.Ok, legalPersonDto, null, null);
    }


    public ResponseDto<List<LegalPersonDto>> findLegalAll(@RequestBody LegalPersonDto legalPersonDto) {
        List<LegalPersonDto> result = legalPersonDao.findByNameAndCompanyCode(legalPersonDto.getName(), legalPersonDto.getCompanyCode());
        return new ResponseDto(ResponseStatus.Ok, result, null, null);
    }

}
