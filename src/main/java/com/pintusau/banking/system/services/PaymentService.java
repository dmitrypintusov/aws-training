package com.pintusau.banking.system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pintusau.banking.system.entities.CompositeKey;
import com.pintusau.banking.system.entities.Payment;
import com.pintusau.banking.system.repositories.PaymentDao;

@Service
public class PaymentService implements CrudService<Payment> {

  @Autowired
  private PaymentDao paymentDao;

  @Override
  public Payment createOrUpdate(Payment payment) {
    return paymentDao.createOrUpdate(payment);
  }

  @Override
  public Payment getById(Long id) {
    return paymentDao.getById(id);
  }

  @Override
  public Payment getByCompositeKey(CompositeKey compositeKey) {
    return paymentDao.getByCompositeKey(compositeKey);
  }

  @Override
  public List<Payment> getAll() {
    return paymentDao.getAll();
  }

  @Override
  public void deleteById(Long id) {
    paymentDao.deleteById(id);
  }

  @Override
  public void deleteByCompositeKey(CompositeKey compositeKey) {
    paymentDao.deleteByCompositeKey(compositeKey);
  }

  @Override
  public Payment getByIndex(String indexName, CompositeKey compositeKey) {
    return paymentDao.getByIndex(indexName, compositeKey);
  }
}