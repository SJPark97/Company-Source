package com.jobtang.sourcecompany.api.user.controller;

import com.jobtang.sourcecompany.config.JwtTokenProvider;
//import com.jobtang.sourcecompany.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("")
    public String test(){
        return "test 통과";
    }

    @PostMapping("/token")
    public String test2(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        return jwtTokenProvider.getUserPk(token);
    }
}
