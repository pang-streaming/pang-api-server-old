package com.pangapiserver.infrastructure.s3.service;

import com.pangapiserver.infrastructure.s3.properties.S3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import io.awspring.cloud.s3.S3Template;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Template s3Template;
    private final S3Properties properties;

    public String uploadImage(MultipartFile file) throws IOException {
        String key = "uploads/" + file.getOriginalFilename();
        s3Template.upload(properties.getBucket(), key, file.getInputStream());

        return String.format("https://%s.s3.%s.amazonaws.com/%s", properties.getBucket(), "ap-northeast-2", key);
    }
}
