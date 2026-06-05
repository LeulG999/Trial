package edu.miu;

import edu.miu.dao.AccountDao;
import edu.miu.dao.CustomerDao;
import edu.miu.dao.impl.InMemoryAccountDao;
import edu.miu.dao.impl.InMemoryCustomerDao;
import edu.miu.service.AccountService;
import edu.miu.service.CustomerService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final CustomerDao CUSTOMER_DAO = new InMemoryCustomerDao();
    private static final AccountDao ACCOUNT_DAO = new InMemoryAccountDao();

    private static final CustomerService CUSTOMER_SERVICE = new CustomerService(CUSTOMER_DAO);
    private static final AccountService ACCOUNT_SERVICE = new AccountService(ACCOUNT_DAO, CUSTOMER_DAO);

    public static void main(String[] args) {
        loadSampleData();

        System.out.println("Customers with accounts:");
        System.out.println(CUSTOMER_SERVICE.findAllCustomersAsJson());

        System.out.println();
        System.out.println("All accounts:");
        System.out.println(ACCOUNT_SERVICE.findAllAccountsAsJson());

        System.out.println();
        System.out.println("Platinum accounts only:");
        System.out.println(ACCOUNT_SERVICE.findPlatinumAccountsAsJson());
    }

    private static void loadSampleData() {
        List<String[]> customers = new ArrayList<String[]>();
        customers.add(new String[]{"C001", "John", "Doe", "john@email.com", "111-111-1111"});
        customers.add(new String[]{"C002", "Mary", "Smith", "mary@email.com", "222-222-2222"});

        for (String[] customer : customers) {
            CUSTOMER_SERVICE.createCustomer(customer[0], customer[1], customer[2], customer[3], customer[4]);
        }

        List<Object[]> accounts = new ArrayList<Object[]>();
        accounts.add(new Object[]{"A001", "C001", new BigDecimal("15000"), LocalDate.now().minusYears(3)});
        accounts.add(new Object[]{"A002", "C001", new BigDecimal("60000"), LocalDate.now().minusYears(6)});
        accounts.add(new Object[]{"A003", "C002", new BigDecimal("150000"), LocalDate.now().minusYears(12)});

        for (Object[] account : accounts) {
            ACCOUNT_SERVICE.createAccount(
                    (String) account[0],
                    (String) account[1],
                    (BigDecimal) account[2],
                    (LocalDate) account[3]
            );
        }
    }
}
