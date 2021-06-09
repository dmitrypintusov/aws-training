package com.pintusau.banking.system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pintusau.banking.system.entities.CompositeKey;
import com.pintusau.banking.system.entities.Key;
import com.pintusau.banking.system.entities.Payment;
import com.pintusau.banking.system.entities.enums.AttributeType;
import com.pintusau.banking.system.services.PaymentService;

import io.swagger.annotations.ApiOperation;

@RestController
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @GetMapping(value = "/payment")
  @ApiOperation(value = "Get all payments")
  public List<Payment> getAll() {
    return paymentService.getAll();
  }

  @GetMapping("/payment/{id}")
  @ApiOperation(value = "Get a payment by id")
  public Payment getById(@PathVariable Long id) {
    return paymentService.getById(id);
  }

  @PostMapping("/payment")
  @ApiOperation(value = "Create a new payment")
  public Payment create(@RequestBody Payment payment) {
    return paymentService.createOrUpdate(payment);
  }

  @PutMapping("/payment")
  @ApiOperation(value = "Update the payment")
  public Payment update(@RequestBody Payment payment) {
    return paymentService.createOrUpdate(payment);
  }

  @DeleteMapping("/payment/{id}")
  @ApiOperation(value = "Delete the payment")
  public void deleteById(@PathVariable Long id) {
    paymentService.deleteById(id);
  }

  @GetMapping("/payment/{id}/currency/{currency}")
  @ApiOperation(value = "Get a payment by id and currency")
  public Payment getByCompositeKey(@PathVariable String id, @PathVariable String currency) {
    Key primary = new Key("id", id, AttributeType.NUMBER);
    Key sort = new Key("currency", currency, AttributeType.STRING);
    CompositeKey compositeKey = new CompositeKey(primary, sort);
    return paymentService.getByCompositeKey(compositeKey);
  }

  @DeleteMapping("/payment/{id}/currency/{currency}")
  @ApiOperation(value = "Delete the payment by id and currency")
  public void deleteByCompositeKey(@PathVariable String id, @PathVariable String currency) {
    Key primary = new Key("id", id, AttributeType.NUMBER);
    Key sort = new Key("currency", currency, AttributeType.STRING);
    CompositeKey compositeKey = new CompositeKey(primary, sort);
    paymentService.deleteByCompositeKey(compositeKey);
  }
}