package com.app.nbco.payment.controller;

import com.app.nbco.loan.entity.Loan;
import com.app.nbco.payment.entity.Payment;
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
@RequestMapping("/api/payment")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/update/{id}")
    public String getUpdatePayment(Model model, @PathVariable("id") Long id) {
        Payment payment = paymentService.findById(id);
        Loan loan = payment.getLoan();
        model.addAttribute("payment", payment);
        model.addAttribute("loan", loan);
        return "payment/paymentUpdate";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(@PathVariable("id") Long id) {
        paymentService.deleteById(id);
        return "redirect:/api/customer/list";
    }

    @PostMapping("/update/{id}")
    public String UpdatePaymentPost(Payment payment, @PathVariable("id") Long id) {
        paymentService.updateById(id,payment);
        return "redirect:/api/customer/list";
    }

}
