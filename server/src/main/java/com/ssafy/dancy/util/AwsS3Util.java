package com.ssafy.dancy.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
@PropertySource("classpath:s3.properties")
public class AwsS3Util {

    private final AmazonS3 s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.s3.commonUrl}")
    private String url;

    public String uploadToAWS(MultipartFile file, String storeFilePath) throws IOException{
        try{
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            PutObjectRequest request = new PutObjectRequest(bucketName, storeFilePath, file.getInputStream(), metadata);
            request.withCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(request);
            return String.format("%s%s", url, storeFilePath);
        }catch(AmazonServiceException e){
            log.error("uploadToAWS AmazonServiceException error={}", e.getMessage());
        }catch(SdkClientException e){
            log.error("uploadToAWS SdkClientException error={}", e.getMessage());
        }

        return "";
    }
}
