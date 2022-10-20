package com.pintusau.banking.system.services;

import com.pintusau.banking.system.dao.AbstractRepository;
import com.pintusau.banking.system.entities.AbstractEntity;
import com.pintusau.banking.system.services.aws.s3.S3Service;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractService<E extends AbstractEntity, R extends AbstractRepository<E>> implements CrudService<E> {

  @Autowired
  private S3Service s3Service;
  protected final R repository;

  @Autowired
  protected AbstractService(R repository) {
    this.repository = repository;
  }

  @Override
  public E createOrUpdate(E entity) {
    log.info("Create or update entity: {}", entity);

    return repository.save(entity);
  }

  @Override
  public E getById(Long id) {
    log.info("Get entity by id: {}", id);

    return repository.findById(id).orElse(null);
  }

  @Override
  public List<E> getAll() {
    log.info("Get all entities");

    return repository.findAll();
  }

  @Override
  public void deleteById(Long id) {
    log.info("Delete entity by id: {}", id);

    repository.deleteById(id);
  }
}
