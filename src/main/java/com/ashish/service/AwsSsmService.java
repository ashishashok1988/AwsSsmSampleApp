package com.ashish.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClient;
import com.amazonaws.services.simplesystemsmanagement.model.GetParametersRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParametersResult;
import com.amazonaws.services.simplesystemsmanagement.model.Parameter;
import com.amazonaws.services.simplesystemsmanagement.model.ParameterType;
import com.amazonaws.services.simplesystemsmanagement.model.PutParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.PutParameterResult;

@Service
public class AwsSsmService {

    @Autowired
    private AWSSimpleSystemsManagementClient ssmClient;

    @Value("${cloud.aws.kms.org.master.key.id}")
    private String awsKmsOrgCMKId;

    private static String API_KEY = "ORG_API_KEY";

    public String putParameterStore(String dishItApiKey) {
        PutParameterRequest putParameterRequest = new PutParameterRequest();
        putParameterRequest.setKeyId(awsKmsOrgCMKId);
        putParameterRequest.setOverwrite(true);
        putParameterRequest.setValue(dishItApiKey);
        putParameterRequest.setType(ParameterType.SecureString);
        putParameterRequest.setName(API_KEY);
        PutParameterResult putParameterResult = ssmClient.putParameter(putParameterRequest);
        return putParameterResult.toString();
    }

    public String getParameterStore(Boolean isDecrypted) {
        String value = "";
        GetParametersRequest getParameterRequest = new GetParametersRequest();
        getParameterRequest.setWithDecryption(isDecrypted);
        getParameterRequest.setNames(Collections.singletonList(API_KEY));
        GetParametersResult getParametersResult = ssmClient.getParameters(getParameterRequest);
        List<Parameter> parameters = getParametersResult.getParameters();
        for (Parameter parameter : parameters) {
            if (parameter.getName().equals(API_KEY)) {
                value = parameter.getValue();
            }
        }
        return value;
    }
}
