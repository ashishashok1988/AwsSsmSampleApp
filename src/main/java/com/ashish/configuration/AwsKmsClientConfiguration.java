package com.ashish.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.kms.AWSKMSClientBuilder;

@Configuration
public class AwsKmsClientConfiguration {

    @Autowired
    private AWSCredentialsProvider awsCredentialsProvider;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;

    @Bean("kmsClient")
    public AWSKMSClient kmsClient() {
        AWSKMSClientBuilder client = AWSKMSClientBuilder.standard();
        client.setCredentials(awsCredentialsProvider);
        client.setRegion(awsRegion);
        return (AWSKMSClient) client.build();
    }
    
}
