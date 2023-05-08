package com.shop.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

// @NotEmpty - NULL, NotBlank - NULL과 "" , Length - 길이 검사, Email - 이메일 형식
//  Max, Min - 지정 값 대소 검사 Null - NULL , NotNull - NULL이 아닌지 검사
@Data
public class MemberFormDto {

    @NotBlank(message = "이름은 필수 입력입니다.")
    private String name;
    @NotEmpty(message = "이메일 필수")
    @Email(message = "이메일 형식으로 좀")
    private String email;
    @NotEmpty(message = "비밀번호 필수임")
    @Length(min=8, max=16, message = "8~16")
    private String password;
    @NotEmpty(message = "주소 필수")
    private String address;
}
