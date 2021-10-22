package com.git.file;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class FileConfiguration {
    // S3에 접속하는 클라언트를 싱글턴을 생성
    // Spring에서 의존 주입해주는 객체로 사용하겠다.
    @Bean
    public AmazonS3 getS3Client() throws IOException {
        ClassPathResource resource = new ClassPathResource("aws-access-info.txt");

        String str = new String(resource.getInputStream().readAllBytes());

        String accessId = str.split(" ")[0];
        String secretKey = str.split(" ")[1];

        AWSCredentials credentials = new BasicAWSCredentials(accessId, secretKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }
}
