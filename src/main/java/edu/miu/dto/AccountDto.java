package edu.miu.dto;

import edu.miu.model.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDto {
    private String accountNumber;
    private BigDecimal balance;
    private LocalDate openedDate;
    private String tier;

    public AccountDto() {
    }

    public AccountDto(String accountNumber, BigDecimal balance, LocalDate openedDate, String tier) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.openedDate = openedDate;
        this.tier = tier;
    }

    public static AccountDto fromAccount(Account account) {
        account.updateTier();

        return new AccountDto(
                account.getAccountNumber(),
                account.getBalance(),
                account.getOpenedDate(),
                account.getTier().name()
        );
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
    }

    public LocalDate getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(LocalDate openedDate) {
        this.openedDate = openedDate;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }
}
