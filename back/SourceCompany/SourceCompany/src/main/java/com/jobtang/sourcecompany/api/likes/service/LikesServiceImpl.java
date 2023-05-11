package com.jobtang.sourcecompany.api.likes.service;

import com.jobtang.sourcecompany.api.community.entity.Community;
import com.jobtang.sourcecompany.api.community.repository.CommunityRepository;
import com.jobtang.sourcecompany.api.exception.CustomException;
import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.likes.entity.Likes;
import com.jobtang.sourcecompany.api.likes.repository.LikesRepository;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements  LikesService{
  private  final LikesRepository likesRepository;
  private final UserRepository userRepository;
  private final CommunityRepository communityRepository;

  @Override
  public Long createLikes(Long communityId, Long userId) {
    
    // 유저 밸리드 체크
    User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_EXISTS));
    // 커뮤니티 밸리드 체크
    Community community = communityRepository.findById(communityId).orElseThrow(() -> new CustomException(ErrorCode.COMM_EXISTS));
    // 이미 해당 라이크 있는지 체크
    likesRepository.findByUserIdAndCommunityId(userId , communityId);
    Likes likes = Likes.builder()
            .user(user)
            .community(community)
            .build();
    return likes.getId();
  }

  @Override
  public void deleteLikes(Long likesId) {

  }
}
