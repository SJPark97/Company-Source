package com.jobtang.sourcecompany.api.user.controller;

import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import com.jobtang.sourcecompany.api.user.service.JwtService;
import com.jobtang.sourcecompany.api.user.service.UserService;
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

    private final JwtService jwtService;
    private final UserRepository userRepository;

//    @PostMapping("")
//    public String test(){
//        return "test 통과";
//    }

//    @PostMapping("/token")
//    public User test2() {
//        return userRepository.findById(userId).get();
//    }
}
