package com.rayanen.bankAccount.dto;

import java.math.BigDecimal;

public class AmountDto {

    private String accountNumber;
    private BigDecimal amount;

    public AmountDto() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
