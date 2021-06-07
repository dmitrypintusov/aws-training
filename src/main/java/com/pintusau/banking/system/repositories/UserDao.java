package com.pintusau.banking.system.repositories;

import org.springframework.stereotype.Repository;

import com.pintusau.banking.system.entities.User;

@Repository
public class UserDao extends AbstractDao<User> {

  public UserDao() {
    super(User.class);
  }
}