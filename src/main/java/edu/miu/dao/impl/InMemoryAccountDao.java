package edu.miu.dao.impl;

import edu.miu.dao.AccountDao;
import edu.miu.model.Account;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InMemoryAccountDao implements AccountDao {
    private Map<String, Account> accounts = new LinkedHashMap<String, Account>();

    public void save(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findByAccountNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public List<Account> findAll() {
        return new ArrayList<Account>(accounts.values());
    }

    public List<Account> findByCustomerId(String customerId) {
        List<Account> customerAccounts = new ArrayList<Account>();

        for (Account account : accounts.values()) {
            if (account.getCustomer() != null
                    && account.getCustomer().getCustomerId().equals(customerId)) {
                customerAccounts.add(account);
            }
        }

        return customerAccounts;
    }
}
