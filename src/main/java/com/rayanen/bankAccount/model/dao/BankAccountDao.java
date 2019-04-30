package com.rayanen.bankAccount.model.dao;

import com.rayanen.bankAccount.model.entity.BankAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BankAccountDao extends JpaRepository<BankAccount, Integer> {

    BankAccount findBankAccountByAccountNumber(String accountNumber);
    boolean existsByAccountNumber(String accountNumber);
    boolean existsByIsActive(Boolean isActive);


@Query("SELECT B FROM BankAccount B ")
  List <BankAccount> findByAccountNumber();



}
