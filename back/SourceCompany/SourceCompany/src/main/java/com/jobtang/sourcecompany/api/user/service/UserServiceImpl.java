package com.jobtang.sourcecompany.api.user.service;

import com.jobtang.sourcecompany.api.user.dto.SignupRequestDto;
import com.jobtang.sourcecompany.api.user.entity.User;
import com.jobtang.sourcecompany.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
// 기존에 만든 UserService interface와 시큐리티 적용을 위한 UserDetailsService를 상속
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    // UserDetailsService를 상속(implements)을 받기위해 override 필요한 메서드
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

    }

    // 회원가입 유효성 에러 핸들링
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    // 회원 가입 처리
    public User signupUser(SignupRequestDto request) {
        // 비밀번호 인코딩
//        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User signUser = request.toEntity();

        return userRepository.save(signUser);
    }

    // 중복 email 회원 가입 검증 (로그인 email)
    public boolean validateDuplicateEmail(String email) {
        // email 중복 유저가 있다면 true
        // email 중복 유저가 없다면 false
        return userRepository.existsByEmail(email);
    }

    // 중복 nickname 회원 가입 검증
    public boolean validateDuplicateNickname(String Nickname) {
        // nickname 중복 유저가 있다면 true
        // nickname 중복 유저가 없다면 false
        return userRepository.existsByNickname(Nickname);
    }


}
