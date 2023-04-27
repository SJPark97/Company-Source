package com.jobtang.sourcecompany.api.user.controller;

import com.jobtang.sourcecompany.api.user.dto.JwtRequestDto;
import com.jobtang.sourcecompany.api.user.dto.SignupRequestDto;
import com.jobtang.sourcecompany.api.user.repo.UserRepo;
import com.jobtang.sourcecompany.api.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
//    private final UserService userService;
//    @PostMapping(value = "/login")
//    public String loginUser(@RequestBody JwtRequestDto request) {
//        return userService.loginUser(request);
//    }
//
//
//    //
//    @PostMapping("/signup")
//    public String signupUser(@RequestBody SignupRequestDto request) {
//        return "test";
//    }

}
