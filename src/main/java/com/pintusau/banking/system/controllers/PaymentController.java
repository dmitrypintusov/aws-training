package com.pintusau.banking.system.controllers;

import com.pintusau.banking.system.entities.Payment;
import com.pintusau.banking.system.services.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {

  private PaymentService paymentService;

  @GetMapping
  @Operation(summary = "Get all payments")
  public List<Payment> getAll() {
    return paymentService.getAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a payment by id")
  public Payment getById(@PathVariable Long id) {
    return paymentService.getById(id);
  }

  @PostMapping
  @Operation(summary = "Create a new payment")
  public Payment create(@RequestBody Payment payment) {
    return paymentService.createOrUpdate(payment);
  }

  @PutMapping
  @Operation(summary = "Update the payment")
  public Payment update(@RequestBody Payment payment) {
    return paymentService.createOrUpdate(payment);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete the payment")
  public void deleteById(@PathVariable Long id) {
    paymentService.deleteById(id);
  }
}
