package com.pintusau.banking.system.services;

import com.pintusau.banking.system.entities.AbstractEntity;
import com.pintusau.banking.system.services.aws.s3.S3Service;
import com.pintusau.banking.system.utils.JsonUtil;
import com.pintusau.banking.system.utils.S3Util;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public abstract class AbstractService<T extends AbstractEntity> implements CrudService<T> {

  @Autowired
  private S3Service s3Service;
  private final Class<T> entityClass;

  protected AbstractService() {
    this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
        .getActualTypeArguments()[0];
  }

  @Override
  public T createOrUpdate(T entity) {
    log.info("Create or update entity: {}", entity);

    s3Service.putObject(S3Util.generateS3Key(entityClass.getSimpleName(), entity.getId()), entity);

    return entity;
  }

  @Override
  public T getById(Long id) {
    log.info("Get entity by id: {}", id);

    String jsonValue = s3Service.getObject(S3Util.generateS3Key(entityClass.getSimpleName(), id));
    return (T) JsonUtil.deserialize(jsonValue, entityClass);
  }

  @Override
  public List<T> getAll() {
    log.info("Get all entities: {}", entityClass.getSimpleName());

    List<String> entities = s3Service.getList(entityClass.getSimpleName());

    return entities.stream()
        .map(jsonValue -> (T) JsonUtil.deserialize(jsonValue, entityClass))
        .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    log.info("Delete entity by id: {}", id);

    s3Service.deleteObject(S3Util.generateS3Key(entityClass.getSimpleName(), id));
  }
}
