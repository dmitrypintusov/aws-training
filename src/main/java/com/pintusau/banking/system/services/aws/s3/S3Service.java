package com.pintusau.banking.system.services.aws.s3;

import com.pintusau.banking.system.utils.JsonUtil;
import com.pintusau.banking.system.utils.S3Util;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

@Slf4j
@Service
public class S3Service {

  public static final String EXCEPTION_WHILE_ACCESSING_S3 = "Exception while accessing S3: {}";
  @Value("${entities.s3.bucket.name}")
  private String s3BucketName;

  @Autowired
  private S3Client awsS3Client;

  public String getObject(String key) {
    try {
      GetObjectRequest request = S3Util.getRequest(s3BucketName, key);
      return awsS3Client.getObject(request, ResponseTransformer.toBytes()).asUtf8String();
    } catch (Exception e) {
      log.error(EXCEPTION_WHILE_ACCESSING_S3, e.getMessage());
    }

    return null;
  }

  public void putObject(String key, Object object) {
    try {
      String value = JsonUtil.serialize(object);
      PutObjectRequest request = S3Util.createOrUpdateRequest(s3BucketName, key);
      awsS3Client.putObject(request, RequestBody.fromString(value));
    } catch (Exception e) {
      log.error(EXCEPTION_WHILE_ACCESSING_S3, e.getMessage());
    }
  }

  public List<String> getList(String prefix) {
    try {
      ListObjectsRequest request = S3Util.getAllRequest(s3BucketName, prefix);
      ListObjectsResponse response = awsS3Client.listObjects(request);
      List<String> keys = response.contents().stream().map(S3Object::key).collect(Collectors.toList());

      log.info("Found keys: {}", keys);

      return keys.stream().map(this::getObject).collect(Collectors.toList());
    } catch (Exception e) {
      log.error(EXCEPTION_WHILE_ACCESSING_S3, e.getMessage());
    }

    return List.of();
  }

  public void deleteObject(String key) {
    try {
      DeleteObjectRequest request = S3Util.deleteRequest(s3BucketName, key);

      awsS3Client.deleteObject(request);
    } catch (Exception e) {
      log.error(EXCEPTION_WHILE_ACCESSING_S3, e.getMessage());
    }
  }
}
