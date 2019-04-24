package com.rayanen.bankAccount.model.dao;


import com.rayanen.bankAccount.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TransactionDao extends JpaRepository<Transaction, Integer> {



}
