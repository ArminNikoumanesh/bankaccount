package com.rayanen.bankAccount.calculations;


import com.rayanen.bankAccount.model.dao.BankAccountDao;
import com.rayanen.bankAccount.model.dao.TransactionDao;
import com.rayanen.bankAccount.model.entity.BankAccount;
import com.rayanen.bankAccount.model.entity.Transaction;
import com.rayanen.bankAccount.serviceController.TransactionService;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;


public class Monthly {




    BankAccountDao bankAccountDao;
    TransactionDao transactionDao;
    public Monthly(BankAccountDao bankAccountDao, TransactionDao transactionDao) {
        this.bankAccountDao = bankAccountDao;
        this.transactionDao = transactionDao;
    }






    private BigDecimal profit(@RequestParam Integer Month){
        Month=getLastMonth();

       List<BankAccount> account = bankAccountDao.findBankAccountByAccountNumber();
        for (BankAccount bankAccount : account) {
         bankAccount.setTransactions(transactionDao.findByTransactionDate(Month));
        List<Transaction> transactions=bankAccount.getTransactions();
        bankAccount.setMin(bankAccount.getInventory());

            for (Transaction transaction : transactions) {
                if(transaction.inventoryBeforeTransaction.compareTo(transaction.getMin())<0){
                   bankAccount.setMin(transaction.inventoryBeforeTransaction);
                }
            }

            TransactionService transactionService;
            

        }

    return null;
}





    private Integer getLastMonth(){
        Calendar calendar=new GregorianCalendar();
        return calendar.get(Calendar.MONTH)+1;
    }


}
