package com.ashish.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClient;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;

@Configuration
public class AwsSsmClientConfiguration {

    @Autowired
    private AWSCredentialsProvider awsCredentialsProvider;

    @Value("${cloud.aws.region.static}")
    private String awsRegion;
    
    @Bean("ssmClient")
    public AWSSimpleSystemsManagementClient ssmClient() {
        AWSSimpleSystemsManagementClientBuilder client = AWSSimpleSystemsManagementClientBuilder.standard();
        client.setCredentials(awsCredentialsProvider);
        client.setRegion(awsRegion);
        return (AWSSimpleSystemsManagementClient) client.build();
    }
}
