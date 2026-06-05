package edu.miu.dto;

import edu.miu.model.Account;
import edu.miu.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDto {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<AccountDto> accounts = new ArrayList<AccountDto>();

    public CustomerDto() {
    }

    public CustomerDto(String customerId, String firstName, String lastName, String email, String phone,
                       List<AccountDto> accounts) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.accounts = accounts;
    }

    public static CustomerDto fromCustomer(Customer customer) {
        List<AccountDto> accountDtos = new ArrayList<AccountDto>();

        for (Account account : customer.getAccounts()) {
            accountDtos.add(AccountDto.fromAccount(account));
        }

        return new CustomerDto(
                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                accountDtos
        );
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }
}
