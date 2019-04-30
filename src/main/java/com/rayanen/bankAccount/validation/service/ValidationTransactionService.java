package com.rayanen.bankAccount.validation.service;


import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.Transaction;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;




@Component
public class ValidationTransactionService {

  private Environment environment;
  private   BankAccountDao bankAccountDao;

    public ValidationTransactionService(Environment environment, BankAccountDao bankAccountDao) {
        this.environment = environment;
        this.bankAccountDao = bankAccountDao;
    }

    public void increaseTransaction (Transaction transaction) throws Exception {
        String error="";
        boolean report = bankAccountDao.existsByAccountNumber(transaction.getIncreaserAccountNumber());
        if (report) {
            BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transaction.getIncreaserAccountNumber());
            boolean active=bankAccountDao.existsByIsActive(account.getActive());
            if(!active){
                error+=environment.getProperty("exc.message.validation.increaseTransaction.less");
            }else if (account.getInventory().compareTo(transaction.getAmount())<0 ){

                error+=environment.getProperty("exc.message.validation.increaseTransaction.notMony");
            }
        }else {
            error += environment.getProperty("exc.message.validation.increaseTransaction.notFine");
        }


        if(!error.equals(""))
            throw  new Exception(error);
    }


    public void decreaseTransaction (Transaction transaction) throws Exception {
        String error="";
        boolean report = bankAccountDao.existsByAccountNumber(transaction.getDecreaserAccountNumber());
        if (report) {
            BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transaction.getDecreaserAccountNumber());
            boolean active=bankAccountDao.existsByIsActive(account.getActive());
            if(!active){

                error+=environment.getProperty("exc.message.validation.increaseTransaction.less");
            }

        }else {
            error += environment.getProperty("exc.message.validation.increaseTransaction.notFined");
        }

        if(!error.equals(""))
            throw  new Exception(error);
    }










}
