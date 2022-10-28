package com.pintusau.banking.system.services;

import com.pintusau.banking.system.dao.AccountRepository;
import com.pintusau.banking.system.entities.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends AbstractService<Account, AccountRepository> {

  public AccountService(AccountRepository repository) {
    super(repository);
  }
}
