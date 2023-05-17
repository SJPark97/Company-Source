package com.jobtang.sourcecompany.api.community_image.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.jobtang.sourcecompany.api.community_image.entity.CommunityImage;
import com.jobtang.sourcecompany.api.community_image.repository.CommunityImageRepository;
import com.jobtang.sourcecompany.api.exception.customerror.CustomException;
import com.jobtang.sourcecompany.api.exception.customerror.ErrorCode;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CommunityImageServiceImpl implements CommunityImageService {
    private final AmazonS3Client amazonS3Client;
    private final CommunityImageRepository communityImageRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public HashMap<String, Object> createCommunityImage(MultipartFile file, Long userId) {
        HashMap<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_EXISTS));
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String fileName = now.format(formatter) + file.getOriginalFilename();
//        String fileUrl = "https://" + bucket + "/" +fileName;
        String fileUrl = "https://pjbooklet.s3.ap-northeast-2.amazonaws.com/" + fileName;
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
            amazonS3Client.setObjectAcl(bucket, fileName, CannedAccessControlList.PublicRead);
            result.put("data", fileUrl);
            CommunityImage communityImage = CommunityImage.builder()
                    .communityImageUrl(fileUrl)
                    .user(user)
                    .build();
            communityImageRepository.save(communityImage);
        } catch (IOException e) {
            // 에러 처리
            throw new CustomException(ErrorCode.AWS_ERROR);
        }
        return result;
    }
}
