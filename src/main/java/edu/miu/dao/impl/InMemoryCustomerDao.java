package edu.miu.dao.impl;

import edu.miu.dao.CustomerDao;
import edu.miu.model.Customer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InMemoryCustomerDao implements CustomerDao {
    private Map<String, Customer> customers = new LinkedHashMap<String, Customer>();

    public void save(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public Customer findById(String customerId) {
        return customers.get(customerId);
    }

    public List<Customer> findAll() {
        return new ArrayList<Customer>(customers.values());
    }
}
