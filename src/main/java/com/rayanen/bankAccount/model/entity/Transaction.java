package com.rayanen.bankAccount.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ARN_TRANSACTION")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
    //ferstande
    private String increaserAccountNumber;
    //girande
    private String decreaserAccountNumber;


    @CreatedDate
    private Date transactionDate;

    private BigDecimal amount;

    private TransactionType transactionType;

    public BigDecimal  inventoryAfterTransaction;

    @Version
    private Long version;


    public BigDecimal getInventoryAfterTransaction() {
        return inventoryAfterTransaction;
    }

    public void setInventoryAfterTransaction(BigDecimal inventoryAfterTransaction) {
        this.inventoryAfterTransaction = inventoryAfterTransaction;
    }

    public String getIncreaserAccountNumber() {
        return increaserAccountNumber;
    }

    public void setIncreaserAccountNumber(String increaserAccountNumber) {
        this.increaserAccountNumber = increaserAccountNumber;
    }

    public String getDecreaserAccountNumber() {
        return decreaserAccountNumber;
    }

    public void setDecreaserAccountNumber(String decreaserAccountNumber) {
        this.decreaserAccountNumber = decreaserAccountNumber;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
