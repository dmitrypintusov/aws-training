package com.pintusau.banking.system.repositories;

import org.springframework.stereotype.Repository;

import com.pintusau.banking.system.entities.Account;

@Repository
public class AccountDao extends AbstractDao<Account> {

  public AccountDao() {
    super(Account.class);
  }
}