package com.ashish;

import java.io.UnsupportedEncodingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.context.config.annotation.EnableContextInstanceData;
import org.springframework.context.ApplicationContext;

import com.ashish.service.AwsKmsService;

@SpringBootApplication
@EnableContextInstanceData
public class MyAppApplication {

    public static void main(String[] args) throws UnsupportedEncodingException {
        ApplicationContext ctx = SpringApplication.run(MyAppApplication.class, args);
        /*AwsKmsService awsKmsService = (AwsKmsService) ctx.getBean("awsKmsService");
        awsKmsService.listKeys();
        String stringToEncrypt = "HAHAClintonDicks";
        System.out.println("String to encrypt: " + stringToEncrypt);
        String encryptedString = awsKmsService.encrypt(stringToEncrypt);
        System.out.println("Encrypted String: " + encryptedString);
        System.out.println("Decrypted String: " + awsKmsService.decrypt(encryptedString));*/
    }
}
