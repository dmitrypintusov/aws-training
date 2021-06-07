package com.pintusau.banking.system.utils;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3ClientUtil {

    private static final String BUCKET_NAME = "pintusau-banking-system-entities";

    public PutObjectRequest createOrUpdateRequest(String key) {
        return PutObjectRequest
                .builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .build();
    }

    public GetObjectRequest getRequest(String key) {
        return GetObjectRequest
                .builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .build();
    }

    public ListObjectsV2Request getAllRequest(String prefix){
        return ListObjectsV2Request
                .builder()
                .bucket(BUCKET_NAME)
                .prefix(prefix)
                .build();
    }

    public DeleteObjectRequest deleteRequest(String key){
        return DeleteObjectRequest
                .builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .build();
    }
}