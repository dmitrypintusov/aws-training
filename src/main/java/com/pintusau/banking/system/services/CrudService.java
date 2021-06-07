package com.pintusau.banking.system.services;

import java.util.List;

public interface CrudService<T> {

  T createOrUpdate(T entity);

  T getById(Long id);

  List<T> getAll();

  void deleteById(Long id);
}