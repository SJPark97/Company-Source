package com.jobtang.sourcecompany.api.community_image.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface CommunityImageService {

    public HashMap<String, Object> createCommunityImage(MultipartFile file, Long userId);

}
