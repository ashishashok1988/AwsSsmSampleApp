package com.ashish.service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.DecryptResult;
import com.amazonaws.services.kms.model.EncryptRequest;
import com.amazonaws.services.kms.model.EncryptResult;
import com.amazonaws.util.Base64;

@Service
public class AwsKmsService {

    @Autowired
    private AWSKMSClient kmsClient;

    @Value("${cloud.aws.kms.dishIt.master.key.id}")
    private String awsKmsDishITCMKId;

    public void listKeys() {
        System.out.println("List of KMS Master Keys: " + kmsClient.listKeys());
    }

    public String encrypt(String stringToEncrypt) {
        EncryptRequest encryptRequest = new EncryptRequest();
        encryptRequest.setKeyId(awsKmsDishITCMKId);
        encryptRequest.setPlaintext(ByteBuffer.wrap(stringToEncrypt.getBytes()));
        EncryptResult encryptResult = kmsClient.encrypt(encryptRequest);
        ByteBuffer byteBuffer = encryptResult.getCiphertextBlob();
        return Base64.encodeAsString(byteBuffer.array());
    }

    public String decrypt(String encryptedString) {
        DecryptRequest decryptRequest = new DecryptRequest();
        decryptRequest.setCiphertextBlob(ByteBuffer.wrap(Base64.decode(encryptedString)));
        DecryptResult decryptedResult =  kmsClient.decrypt(decryptRequest);
        return StandardCharsets.UTF_8.decode(decryptedResult.getPlaintext()).toString();
    }
}
