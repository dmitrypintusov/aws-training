package com.pintusau.banking.system.repositories;

import java.util.List;

import com.pintusau.banking.system.entities.AbstractEntity;

public interface CrudDao<T extends AbstractEntity> {

  T createOrUpdate(T entity);

  T getById(Long id);

  List<T> getAll();

  void deleteById(Long id);
}