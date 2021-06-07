package com.pintusau.banking.system.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pintusau.banking.system.entities.User;
import com.pintusau.banking.system.repositories.UserDao;

@Service
public class UserService implements CrudService<User> {

  @Autowired
  private UserDao userDao;

  @Override
  public User createOrUpdate(User user) {
    return userDao.createOrUpdate(user);
  }

  @Override
  public User getById(Long id) {
    return userDao.getById(id);
  }

  @Override
  public void deleteById(Long id) {
    userDao.deleteById(id);
  }

  @Override
  public List<User> getAll() {
    return userDao.getAll();
  }
}