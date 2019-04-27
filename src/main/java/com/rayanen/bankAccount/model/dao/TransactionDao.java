package com.rayanen.bankAccount.model.dao;



import com.rayanen.bankAccount.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TransactionDao extends JpaRepository<Transaction, Integer> {

    @Query("SELECT T FROM Transaction T WHERE DATEPART(MONTH,transactionDate)=Month")
    List<Transaction> findByTransactionDate(@Param("Month") Integer Month);

    @Query("SELECT T FROM Transaction T WHERE DATEPART(Days,transactionDate)=Days")
    List<Transaction> findByTransactionDateDays(@Param("Days") Integer Days);

//    @Query("SELECT T FROM Transaction T WHERE (SELECT T FROM Transaction T WHERE DATEPART(MONTH,transactionDate)=Month ),DATEPART(Day,transactionDate)=Days")
//  List<Transaction> findByTransactionDateBetween(@Param("Month") Integer Month,@Param("Days") Integer Days);

//    @Query("SELECT T FROM Transaction T WHERE DATEPART(Day,transactionDate)=Day ")
//    List<Transaction> findByTransactionDateAnd(@Param("Day") Integer Month);



}
