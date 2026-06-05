package edu.miu.dto;

import edu.miu.model.Account;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDto {
    private String accountNumber;
    private BigDecimal balance;
    private LocalDate openedDate;
    private String tier;
    private String customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerPhone;

    public AccountDto() {
    }

    public AccountDto(String accountNumber, BigDecimal balance, LocalDate openedDate, String tier,
                      String customerId, String customerFirstName, String customerLastName, String customerEmail,
                      String customerPhone) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.openedDate = openedDate;
        this.tier = tier;
        this.customerId = customerId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
    }

    public static AccountDto fromAccount(Account account) {
        account.updateTier();

        return new AccountDto(
                account.getAccountNumber(),
                account.getBalance(),
                account.getOpenedDate(),
                account.getTier().name(),
                account.getCustomer() == null ? null : account.getCustomer().getCustomerId(),
                account.getCustomer() == null ? null : account.getCustomer().getFirstName(),
                account.getCustomer() == null ? null : account.getCustomer().getLastName(),
                account.getCustomer() == null ? null : account.getCustomer().getEmail(),
                account.getCustomer() == null ? null : account.getCustomer().getPhone()
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
}
