package com.jobtang.sourcecompany.api.community_image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CommunityImageServiceImpl implements CommunityImageService {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Override
    public HashMap<String, Object> createCommunityImage(MultipartFile file, Long userId) {
        HashMap<String, Object> result = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String fileName = now.format(formatter) + file.getOriginalFilename();
        String fileUrl = "https://" + bucket + "/" +fileName;
//        String userUrl = "https://pjbooklet.s3.ap-northeast-2.amazonaws.com/" + fileName;
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
            result.put("data", fileUrl);
        } catch (IOException e) {
            // 에러 처리
            throw new CustomException(ErrorCode.AWS_ERROR);
        }
        return result;
    }
}
