package edu.miu.service;

import edu.miu.dao.AccountDao;
import edu.miu.dao.CustomerDao;
import edu.miu.dto.AccountDto;
import edu.miu.model.Account;
import edu.miu.model.AccountTier;
import edu.miu.model.Customer;
import edu.miu.util.JsonConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private AccountDao accountDao;
    private CustomerDao customerDao;

    public AccountService(AccountDao accountDao, CustomerDao customerDao) {
        this.accountDao = accountDao;
        this.customerDao = customerDao;
    }

    public Account createAccount(String accountNumber, String customerId, BigDecimal balance, LocalDate openedDate) {
        Customer customer = customerDao.findById(customerId);

        if (customer == null) {
            throw new IllegalArgumentException("Customer not found: " + customerId);
        }

        Account account = new Account(accountNumber, balance, openedDate);
        customer.addAccount(account);
        accountDao.save(account);
        customerDao.save(customer);
        return account;
    }

    public Account findAccountByNumber(String accountNumber) {
        return accountDao.findByAccountNumber(accountNumber);
    }

    public List<Account> findAllAccounts() {
        return accountDao.findAll();
    }

    public List<Account> findAccountsByCustomerId(String customerId) {
        return accountDao.findByCustomerId(customerId);
    }

    public String findAllAccountsAsJson() {
        return accountsToJson(accountDao.findAll());
    }

    public String findAccountsByCustomerIdAsJson(String customerId) {
        return accountsToJson(accountDao.findByCustomerId(customerId));
    }

    public String findPlatinumAccountsAsJson() {
        List<Account> platinumAccounts = new ArrayList<Account>();

        for (Account account : accountDao.findAll()) {
            account.updateTier();

            if (account.getTier() == AccountTier.PLATINUM) {
                platinumAccounts.add(account);
            }
        }

        return accountsToJson(platinumAccounts);
    }

    private String accountsToJson(List<Account> accounts) {
        List<AccountDto> accountDtos = new ArrayList<AccountDto>();

        for (Account account : accounts) {
            accountDtos.add(AccountDto.fromAccount(account));
        }

        return JsonConverter.toJson(accountDtos);
    }
}
