package com.jobtang.sourcecompany.api.user.service;

import com.jobtang.sourcecompany.api.user.dto.SignupRequestDto;
import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.validation.Errors;

import java.util.Map;

public interface UserService {
    User signupUser(SignupRequestDto request);
    boolean validateDuplicateEmail(String email);
    boolean validateDuplicateNickname(String Nickname);



    Map<String, String>  validateHandling(Errors errors);


}
