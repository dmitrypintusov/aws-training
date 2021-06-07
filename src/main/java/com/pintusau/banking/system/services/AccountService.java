package com.pintusau.banking.system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pintusau.banking.system.entities.Account;
import com.pintusau.banking.system.repositories.AccountDao;

@Service
public class AccountService implements CrudService<Account> {

  @Autowired
  private AccountDao accountDao;

  @Override
  public Account createOrUpdate(Account account) {
    return accountDao.createOrUpdate(account);
  }

  @Override
  public Account getById(Long id) {
    return accountDao.getById(id);
  }

  @Override
  public List<Account> getAll() {
    return accountDao.getAll();
  }

  @Override
  public void deleteById(Long id) {
    accountDao.deleteById(id);
  }
}