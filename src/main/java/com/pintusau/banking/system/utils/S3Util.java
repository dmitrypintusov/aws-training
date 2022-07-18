package com.pintusau.banking.system.utils;

import lombok.experimental.UtilityClass;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@UtilityClass
public class S3Util {

  public static PutObjectRequest createOrUpdateRequest(String bucketName, String key) {
    return PutObjectRequest
        .builder()
        .bucket(bucketName)
        .key(key)
        .build();
  }

  public static GetObjectRequest getRequest(String bucketName, String key) {
    return GetObjectRequest
        .builder()
        .bucket(bucketName)
        .key(key)
        .build();
  }

  public static ListObjectsRequest getAllRequest(String bucketName, String className) {
    return ListObjectsRequest
        .builder()
        .bucket(bucketName)
        .prefix(className)
        .build();
  }

  public static DeleteObjectRequest deleteRequest(String bucketName, String key) {
    return DeleteObjectRequest
        .builder()
        .bucket(bucketName)
        .key(key)
        .build();
  }

  public static String generateS3Key(String className, Long id) {
    if (className != null && id != null) {
      return String.join("/", className, String.valueOf(id));
    } else {
      throw new IllegalArgumentException("Cannot generate a key for S3 request: " + id);
    }
  }
}
