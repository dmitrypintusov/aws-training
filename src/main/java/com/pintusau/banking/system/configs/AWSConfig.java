package com.pintusau.banking.system.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfig {

  @Bean
  public S3Client awsS3Client() {
    return S3Client.builder().region(Region.US_EAST_1).build();
  }

  @Bean
  public DynamoDbClient dynamoDbClient() {
    return DynamoDbClient.builder().region(Region.US_EAST_1).build();
  }
}