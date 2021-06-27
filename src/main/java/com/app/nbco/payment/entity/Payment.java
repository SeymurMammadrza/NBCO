package com.app.nbco.payment.entity;

import com.app.nbco.loan.entity.Loan;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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

    @ManyToOne
    private Loan loan;

    public Long getDebtBeforePayment(Loan loan) {
        long debtBeforePayment = loan.getAmount();
        return debtBeforePayment;
    }

    public Long getRemainingDebt(Loan loan) {
        long remainingDebt = loan.getAmount() - amount;
        return remainingDebt;
    }
}
