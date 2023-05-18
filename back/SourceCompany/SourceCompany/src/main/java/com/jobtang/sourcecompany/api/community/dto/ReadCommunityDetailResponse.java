package com.jobtang.sourcecompany.api.community.dto;

import com.jobtang.sourcecompany.api.comment.dto.ReadCommentResponse;
import com.jobtang.sourcecompany.api.community.entity.Community;
import lombok.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ReadCommunityDetailResponse {
  private  Long communityId ;

  private String title;


  private String date ;
  private String time ;
  private String content;

  private String userName;

  private boolean isLiked;

  private int likesCount;
  private int viewCount;
  List<ReadCommentResponse> comments;
  public static ReadCommunityDetailResponse EntityToDTO (Community community , int viewCount  , boolean isLiked ,List<ReadCommentResponse> comments ) {
    ReadCommunityDetailResponse readCommentResponse = ReadCommunityDetailResponse.builder()
            .userName(community.getUser().getNickname())
            .date(community.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            .time(community.getCreatedDate().format(DateTimeFormatter.ofPattern("HH:mm")))
            .title(community.getTitle())
            .communityId(community.getId())
            .content(community.getContent())
            .isLiked(isLiked)
            .comments(comments)
            .viewCount(viewCount)
            .likesCount(community.getLikesCnt())
            .build();
    return readCommentResponse;
  }
//comments.stream.map(comment ->  ReadCommentResponse.Ent)
}
