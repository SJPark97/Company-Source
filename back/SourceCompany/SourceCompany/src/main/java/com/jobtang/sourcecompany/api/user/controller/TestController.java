package com.jobtang.sourcecompany.api.user.controller;

import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import com.jobtang.sourcecompany.api.user.service.JwtService;
import com.jobtang.sourcecompany.api.user.service.UserService;
import com.jobtang.sourcecompany.config.JwtTokenProvider;
//import com.jobtang.sourcecompany.util.JwtUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.util.Arrays;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    @GetMapping("")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() != null) {
            Cookie oldCookie = Arrays
                    .stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("test"))
                    .findFirst()
                    .orElseGet(() -> {
                        System.out.println("test 쿠키가 없어요");
                        Cookie cookie = new Cookie("test","1");
                        cookie.setMaxAge(60*60);
                        response.addCookie(cookie);
                        return cookie;
                    });
        } else {
            System.out.println("그냥 쿠키가 없어요");
            Cookie cookie = new Cookie("test", "1");
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
        }
//        Cookie[] cookies = request.getCookies();
//
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("test")) {
//                System.out.println("test 쿠키가 있으시네요");
//                break;
//            }
//        }

    }
//    @PostMapping("")
//    public String test(){
//        return "test 통과";
//    }

//    @PostMapping("/token")
//    public User test2() {
//        return userRepository.findById(userId).get();
//    }
}
