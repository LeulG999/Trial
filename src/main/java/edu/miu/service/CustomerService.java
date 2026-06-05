package edu.miu.service;

import edu.miu.dao.CustomerDao;
import edu.miu.dto.CustomerDto;
import edu.miu.model.Customer;
import edu.miu.util.JsonConverter;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Customer createCustomer(String customerId, String firstName, String lastName, String email, String phone) {
        Customer customer = new Customer(customerId, firstName, lastName, email, phone);
        customerDao.save(customer);
        return customer;
    }

    public Customer findCustomerById(String customerId) {
        return customerDao.findById(customerId);
    }

    public List<Customer> findAllCustomers() {
        return customerDao.findAll();
    }

    public String findAllCustomersAsJson() {
        List<CustomerDto> customerDtos = new ArrayList<CustomerDto>();

        for (Customer customer : customerDao.findAll()) {
            customerDtos.add(CustomerDto.fromCustomer(customer));
        }

        return JsonConverter.toJson(customerDtos);
    }
}
