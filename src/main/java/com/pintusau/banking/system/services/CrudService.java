package com.pintusau.banking.system.services;

import com.pintusau.banking.system.entities.AbstractEntity;
import java.util.List;
import java.util.Optional;

public interface CrudService<E extends AbstractEntity> {

  E createOrUpdate(E entity);

  E getById(Long id);

  List<E> getAll();

  void deleteById(Long id);
}
