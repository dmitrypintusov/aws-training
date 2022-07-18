package com.pintusau.banking.system.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSConfig {

  @Bean
  public S3Client awsS3Client() {
    return S3Client.builder().build();
  }
}
