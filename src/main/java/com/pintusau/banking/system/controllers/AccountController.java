package com.pintusau.banking.system.controllers;

import com.pintusau.banking.system.entities.Account;
import com.pintusau.banking.system.services.AccountService;
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
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @GetMapping
  @Operation(summary = "Get all accounts")
  public List<Account> getAll() {
    return accountService.getAll();
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get an account by id")
  public Account getById(@PathVariable Long id) {
    return accountService.getById(id);
  }

  @PostMapping
  @Operation(summary = "Create a new account")
  public Account create(@RequestBody Account account) {
    return accountService.createOrUpdate(account);
  }

  @PutMapping
  @Operation(summary = "Update the account")
  public Account update(@RequestBody Account account) {
    return accountService.createOrUpdate(account);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete the account")
  public void deleteById(@PathVariable Long id) {
    accountService.deleteById(id);
  }
}
