package com.rayanen.bankAccount.dto;



import java.math.BigDecimal;
import java.util.Date;


public class TransactionDto {

    private Long id;
    //ferstande
    private String payerAccountNumber;
    //girande
    private String payeeAccountNumber;

    private Date transactionDate;

    private BigDecimal amount;

    private TransactionTypesDto transactionType;

    public BigDecimal  inventoryAfterTransaction;

    private Long version;




    public BigDecimal getInventoryAfterTransaction() {
        return inventoryAfterTransaction;
    }

    public void setInventoryAfterTransaction(BigDecimal inventoryAfterTransaction) {
        this.inventoryAfterTransaction = inventoryAfterTransaction;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public TransactionTypesDto getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypesDto transactionType) {
        this.transactionType = transactionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayerAccountNumber() {
        return payerAccountNumber;
    }

    public void setPayerAccountNumber(String payerAccountNumber) {
        this.payerAccountNumber = payerAccountNumber;
    }

    public String getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    public void setPayeeAccountNumber(String payeeAccountNumber) {
        this.payeeAccountNumber = payeeAccountNumber;
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
