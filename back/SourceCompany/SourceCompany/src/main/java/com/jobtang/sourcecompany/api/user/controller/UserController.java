package com.jobtang.sourcecompany.api.user.controller;

import com.jobtang.sourcecompany.api.exception.ErrorCode;
import com.jobtang.sourcecompany.api.user.dto.LoginRequestDto;
import com.jobtang.sourcecompany.api.user.dto.SignupRequestDto;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import com.jobtang.sourcecompany.api.user.service.UserService;
import com.jobtang.sourcecompany.config.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import com.jobtang.sourcecompany.api.exception.CustomException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Api("유저 API")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    // 로그인. 현재는 jwt 반환하는 형태
    @ApiOperation(
            value = "유저 로그인",
            notes = "email, ",
            response = HashMap.class
    )
    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto request) {
        HashMap<String,Object> result = new HashMap<>();

        Optional<User> user = userRepository.findByEmail(request.getEmail());
//                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (user.isPresent()) {
            result.put("data", jwtTokenProvider.createToken(user.get(), user.get().getRole()));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        throw new CustomException("Not Sign Email", ErrorCode.USER_NOT_FOUND);

//        if (request.getPassword == user.get().getPassword()) {
//
//        }
//        result.put("message", "가입되지 않은 E-MAIL 입니다.");
//        result.put("status", "400");
//        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }


    // 회원가입
    @ApiOperation(
            value = "유저 회원 가입",
            notes = "입력된 값을 가지고 회원가입",
            response = HashMap.class
    )
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody @Valid SignupRequestDto request, Errors errors) {
        HashMap<String,Object> result = new HashMap<>();

        // 기본 유효성 검사
        if (errors.hasErrors()) {
            Map<String, String> validatorResult = userService.validateHandling(errors);
            result.put("data", validatorResult);
            result.put("message", "회원 가입 유효성 에러");
            result.put("status", "400");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        User signUser = userService.signupUser(request);
        if (signUser == null) {
            throw new CustomException("signup failed", ErrorCode.SAVE_FAILED);
//            result.put("status", "400");
//            result.put("message", "회원가입 실패");
//            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        result.put("data", signUser);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // username 중복검증
    @ApiOperation(
            value = "email 중복검증",
            notes = "email 중복인지 확인",
            response = HashMap.class
    )
    @GetMapping("/validusername/{email}")
    public ResponseEntity<?> validateDuplicateEmail(@PathVariable String email) {
//        HashMap<String,Object> result = new HashMap<>();
        boolean isEmailDuplicate = userService.validateDuplicateEmail(email);
        if (isEmailDuplicate) {
            throw new CustomException("Duplicated Email", ErrorCode.USER_EXISTS);
//            result.put("status", "400");
//            result.put("message", "email 중복");
//            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
//        result.put("message", "");
//        result.put("status", "200");
//        return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(
            value = "nickname 중복검증",
            notes = "nickname이 중복인지 확인",
            response = HashMap.class
    )
    @GetMapping("/validnickname/{nickname}")
    public ResponseEntity<?> validateDuplicateNickname(@PathVariable String nickname) {
        HashMap<String,Object> result = new HashMap<>();
        boolean isNicknameDuplicate = userService.validateDuplicateNickname(nickname);
        if (isNicknameDuplicate) {
            throw new CustomException("Duplicated Nickname", ErrorCode.USER_EXISTS);
//            result.put("status", "400");
//            result.put("message", "nickname 중복");
//            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
//        result.put("message", "");
//        result.put("status", "200");
//        return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
