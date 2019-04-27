package com.rayanen.bankAccount.calculator;


import com.rayanen.bankAccount.dto.TransactionDto;
import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.TransactionDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.Transaction;
import com.rayanen.bankAccount.serviceController.TransactionService;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class Monthly {


    TransactionService transactionService;
    BankAccountDao bankAccountDao;
    TransactionDao transactionDao;
    Transaction transaction;

    public Monthly(TransactionService transactionService, BankAccountDao bankAccountDao, TransactionDao transactionDao, Transaction transaction) {
        this.transactionService = transactionService;
        this.bankAccountDao = bankAccountDao;
        this.transactionDao = transactionDao;
        this.transaction = transaction;
    }


    private void profit(@RequestParam Integer Month) throws Exception {
        Month=getLastMonth();
         BigDecimal Amount;
       List<BankAccount> account = bankAccountDao.findByAccountNumber();
        for (BankAccount bankAccount : account) {
         bankAccount.setTransactions(transactionDao.findByTransactionDate(Month));
        List<Transaction> transactionList=bankAccount.getTransactions();
        bankAccount.setMin(bankAccount.getInventory());

            for (Transaction transaction : transactionList) {
                if(transaction.inventoryBeforeTransaction.compareTo(transaction.getMin())<0){
                   bankAccount.setMin(transaction.inventoryBeforeTransaction);
                }
            }

            Amount =bankAccount.getMin().multiply(BigDecimal.valueOf(20));
            Amount = Amount.divide(BigDecimal.valueOf(36500));


            transaction.setAmount(Amount);
            transaction.setIncreaserAccountNumber(bankAccount.getAccountNumber());
            transactionService.decreaseTransaction(transaction);

        }


}





    private Integer getLastMonth(){
        Calendar calendar=new GregorianCalendar();
        return calendar.get(Calendar.MONTH)+1;
    }


}
