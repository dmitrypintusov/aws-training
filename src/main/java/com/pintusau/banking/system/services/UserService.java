package com.pintusau.banking.system.services;

import com.pintusau.banking.system.dao.UserRepository;
import com.pintusau.banking.system.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, UserRepository> {

  public UserService(UserRepository repository) {
    super(repository);
  }
}
