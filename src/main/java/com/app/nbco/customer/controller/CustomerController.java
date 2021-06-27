package com.app.nbco.customer.controller;

import com.app.nbco.customer.entity.Customer;
import com.app.nbco.customer.repository.CustomerRepository;
import com.app.nbco.customer.service.CustomerService;
import com.app.nbco.loan.entity.Loan;
import com.app.nbco.loan.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private LoanService loanService;

    public CustomerController(CustomerService customerService, CustomerRepository customerRepository, LoanService loanService) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.loanService = loanService;
    }

    @GetMapping("{id}/loans")
    public String customerLoans(Model model, @PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        List<Loan> loans = customer.getLoans();
        log.info("customer ={}", customer);
        log.info("loans = {}", customer.getLoans());
        model.addAttribute("customer", customer);
        model.addAttribute("loans", loans);
        return "loan/loanListByCustomer";
    }

    @GetMapping("{id}/loans/new")
    public String newLoanToCustomer(Model model,Loan loan, @PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        loan.setCustomer(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("loan", loan);
        return "loan/loanOperations";
    }

    //Save loan
    @PostMapping("{id}/loans/save")
    public String saveLoanToCustomer(@ModelAttribute Loan loan, @PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        loan.setCustomer(customer);
        loanService.save(loan);
        return "redirect:/api/customer/{id}/loans";
    }

    @GetMapping("/list")
    public String customers(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/customerList";
    }

    @GetMapping("/new")
    public String newCustomer(Model model, Customer customer) {
        model.addAttribute("customer", customer);
        return "customer/customerOperations";
    }

    //Save customer
    @PostMapping("/save")
    @Transactional
    public String saveCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/api/customer/list";
    }

    @GetMapping("/update/{id}")
    @Transactional
    public String updateCustomer(Model model, @PathVariable("id") Long id) {
        model.addAttribute("customer", customerService.findById(id));
        return "customer/customerUpdate";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteById(id);
        System.out.println("customer deleted");
        return "redirect:/api/customer/list";
    }
}
