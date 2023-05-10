package com.jobtang.sourcecompany.api.user.service;

import com.jobtang.sourcecompany.api.user.dto.SignupRequestDto;
import com.jobtang.sourcecompany.api.user.entity.User;
import org.springframework.validation.Errors;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserService {
    User signupUser(SignupRequestDto request);
    boolean validateDuplicateEmail(String email);
    boolean validateDuplicateNickname(String Nickname);

<<<<<<< HEAD


=======
>>>>>>> dcd36873a727d1402c37c4c0deafe32f26e4f324
    Map<String, String>  validateHandling(Errors errors);


}
