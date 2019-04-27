package com.rayanen.bankAccount.serviceController;


import com.rayanen.bankAccount.model.entity.Transaction;

public interface TransactionService {

     void decreaseTransaction( Transaction transaction) throws Exception;

     void increaseTransaction( Transaction transaction) throws Exception;

     void transferTransaction( Transaction transaction) throws Exception;
}
