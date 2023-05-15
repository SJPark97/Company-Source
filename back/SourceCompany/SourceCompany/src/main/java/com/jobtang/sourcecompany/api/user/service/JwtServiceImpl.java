package com.jobtang.sourcecompany.api.user.service;

import com.jobtang.sourcecompany.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    private final JwtTokenProvider jwtTokenProvider;

    // token을 userId값으로 반환
    public Long userPkByToken(String token) {
        return Long.parseLong(jwtTokenProvider.getUserPk(token));
    }
}
