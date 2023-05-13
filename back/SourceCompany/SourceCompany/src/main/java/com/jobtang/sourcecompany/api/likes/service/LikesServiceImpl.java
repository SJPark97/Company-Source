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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {
  private final LikesRepository likesRepository;
  private final UserRepository userRepository;
  private final CommunityRepository communityRepository;

  @Override
  @Transactional
  public Long createLikes(Long communityId, Long userId) {

    // 유저 밸리드 체크
    User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_EXISTS));
    // 커뮤니티 밸리드 체크
    Community community = communityRepository.findById(communityId).orElseThrow(() -> new CustomException(ErrorCode.COMM_EXISTS));
    // 이미 해당 라이크 있는지 체크
    Optional<Likes> likees = likesRepository.findByUserIdAndCommunityId(userId, communityId);
    if (likees.isPresent()) {
      //  이미 해당라이크가 있고 , 유효하지않은 경우에는 다시 likecnt증가
      if(likees.get().isActive()==false){
        likees.get().getCommunity().addLikeCnt();
      }
      likees.get().reCreateEntity();
      return likees.get().getId();
    }
    //없으면 새로 생성
    Likes likes = Likes.builder()
            .user(user)
            .community(community)
            .build();
    likesRepository.save(likes);
    // community 의 likeCount 를 올린다.
    likes.getCommunity().addLikeCnt();
    return likes.getId();
  }

  @Override
  @Transactional
  public void deleteLikes(Long userId , Long communityId) {
    // 로그인한 유저와  , 게시글 아이디로 좋아요를 찾는다.
    Likes likes = likesRepository.findByUserIdAndCommunityIdAndIsActiveTrue(userId , communityId).orElseThrow(() -> new CustomException(ErrorCode.LIKES_EXISTS));
    // community 의 likeCount 를 줄인다.
    likes.getCommunity().minusLikeCnt();
    likes.deleteEntity();
  }
}
