package com.git.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

@RestController
public class FileController {

    private final String BUCKET_NAME = "git2021-bucket";
    private final String DISTRIBUTION_URL = "https://d3o6g8deu522v9.cloudfront.net/";
    private AmazonS3 client;

    @Autowired
    public FileController(AmazonS3 client) {
        this.client = client;
    }

    @PostMapping("/files")
    public String uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());

        // 1. 파일 메타 데이터 생성
        // S3에 올라가는 객체 메타데이터터를 설정해줌
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType()); // image/jpeg
        metadata.setContentLength(file.getSize()); // 50kb

        // 2. 객체 key 생성
        // S3에서는 파일 경로 key
        // 예) 20211022/images/penguin.jpg
        String objectKey = getObjectKey(file.getOriginalFilename());

        // 3. put 요청 객체 생성, public-read
        PutObjectRequest req = new PutObjectRequest(
                BUCKET_NAME,
                objectKey,
                file.getInputStream(),
                metadata
        ).withCannedAcl(CannedAccessControlList.PublicRead);

        // 4. 객체 업로드
        PutObjectResult result = client.putObject(req);
        System.out.println(result.getETag());

        return DISTRIBUTION_URL + objectKey;
    }

    @DeleteMapping("/files/{objectKey}")
    public void deleteFile(@PathVariable String objectKey, HttpServletResponse res) {
        // 버킷에 객체가 있는 확인
        if (!client.doesObjectExist(BUCKET_NAME, objectKey)) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        client.deleteObject(BUCKET_NAME, objectKey);
        System.out.println("--deleted--");
    }

    // OTP(One Time Password): secret + unique + time
    // 예) dflkajlksdjf9323asdf + kdkcom + 1600234023040

    // object key 해시 생성
    private String getObjectKey(String filename) {
        String secret = "git2021";
        long timestamp = new Date().getTime(); // 160664030340... 1970/01/01 unix epoch time

        return sha256Hex(secret + filename + timestamp);
    }
}
