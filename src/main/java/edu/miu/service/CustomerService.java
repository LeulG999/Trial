package edu.miu.service;

import edu.miu.dto.CustomerDto;
import edu.miu.model.Customer;
import edu.miu.util.JsonConverter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
    private final Map<String, Customer> customers = new LinkedHashMap<String, Customer>();

    public Customer createCustomer(String customerId, String firstName, String lastName, String email, String phone) {
        Customer customer = new Customer(customerId, firstName, lastName, email, phone);
        customers.put(customerId, customer);
        return customer;
    }

    public Customer findCustomerById(String customerId) {
        return customers.get(customerId);
    }

    public List<Customer> findAllCustomers() {
        return new ArrayList<Customer>(customers.values());
    }

    public void save(Customer customer) {
        customers.put(customer.getCustomerId(), customer);
    }

    public String findAllCustomersAsJson() {
        List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();

        for (Customer customer : customers.values()) {
            customerDtos.add(CustomerDto.fromCustomer(customer));
        }

        return JsonConverter.toJson(customerDtos);
    }
}
