package com.app.nbco.payment.entity;

import com.app.nbco.loan.entity.Loan;
import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.dynalink.linker.LinkerServices;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate paymentDate;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "interest_charge")
    private Long interestCharge;

    @Column(name = "debt_before_payment")
    private Long debtBeforePayment;

    @Column(name = "remainingDebt")
    private Long remainingDebt;

    @ManyToOne
    private Loan loan;

    public Long getDebtBeforePayment(Loan loan) {
        List<Payment> payments = loan.getPayments();

        Long totalAmountOfPaidPayments = 0L;

        for(Payment payment: payments){
            if(payment.id < this.id) totalAmountOfPaidPayments += payment.amount;
        }

        Long debtBeforePayment = loan.getAmount() - totalAmountOfPaidPayments;
        return debtBeforePayment;
    }
    public Long getRemainingDebt(Loan loan) {

        Long remainingDebt = getDebtBeforePayment(loan) - this.amount;
        return remainingDebt;
    }
}
