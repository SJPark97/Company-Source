package com.jobtang.sourcecompany.api.user.service;

import com.jobtang.sourcecompany.api.user.dto.UserCommunity;
import com.jobtang.sourcecompany.api.user.dto.UserInfo;

import java.util.List;

public interface MyPageService {
    UserInfo getUserInfo(Long userId);
    List<UserCommunity> getUserCommunityList(Long userId);
}
