package com.pintusau.banking.system.services;

import java.util.List;

import com.pintusau.banking.system.entities.CompositeKey;

public interface CrudService<T> {

  T createOrUpdate(T entity);

  T getById(Long id);

  T getByCompositeKey(CompositeKey compositeKey);

  List<T> getAll();

  void deleteById(Long id);

  void deleteByCompositeKey(CompositeKey compositeKey);

  T getByIndex(String indexName, CompositeKey compositeKey);
}