package com.rayanen.bankAccount.dto;




import java.util.Date;
import java.util.List;

public abstract class CustomerDto {
    private Integer id;
    private String address;
    private String name;
    private String postalCode;
    private List<NumberDto> numbers;
    private Date date;
    private Integer version;
    private List<BankAccountDto> bankAccounts;


    public List<NumberDto> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<NumberDto> numbers) {
        this.numbers = numbers;
    }

    public List<BankAccountDto> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccountDto> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
