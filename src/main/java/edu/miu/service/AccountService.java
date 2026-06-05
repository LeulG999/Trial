package edu.miu.service;

import edu.miu.dto.AccountDto;
import edu.miu.dto.AccountListDto;
import edu.miu.model.Account;
import edu.miu.model.AccountTier;
import edu.miu.model.Customer;
import edu.miu.util.JsonConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AccountService {
    private final Map<String, Account> accounts = new LinkedHashMap<String, Account>();
    private final CustomerService customerService;

    public AccountService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Account createAccount(String accountNumber, String customerId, BigDecimal balance, LocalDate openedDate) {
        Customer customer = customerService.findCustomerById(customerId);

        if (customer == null) {
            throw new IllegalArgumentException("Customer not found: " + customerId);
        }

        Account account = new Account(accountNumber, balance, openedDate);
        customer.addAccount(account);
        accounts.put(accountNumber, account);
        customerService.save(customer);
        return account;
    }

    public Account findAccountByNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public List<Account> findAllAccounts() {
        return new ArrayList<Account>(accounts.values());
    }

    public List<Account> findAllAccountsSortedByBalanceDescending() {
        List<Account> sortedAccounts = findAllAccounts();
        Collections.sort(sortedAccounts, new Comparator<Account>() {
            public int compare(Account first, Account second) {
                return second.getBalance().compareTo(first.getBalance());
            }
        });
        return sortedAccounts;
    }

    public List<Account> findAccountsByCustomerId(String customerId) {
        List<Account> customerAccounts = new ArrayList<Account>();

        for (Account account : accounts.values()) {
            if (account.getCustomer() != null
                    && account.getCustomer().getCustomerId().equals(customerId)) {
                customerAccounts.add(account);
            }
        }

        return customerAccounts;
    }

    public String findAllAccountsAsJson() {
        List<AccountDto> accountDtos = accountsToDtos(findAllAccountsSortedByBalanceDescending());
        return JsonConverter.toJson(new AccountListDto(accountDtos, calculateLiquidityPosition()));
    }

    public String findAccountsByCustomerIdAsJson(String customerId) {
        return accountsToJson(findAccountsByCustomerId(customerId));
    }

    public String findPlatinumAccountsAsJson() {
        List<Account> platinumAccounts = new ArrayList<Account>();

        for (Account account : accounts.values()) {
            account.updateTier();

            if (account.getTier() == AccountTier.PLATINUM) {
                platinumAccounts.add(account);
            }
        }

        return accountsToJson(platinumAccounts);
    }

    private String accountsToJson(List<Account> accounts) {
        return JsonConverter.toJson(accountsToDtos(accounts));
    }

    private List<AccountDto> accountsToDtos(List<Account> accounts) {
        List<AccountDto> accountDtos = new ArrayList<AccountDto>();

        for (Account account : accounts) {
            accountDtos.add(AccountDto.fromAccount(account));
        }

        return accountDtos;
    }

    public BigDecimal calculateLiquidityPosition() {
        BigDecimal liquidityPosition = BigDecimal.ZERO;

        for (Account account : accounts.values()) {
            liquidityPosition = liquidityPosition.add(account.getBalance());
        }

        return liquidityPosition;
    }
}
