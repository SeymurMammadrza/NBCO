package com.app.nbco.loan.controller;

import com.app.nbco.customer.entity.Customer;
import com.app.nbco.loan.entity.Loan;
import com.app.nbco.loan.service.LoanService;
import com.app.nbco.payment.entity.Payment;
import com.app.nbco.payment.repository.PaymentRepository;
import com.app.nbco.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin
@RequestMapping("/api/loan")
public class LoanController {
    private LoanService loanService;
    private PaymentService paymentService;
    private PaymentRepository paymentRepository;

    public LoanController(LoanService loanService, PaymentService paymentService, PaymentRepository paymentRepository) {
        this.loanService = loanService;
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/{id}/payments")
    public String loanPayments(Model model, @PathVariable("id") Long id) {
        Loan loan = loanService.findById(id);
        Customer customer = loan.getCustomer();
        List<Payment> payments = loan.getPayments();
        log.info("loan ={}", loan);
        log.info("payments = {}", payments);
        model.addAttribute("customer",customer);
        model.addAttribute("loan", loan);
        model.addAttribute("payments", payments);
        return "payment/paymentListByLoan";
    }

    @GetMapping("/{id}/payments/new")
    public String newPaymentToLoan(Model model, Payment payment, @PathVariable("id") Long id) {
        Loan loan = loanService.findById(id);
        payment.setLoan(loan);
        model.addAttribute("loan", loan);
        model.addAttribute("payment", payment);
        return "payment/paymentOperations";
    }

    @PostMapping("{id}/payments/save")
    public String savePaymentToLoan(@ModelAttribute("payment") Payment payment, @PathVariable("id") Long id) {
        Loan loan = loanService.findById(id);
        payment.setLoan(loan);
        paymentRepository.save(payment);
        return "redirect:/api/loan/{id}/payments";
    }

    @GetMapping("/update/{id}")
    public String updateLoan(Model model, @PathVariable("id") Long id) {
        Loan loan = loanService.findById(id);
        Customer customer = loan.getCustomer();
        model.addAttribute("customer",customer);
        model.addAttribute("loan", loan);
        return "loan/loanUpdate";
    }

    @GetMapping("/delete/{id}")
    public String deleteLoan(@PathVariable("id") Long id) {
        loanService.deleteById(id);
        return "redirect:/api/customer/list";
    }

    @PostMapping("/save")
    @Transactional
    public String saveLoan(@ModelAttribute Loan loan) {
        loanService.save(loan);
        return "redirect:/api/customer/list";
    }
}
