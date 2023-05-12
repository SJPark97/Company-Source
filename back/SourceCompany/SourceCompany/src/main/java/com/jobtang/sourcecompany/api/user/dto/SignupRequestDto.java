package com.jobtang.sourcecompany.api.user.dto;

import com.jobtang.sourcecompany.api.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@RequiredArgsConstructor
@ApiModel(value = "회원 가입 정보", description = "아이디, 닉네임, 비밀번호, 이메일, 성별, 생일")
//@Schema(description = "게시물 리스트 응답DTO")
// 회원가입시 받는 정보들
public class SignupRequestDto {

    @ApiModelProperty(value = "닉네임")
    @NotBlank(message = "닉네임은 필수 입력 값입니다")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")

    private String nickname;

    @ApiModelProperty(value = "이메일")
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,30}$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @ApiModelProperty(value = "비밀번호")
    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$",
             message = "비밀번호는 8~16글자 대소문자와 숫자, 특수문자를 포함해주세요")
    private String password;

    @ApiModelProperty(value = "성별")
    private String sex;

    @ApiModelProperty(value = "생일")
    @Past(message = "생일은 현재보다 과거여야 합니다.")
    private LocalDate birth;


    public User toEntity() {
        return User.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .sex(sex)
                .birth(birth)
//                .role(new ArrayList<>(Arrays.asList("ROLE_MEMBER")))
                .role("ROLE_MEMBER")
                .build();
    }
}
