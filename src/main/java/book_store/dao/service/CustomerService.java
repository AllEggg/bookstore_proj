package book_store.dao.service;

import book_store.dao.entity.Customer;
import book_store.dao.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerByName(String name) {
        return customerRepository.getCustomerByName(name);
    }
}
