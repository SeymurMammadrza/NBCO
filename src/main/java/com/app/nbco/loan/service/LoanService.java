package com.app.nbco.loan.service;

import com.app.nbco.loan.entity.Loan;
import com.app.nbco.loan.repository.LoanRepository;
import com.app.nbco.payment.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {
    private LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> findAll() {
        List<Loan> loans = new ArrayList<Loan>();
        loanRepository.findAll().forEach(loans::add);
        return loans;
    }

    public Loan findById(Long id) {
        return loanRepository.findById(id).get();
    }

    public Loan save(Loan loan) {
        return loanRepository.save(loan);
    }

    public void delete(Loan loan) {
        loanRepository.delete(loan);
    }

    public void deleteById(Long id) {
        loanRepository.delete(findById(id));
    }

    public Loan updateById(Long id, Loan loan) {
        loanRepository.findById(id);
        loan.setId(id);
        return loanRepository.save(loan);
    }

    public List<Payment> getPaymentsByLoanId(Long id) {
        return loanRepository.findById(id).get().getPayments();
    }

    public void addPaymentToLoan(Payment payment, Long id) {
        List<Payment> loanPayments = loanRepository.findById(id).get().getPayments();
        loanPayments.add(payment);
    }
}
