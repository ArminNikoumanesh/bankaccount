package com.rayanen.bankAccount.serviceController.impl;


import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.TransactionDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.Transaction;
import com.rayanen.bankAccount.restController.TransactionRestController;
import com.rayanen.bankAccount.serviceController.TransactionService;
import com.rayanen.bankAccount.validation.service.ValidationTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;


@Service
public class TransactionServiceImpl implements TransactionService{

    private static Logger logger= LoggerFactory.getLogger(TransactionRestController.class);

    private BankAccountDao bankAccountDao;
    private ValidationTransactionService validationTransactionService;
    private TransactionDao transactionDao;

    public TransactionServiceImpl(BankAccountDao bankAccountDao, ValidationTransactionService validationTransactionService, TransactionDao transactionDao) {
        this.bankAccountDao = bankAccountDao;
        this.validationTransactionService = validationTransactionService;
        this.transactionDao = transactionDao;
    }
    //variz

    public void decreaseTransaction( Transaction transaction) throws Exception {

        logger.info("startDepositTransactionService");
        validationTransactionService.decreaseTransaction(transaction);
        BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transaction.getDecreaserAccountNumber());
        transaction.setInventoryBeforeTransaction(account.inventory);
        account.setInventory(account.getInventory().add(transaction.getAmount()));
        transactionDao.save(transaction);
        logger.info("endDepositTransactionService");
    }

    //bardasht

    public void increaseTransaction( Transaction transaction) throws Exception {
        logger.info("startIncreaseTransactionService");
        validationTransactionService.increaseTransaction(transaction);
            BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transaction.getIncreaserAccountNumber());
            transaction.setInventoryBeforeTransaction(account.inventory);
            account.setInventory(account.getInventory().subtract(transaction.getAmount()));
//            if (account.getMin().compareTo(account.inventory)>0){
//                account.setMin(account.getInventory());
//
//            }
            transactionDao.save(transaction);
            logger.info("endIncreaseTransactionServiceService");


    }


    //enteghal vajh

    public void transferTransaction( Transaction transaction) throws Exception {
        logger.info("startTransferTransactionService");
        increaseTransaction(transaction);
        decreaseTransaction(transaction);
        logger.info("endTransferTransactionService");

    }



}
