package com.app.nbco.customer.service;

import com.app.nbco.customer.entity.Customer;
import com.app.nbco.customer.repository.CustomerRepository;
import com.app.nbco.loan.entity.Loan;
import com.app.nbco.payment.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<Customer>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    public Customer save(Customer Customer) {
        return customerRepository.save(Customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public void deleteById(Long id) {
        customerRepository.delete(findById(id));
    }

    public Customer updateById(Long id, Customer customer) {
        customerRepository.findById(id);
        customer.setId(id);
        return customerRepository.save(customer);
    }

    public List<Loan> getLoansByCustomerId(Long id) {
        return customerRepository.findById(id).get().getLoans();
    }

    public void addLoanToCustomer(Loan loan, Long id) {
        List<Loan> customerLoans = customerRepository.findById(id).get().getLoans();
        customerLoans.add(loan);
    }
}
