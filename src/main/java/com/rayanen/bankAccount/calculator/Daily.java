package com.rayanen.bankAccount.calculator;


import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.TransactionDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.Transaction;
import com.rayanen.bankAccount.serviceController.TransactionService;
import org.springframework.web.bind.annotation.RequestParam;
import sun.util.resources.ca.CalendarData_ca;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Daily {


    TransactionService transactionService;
    BankAccountDao bankAccountDao;
    TransactionDao transactionDao;
    Transaction transaction;

    public Daily(TransactionService transactionService, BankAccountDao bankAccountDao, TransactionDao transactionDao, Transaction transaction) {
        this.transactionService = transactionService;
        this.bankAccountDao = bankAccountDao;
        this.transactionDao = transactionDao;
        this.transaction = transaction;
    }


    private void profit(@RequestParam Integer Month, @RequestParam Integer Days) throws Exception{
        Month=getLastMonth();
        Days=getDays();

        List<BankAccount> account = bankAccountDao.findByAccountNumber();
        for (BankAccount bankAccount : account) {
            bankAccount.setTransactions(transactionDao.findByTransactionDate(Month));
            List<Transaction> transactionList=bankAccount.getTransactions();

            for (Transaction transaction : transactionList) {
                

            }
        }

    }






    private Integer getLastMonth(){
        Calendar calendar=new GregorianCalendar();
        return calendar.get(Calendar.MONTH)+1;
    }

    private Integer getDays(){
        Calendar calendar= new GregorianCalendar();
        return calendar.get(Calendar.DAY_OF_MONTH)+1;
    }

}
