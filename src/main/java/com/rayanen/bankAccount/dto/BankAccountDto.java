package com.rayanen.bankAccount.dto;


import java.math.BigDecimal;
import java.util.List;

public class BankAccountDto {

    private Integer id;
    private String accountNumber;
    private AccountTypeDto accountTypes;
    //mojody
    public BigDecimal inventory;

    private Integer version;
    //vaze hesab
    private List<TransactionDto> transactions;

    private Boolean isActive;
    public BankAccountDto() {
        this.isActive=true;
    }






    public Boolean getActive() { return isActive; }

    public void setActive(Boolean active) { isActive = active; }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public AccountTypeDto getAccountTypes() {
        return accountTypes;
    }

    public void setAccountTypes(AccountTypeDto accountTypes) {
        this.accountTypes = accountTypes;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getInventory() {
        return inventory;
    }

    public void setInventory(BigDecimal inventory) {
        this.inventory = inventory;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
