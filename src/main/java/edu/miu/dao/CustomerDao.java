package edu.miu.dao;

import edu.miu.model.Customer;

import java.util.List;

public interface CustomerDao {
    void save(Customer customer);

    Customer findById(String customerId);

    List<Customer> findAll();
}
