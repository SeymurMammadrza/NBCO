package com.app.nbco.payment.service;

import com.app.nbco.payment.entity.Payment;
import com.app.nbco.payment.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<Payment>();
        paymentRepository.findAll().forEach(payments::add);
        return payments;
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).get();
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void delete(Payment payment) {
        paymentRepository.delete(payment);
    }


    public void deleteById(Long id) {
        paymentRepository.delete(findById(id));
    }

    public Payment updateById(Long id, Payment payment) {
        paymentRepository.findById(id);
        payment.setId(id);
        return paymentRepository.save(payment);
    }
}
