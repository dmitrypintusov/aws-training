package com.pintusau.banking.system.repositories;

import org.springframework.stereotype.Repository;

import com.pintusau.banking.system.entities.Payment;

@Repository
public class PaymentDao extends AbstractDao<Payment> {

  public PaymentDao() {
    super(Payment.class);
  }
}