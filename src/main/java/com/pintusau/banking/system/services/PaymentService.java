package com.pintusau.banking.system.services;

import com.pintusau.banking.system.dao.PaymentRepository;
import com.pintusau.banking.system.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends AbstractService<Payment, PaymentRepository> {

  public PaymentService(PaymentRepository repository) {
    super(repository);
  }
}
