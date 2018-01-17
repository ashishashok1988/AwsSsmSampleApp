package com.ashish;

import java.io.UnsupportedEncodingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.context.config.annotation.EnableContextInstanceData;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableContextInstanceData
public class MyAppApplication {

    public static void main(String[] args) throws UnsupportedEncodingException {
        ApplicationContext ctx = SpringApplication.run(MyAppApplication.class, args);
    }
}
