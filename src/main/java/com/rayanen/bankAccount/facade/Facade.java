package com.rayanen.bankAccount.facade;

import com.rayanen.bankAccount.dto.*;
import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.LegalPersonDao;
import com.rayanen.bankAccount.model.dao.RealPersonDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.LegalPerson;
import com.rayanen.bankAccount.model.entity.RealPerson;
import com.rayanen.bankAccount.model.entity.Transaction;
import com.rayanen.bankAccount.restController.LegalPersonRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


public class Facade {
    private static Logger logger= LoggerFactory.getLogger(LegalPersonRestController.class);
    private LegalPersonDao legalPersonDao;
    private RealPersonDao realPersonDao;
    private BankAccountDao bankAccountDao;





    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> saveLegal(@Valid @RequestBody LegalPersonDto legalPersonDto) {
        logger.info("start save legal");

        return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> updateLegal(@RequestBody LegalPersonDto legalPersonDto) {
        logger.info("start update legal");

        return new ResponseDto<>(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);    }


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



    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> updateReal(@RequestBody RealPersonDto realPersonDto) {
        logger.info("start update save");

        logger.info("end update save");
        return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseDto<String> saveReal(@RequestBody RealPersonDto realPersonDto) {
        logger.info("start real save");


        logger.info("end real save");
        return new ResponseDto(ResponseStatus.Ok, null, "اطلاعات ذخیره شد.", null);
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


    public ResponseDto<List<RealPersonDto>> findLegalAll(@RequestBody RealPersonDto realPersonDto) {
        List<RealPersonDto> result = realPersonDao.findByNameAndFamilyName(realPersonDto.getName(), realPersonDto.getFamilyName());
        return new ResponseDto(ResponseStatus.Ok, result, null, null);
    }



    public ResponseDto<Object> decreaseTransaction(@RequestBody Transaction transaction) {
        Boolean report = bankAccountDao.existsByAccountNumber(transaction.getPayeeAccountNumber());
        logger.info("start depositTransaction");
        if (!report ) {
            return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("حسابی یافت نشد"));
        }
        BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transaction.getPayeeAccountNumber());
        account.setInventory(account.getInventory().add(transaction.getAmount()));

        logger.info("end depositTransaction");
        return new ResponseDto<>(ResponseStatus.Ok, null, "واریز وجه با موفقیت انجام شد.", null);
    }

    //bardasht

    public ResponseDto<Object> increaseTransaction(@RequestBody Transaction transaction) {
        Boolean report = bankAccountDao.existsByAccountNumber(transaction.getPayerAccountNumber());
        logger.info("start pickedUpTransaction");
        if (report) {
            BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transaction.getPayerAccountNumber());
            if (account.getInventory().compareTo(transaction.getAmount())>0 ){
                return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("موجودی کم است"));
            }
            account.setInventory(account.getInventory().subtract(transaction.getAmount()) );
            logger.info("end  pickedUpTransaction");
            return new ResponseDto<>(ResponseStatus.Ok, null, "برداشت با موفقیت انجام شد.", null);
        }else{
            logger.error(" error finding account ");
            return new ResponseDto<>(ResponseStatus.Error, null, null, new ResponseException("حساب موجود نیست"));
        }
    }




    //enteghal vajh

    public ResponseDto<Object> transferTransaction(@RequestBody Transaction transaction) {
        decreaseTransaction(transaction);
        increaseTransaction(transaction);
        return new ResponseDto<>(ResponseStatus.Ok, null, "انتقال وجه با موفقیت انجام شد.", null);


    }



}
