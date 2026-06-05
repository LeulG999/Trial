package edu.miu.dao;

import edu.miu.model.Account;

import java.util.List;

public interface AccountDao {
    void save(Account account);

    Account findByAccountNumber(String accountNumber);

    List<Account> findAll();

    List<Account> findByCustomerId(String customerId);
}
