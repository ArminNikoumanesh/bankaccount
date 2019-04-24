package com.rayanen.bankAccount.serviceController;


import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.Transaction;
import org.springframework.stereotype.Component;




@Component
public class ValidationTransaction {


    BankAccountDao bankAccountDao;

    public ValidationTransaction(BankAccountDao bankAccountDao) {
        this.bankAccountDao = bankAccountDao;
    }

    public void increaseTransaction (Transaction transaction) throws Exception {
        String error="";
        boolean report = bankAccountDao.existsByAccountNumber(transaction.getPayerAccountNumber());
        if (!report) {
            BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transaction.getPayerAccountNumber());
            boolean active=bankAccountDao.existsByIsActive(account.getActive());
            if(!active){
                error+="حساب مسدود است";
            }
        }else {
            error += "حسابی یافت نشد برای برداشت";
        }


        if(!error.equals(""))
            throw  new Exception(error);
    }


    public void decreaseTransaction (Transaction transaction) throws Exception {
        String error="";
        boolean report = bankAccountDao.existsByAccountNumber(transaction.getPayeeAccountNumber());
        if (report) {
            BankAccount account = bankAccountDao.findBankAccountByAccountNumber(transaction.getPayeeAccountNumber());
            boolean active=bankAccountDao.existsByIsActive(account.getActive());
            if(!active){
                 error+="حساب مسدود است";
            }else if (account.getInventory().compareTo(transaction.getAmount())>0 ){

                error+="موجودی کافی نیست";
            }

        }else {
            error += "حسابی یافت نشد برای برداشت";
        }

        if(!error.equals(""))
            throw  new Exception(error);
    }










}
