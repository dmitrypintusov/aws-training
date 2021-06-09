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

import com.pintusau.banking.system.entities.Account;
import com.pintusau.banking.system.entities.CompositeKey;
import com.pintusau.banking.system.entities.Key;
import com.pintusau.banking.system.entities.enums.AttributeType;
import com.pintusau.banking.system.services.AccountService;

import io.swagger.annotations.ApiOperation;

@RestController
public class AccountController {

  @Autowired
  private AccountService accountService;

  @GetMapping(value = "/account")
  @ApiOperation(value = "Get all accounts")
  public List<Account> getAll() {
    return accountService.getAll();
  }

  @GetMapping("/account/{id}")
  @ApiOperation(value = "Get an account by id")
  public Account getById(@PathVariable Long id) {
    return accountService.getById(id);
  }

  @PostMapping("/account")
  @ApiOperation(value = "Create a new account")
  public Account create(@RequestBody Account account) {
    return accountService.createOrUpdate(account);
  }

  @PutMapping("/account")
  @ApiOperation(value = "Update the account")
  public Account update(@RequestBody Account account) {
    return accountService.createOrUpdate(account);
  }

  @DeleteMapping("/account/{id}")
  @ApiOperation(value = "Delete the account")
  public void deleteById(@PathVariable Long id) {
    accountService.deleteById(id);
  }

  @GetMapping("/account/{id}/currency/{currency}")
  @ApiOperation(value = "Get an account by id and currency")
  public Account getByCompositeKey(@PathVariable String id, @PathVariable String currency) {
    Key primary = new Key("id", id, AttributeType.NUMBER);
    Key sort = new Key("currency", currency, AttributeType.STRING);
    CompositeKey compositeKey = new CompositeKey(primary, sort);
    return accountService.getByCompositeKey(compositeKey);
  }

  @DeleteMapping("/account/{id}/currency/{currency}")
  @ApiOperation(value = "Delete the account by id and currency")
  public void deleteByCompositeKey(@PathVariable String id, @PathVariable String currency) {
    Key primary = new Key("id", id, AttributeType.NUMBER);
    Key sort = new Key("currency", currency, AttributeType.STRING);
    CompositeKey compositeKey = new CompositeKey(primary, sort);
    accountService.deleteByCompositeKey(compositeKey);
  }
}