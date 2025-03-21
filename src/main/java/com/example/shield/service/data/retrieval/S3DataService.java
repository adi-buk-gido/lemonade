package com.example.shield.service.data.retrieval;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@Slf4j
@Service
public class S3DataService implements IDataRetrievalService {

    @Value("${aws.s3.access-key}")
    private String accessKey;

    @Value("${aws.s3.secret-key}")
    private String secretKey;

    @Value("${aws.s3.region}")
    private String region;
    
    private S3Client s3Client;


    @Override
    public void connect() {
        log.info("Connecting to AWS S3...");

        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();

        log.info("Successfully connected to AWS S3 in region: {}", region);
    }

    @Override
    public void disconnect() {
        if (s3Client != null) {
            log.info("Disconnecting from AWS S3...");
            s3Client.close();
            log.info("AWS S3 Client closed successfully.");
        }
    }

    @Override
    public InputStream retrieveData(String inputId) {
        log.info("Fetching file '{}' from S3 bucket '{}'", inputId);
        
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .key(inputId)
                    .build();

            return s3Client.getObject(getObjectRequest);

        } catch (S3Exception | SdkClientException e) {
            log.error("Error retrieving  :{} from S3", inputId, e);
            throw new RuntimeException("Failed to retrieve file from S3", e);
        }

    }

}
