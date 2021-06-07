package com.pintusau.banking.system.repositories;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pintusau.banking.system.entities.AbstractEntity;
import com.pintusau.banking.system.utils.JsonUtil;
import com.pintusau.banking.system.utils.S3ClientUtil;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

public abstract class AbstractDao<T extends AbstractEntity> implements CrudDao<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDao.class);

  @Autowired
  private S3Client awsS3Client;
  @Autowired
  private JsonUtil jsonUtil;
  @Autowired
  private S3ClientUtil s3ClientUtil;

  private Class<T> type;

  protected AbstractDao(Class<T> type) {
    this.type = type;
  }

  @Override
  public T createOrUpdate(T entity) {
    String message = String.format("Create or update entity: %s", entity);

    if (entity != null) {
      LOGGER.info(message);

      try {
        String value = jsonUtil.serialize(entity);
        PutObjectRequest request = s3ClientUtil.createOrUpdateRequest(generateKey(entity.getId()));
        awsS3Client.putObject(request, RequestBody.fromString(value));
      } catch (Exception e) {
        message = String.format("Exception while accessing S3: %s", e.getMessage());
        LOGGER.error(message);
      }
    }

    return entity;
  }

  @Override
  public T getById(Long id) {
    LOGGER.info("Get entity by id: {}", id);
    try {
      GetObjectRequest request = s3ClientUtil.getRequest(generateKey(id));
      String jsonValue = awsS3Client.getObject(request, ResponseTransformer.toBytes()).asUtf8String();
      return (T) jsonUtil.deserialize(jsonValue, type);
    } catch (Exception e) {
      LOGGER.error("Exception while accessing S3: {}", e.getMessage());
    }

    return null;
  }

  @Override
  public List<T> getAll() {
    try {
      ListObjectsV2Request request = s3ClientUtil.getAllRequest(type.getSimpleName());
      ListObjectsV2Response response = awsS3Client.listObjectsV2(request);
      List<String> keys = response.contents().stream().map(S3Object::key).collect(Collectors.toList());

      LOGGER.info("Found keys: {}", keys);

      return keys.stream().map(key -> {
        Long id = keyToId(key);
        return this.getById(id);
      }).collect(Collectors.toList());
    } catch (Exception e) {
      LOGGER.error("Exception while accessing S3: {}", e.getMessage());
    }

    return Collections.emptyList();
  }

  @Override
  public void deleteById(Long id) {
    LOGGER.info("Delete entity by id: {}", id);
    try {
      DeleteObjectRequest request = s3ClientUtil.deleteRequest(generateKey(id));

      awsS3Client.deleteObject(request);
    } catch (Exception e) {
      String message = String.format("Exception while accessing S3: %s", e.getMessage());
      LOGGER.error(message);
    }
  }

  private String generateKey(Long id) {
    if (type != null && id != null) {
      return String.join("/", type.getSimpleName(), String.valueOf(id));
    } else {
      throw new IllegalArgumentException("Cannot generate a key for S3 request: " + id);
    }
  }

  private Long keyToId(String key) {
    String[] strings = key.split("/");
    return new Long(strings[1]);
  }
}