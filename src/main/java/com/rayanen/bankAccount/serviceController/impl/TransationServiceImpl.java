package com.rayanen.bankAccount.serviceController.impl;

import com.rayanen.bankAccount.dto.ResponseDto;
import com.rayanen.bankAccount.dto.ResponseException;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.Transaction;
import com.rayanen.bankAccount.restController.TransactionRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestBody;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TransationServiceImpl {

    private static Logger logger= LoggerFactory.getLogger(TransactionRestController.class);
    private BankAccountDao bankAccountDao;

    public TransationServiceImpl(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }



    //amount meghdar moteghayer hesab dar amountDto ast

    //variz

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


    private void sodem(){




    }

}
