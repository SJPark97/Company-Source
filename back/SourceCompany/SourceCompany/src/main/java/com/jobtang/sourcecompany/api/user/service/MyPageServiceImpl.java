package com.jobtang.sourcecompany.api.user.service;

import com.jobtang.sourcecompany.api.user.dto.UserCommunity;
import com.jobtang.sourcecompany.api.user.dto.UserInfo;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MyPageServiceImpl implements MyPageService{
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserInfo getUserInfo(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return mapper.map(user.get(),UserInfo.class);
        } else {
            return new UserInfo();
        }
    }

    public List<UserCommunity> getUserCommunityList(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(value -> value.getCommunities().stream()
                .map(m -> mapper.map(m, UserCommunity.class))
                .collect(Collectors.toList())).orElseGet(ArrayList::new);
    }

}
