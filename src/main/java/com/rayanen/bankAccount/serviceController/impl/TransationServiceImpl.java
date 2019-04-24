package com.rayanen.bankAccount.serviceController.impl;

import com.rayanen.bankAccount.dto.ResponseDto;
import com.rayanen.bankAccount.dto.ResponseException;
import com.rayanen.bankAccount.dto.ResponseStatus;
import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.Transaction;
import com.rayanen.bankAccount.restController.TransactionRestController;
import com.rayanen.bankAccount.serviceController.ValidationTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;



@Component
public class TransationServiceImpl {

    private static Logger logger= LoggerFactory.getLogger(TransactionRestController.class);

    private BankAccountDao bankAccountDao;
    private ValidationTransaction validationTransaction;

    public TransationServiceImpl(BankAccountDao bankAccountDao, ValidationTransaction validationTransaction) {
        this.bankAccountDao = bankAccountDao;
        this.validationTransaction = validationTransaction;
    }
    //variz

    public void decreaseTransaction( Transaction transaction) throws Exception {

        logger.info("startDepositTransactionService");
        validationTransaction.decreaseTransaction(transaction);
        BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transaction.getPayeeAccountNumber());
        account.setInventory(account.getInventory().add(transaction.getAmount()));
        logger.info("endDepositTransactionService");
    }

    //bardasht

    public void increaseTransaction( Transaction transaction) throws Exception {
        logger.info("startIncreaseTransactionService");
        validationTransaction.increaseTransaction(transaction);
            BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transaction.getPayerAccountNumber());
            account.setInventory(account.getInventory().subtract(transaction.getAmount()) );
            logger.info("endIncreaseTransactionServiceService");


    }




    //enteghal vajh

    public ResponseDto<Object> transferTransaction( Transaction transaction) throws Exception {
        logger.info("startTransferTransactionService");
        decreaseTransaction(transaction);
        increaseTransaction(transaction);
        logger.info("endTransferTransactionService");
        return new ResponseDto<>(ResponseStatus.Ok, null, "انتقال وجه با موفقیت انجام شد.", null);


    }


    private void sodem(){




    }

}
