package com.ashish.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.service.AwsSsmService;

@RestController
public class SSMController {

    @Autowired
    private AwsSsmService awsSsmService;
    
    @RequestMapping(value = "/ssm/storeParameter/{parameterValue}", method = RequestMethod.POST)
    public String encryptThroughKms(@PathVariable String parameterValue) throws UnsupportedEncodingException {
        return awsSsmService.putParameterStore(parameterValue);
    }

    @RequestMapping(value = "/ssm/getParameter", method = RequestMethod.GET)
    public String decryptThroughKms() throws UnsupportedEncodingException {
        return awsSsmService.getParameterStore(true) ;
    }
}
