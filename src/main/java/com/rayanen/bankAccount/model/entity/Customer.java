package com.rayanen.bankAccount.model.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@MappedSuperclass
@Table(name="ARN_CUSTOMER")
@EntityListeners(AuditingEntityListener.class)
public abstract class Customer  {

    @Id
    @GeneratedValue
    private Integer id;

    private String address;

    private String name;

    private String postalCode;


    @OneToMany(cascade = CascadeType.ALL)

    private List<Number> numbers;

    @CreatedDate
    @Column(name = "CUSTOMER_CREATEDATE")
    private Date date;

    @Version
    private Integer version;



    @OneToMany(cascade = CascadeType.ALL)

    private List<BankAccount> bankAccounts;


    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
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



    public List<Number> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Number> numbers) {
        this.numbers = numbers;
    }
}
