package edu.miu.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Account {
    private String accountNumber;
    private BigDecimal balance;
    private LocalDate openedDate;
    private AccountTier tier;
    private Customer customer;

    public Account() {
    }

    public Account(String accountNumber, BigDecimal balance, LocalDate openedDate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.openedDate = openedDate;
        this.tier = calculateTier();
    }

    public AccountTier calculateTier() {
        int yearsOpen = Period.between(openedDate, LocalDate.now()).getYears();

        if (yearsOpen >= 10 && balance.compareTo(new BigDecimal("100000")) >= 0) {
            return AccountTier.PLATINUM;
        }

        if (yearsOpen >= 5 && balance.compareTo(new BigDecimal("50000")) >= 0) {
            return AccountTier.GOLD;
        }

        return AccountTier.SILVER;
    }

    public void updateTier() {
        this.tier = calculateTier();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
        updateTier();
    }

    public LocalDate getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(LocalDate openedDate) {
        this.openedDate = openedDate;
        updateTier();
    }

    public AccountTier getTier() {
        return tier;
    }

    public void setTier(AccountTier tier) {
        this.tier = tier;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
