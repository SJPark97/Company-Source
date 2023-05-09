package com.jobtang.sourcecompany.api.user.service;

import com.jobtang.sourcecompany.config.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

    private final JwtTokenProvider jwtTokenProvider;

    // HttpServletRequest를 받고 거기서 토큰을 가져와서 userId값으로 반환
    public Long userPkByRequest(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        return  Long.parseLong(jwtTokenProvider.getUserPk(token));
    }
}
