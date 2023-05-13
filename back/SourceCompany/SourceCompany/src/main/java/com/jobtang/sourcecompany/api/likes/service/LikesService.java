package com.jobtang.sourcecompany.api.likes.service;

public interface LikesService {

  Long createLikes(Long communityId , Long userId);

  void deleteLikes(Long userId , Long communityId);
}
