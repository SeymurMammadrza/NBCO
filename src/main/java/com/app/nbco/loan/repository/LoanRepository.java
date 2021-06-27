package com.app.nbco.loan.repository;

import com.app.nbco.loan.entity.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loan,Long> {
}
