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

import com.pintusau.banking.system.entities.Payment;
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
}