package com.rayanen.bankAccount.calculator;


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
        BigDecimal Amount = null;
        List<BankAccount> account = bankAccountDao.findByAccountNumber();

        for (BankAccount bankAccount : account) {
            bankAccount.setTransactions(transactionDao.findByTransactionDate(Month));
            List<Transaction> transactionList=bankAccount.getTransactions();
            bankAccount.setMin(bankAccount.getInventory());

            for (Transaction transaction : transactionList) {
                List<Transaction> transactionForEachDay=transactionDao.findByTransactionDateDays(Days);

                for (Transaction transactions: transactionForEachDay) {
                    if(transactions.inventoryBeforeTransaction.compareTo(transactions.getMin())<0){
                        bankAccount.setMin(transactions.inventoryBeforeTransaction);
                    }
                }
                transaction.setMin(bankAccount.getMin().multiply(BigDecimal.valueOf(20)).divide(BigDecimal.valueOf(36500)));
                Amount= Amount.add(transaction.getMin());

            }
            transaction.setAmount(Amount);
            transaction.setIncreaserAccountNumber(bankAccount.getAccountNumber());
            transactionService.decreaseTransaction(transaction);
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
